import 'package:animated_size_and_fade/animated_size_and_fade.dart';
import 'package:flutter/material.dart';
import 'package:flutter_app/bloc/events_bloc.dart';
import 'package:flutter_app/bloc/events_event.dart';
import 'package:flutter_app/bloc/events_state.dart';
import 'package:flutter_app/common/bottom_loader.dart';
import 'package:flutter_app/page_events/event_collapsed_item.dart';
import 'package:flutter_app/page_events/event_expanded_item.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
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
  EventsBloc _eventsBloc;

  @override
  void initState() {
    super.initState();
    widget.controller.addListener(_onScroll);
    _eventsBloc = BlocProvider.of<EventsBloc>(context);
  }

  @override
  Widget build(BuildContext context) {
    return BlocBuilder<EventsBloc, EventsState>(builder: (context, state) {
      if (state is EventsSuccess) {
        return SliverList(
          delegate: SliverChildBuilderDelegate((context, index) {
            return AutoScrollTag(
              key: ValueKey(index),
              controller: widget.controller,
              index: index,
              child: AnimatedSizeAndFade(
                vsync: this,
                child: _getItem(state, index),
              ),
            );
          }, childCount: _getItemsCount(state)),
        );
      }
      return SliverPadding(padding: EdgeInsets.zero);
    });
  }

  Widget _getItem(EventsSuccess state, int index) {
    if (index >= state.events.length) {
      return BottomLoader();
    } else if (_isExpanded(index)) {
      return EventExpandedItem(
        item: state.events[index],
        onClose: () => _closeExpandedItem(),
      );
    } else {
      return EventCollapsedItem(
        item: state.events[index],
        onClick: () => _expandItem(index),
      );
    }
  }

  void _onScroll() {
    final maxScroll = widget.controller.position.maxScrollExtent;
    final currentScroll = widget.controller.position.pixels;
    if (maxScroll - currentScroll <= _scrollThreshold) {
      _eventsBloc.add(EventsFetched());
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

  int _getItemsCount(EventsSuccess state) {
    if (state.hasReachedMax) {
      return state.events.length;
    } else {
      return state.events.length + 1;
    }
  }
}
