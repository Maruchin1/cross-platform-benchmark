import 'package:animated_size_and_fade/animated_size_and_fade.dart';
import 'package:flutter/material.dart';
import 'package:flutter_app/common/bottom_loader.dart';
import 'package:flutter_app/model/event.dart';
import 'package:flutter_app/page_events/event_collapsed_item.dart';
import 'package:flutter_app/page_events/event_expanded_item.dart';
import 'package:flutter_app/repository/repository.dart';
import 'package:provider/provider.dart';
import 'package:scroll_to_index/scroll_to_index.dart';

class EventsList extends StatefulWidget {
  final AutoScrollController controller;

  EventsList(this.controller);

  @override
  State<StatefulWidget> createState() {
    return _EventsListState();
  }
}

class _EventsListState extends State<EventsList> with TickerProviderStateMixin {
  final _scrollThreshold = 200.0;
  var _expandedItemPosition = -1;
  Repository _repository;

  @override
  void initState() {
    super.initState();
    widget.controller.addListener(_onScroll);
  }

  @override
  Widget build(BuildContext context) {
    _repository = Provider.of<Repository>(context, listen: false);
    return Consumer<Repository>(builder: (context, value, child) {
      final events = value.getEvents();
      if (events.isNotEmpty) {
        return SliverList(
          delegate: SliverChildBuilderDelegate((context, index) {
            return AutoScrollTag(
              key: ValueKey(index),
              controller: widget.controller,
              index: index,
              child: AnimatedSizeAndFade(
                vsync: this,
                child: _getItem(events, index),
              ),
            );
          }, childCount: _getItemsCount(events)),
        );
      }
      return SliverPadding(padding: EdgeInsets.zero);
    });
  }

  Widget _getItem(List<Event> events, int index) {
    if (index >= events.length) {
      return BottomLoader();
    } else if (_isExpanded(index)) {
      return EventExpandedItem(
        item: events[index],
        onClose: () => _closeExpandedItem(),
      );
    } else {
      return EventCollapsedItem(
        item: events[index],
        onClick: () => _expandItem(index),
      );
    }
  }

  void _onScroll() {
    final maxScroll = widget.controller.position.maxScrollExtent;
    final currentScroll = widget.controller.position.pixels;
    if (maxScroll - currentScroll <= _scrollThreshold) {
      _repository.loadMoreEvents();
    }
  }

  void _expandItem(int position) {
    setState(() {
      _expandedItemPosition = position;
      _scrollToIndex(position);
    });
  }

  void _closeExpandedItem() {
    setState(() {
      _expandedItemPosition = -1;
    });
  }

  void _scrollToIndex(int position) {
    Future.delayed(Duration(milliseconds: 400), () {
      widget.controller
          .scrollToIndex(position, preferPosition: AutoScrollPosition.begin);
    });
  }

  bool _isExpanded(int position) {
    return _expandedItemPosition == position;
  }

  int _getItemsCount(List<Event> events) {
    return events.length + 1;
  }
}
