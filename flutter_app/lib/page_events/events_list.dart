import 'package:animated_size_and_fade/animated_size_and_fade.dart';
import 'package:flutter/material.dart';
import 'package:flutter_app/bloc/events_bloc.dart';
import 'package:flutter_app/bloc/events_event.dart';
import 'package:flutter_app/bloc/events_state.dart';
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
  var expandedItemPosition = -1;
  EventsBloc _eventsBloc;

  @override
  void initState() {
    super.initState();
    widget.controller.addListener(_onScroll);
    _eventsBloc = BlocProvider.of<EventsBloc>(context);
  }

  void expandItem(int position) {
    setState(() {
      expandedItemPosition = position;
      scrollToIndex(position);
    });
  }

  void closeExpandedItem() {
    setState(() {
      expandedItemPosition = -1;
    });
  }

  void scrollToIndex(int position) {
    Future.delayed(Duration(milliseconds: 400), () {
      widget.controller
          .scrollToIndex(position, preferPosition: AutoScrollPosition.begin);
    });
  }

  bool isExpanded(int position) {
    return expandedItemPosition == position;
  }

  @override
  Widget build(BuildContext context) {
    return BlocBuilder<EventsBloc, EventsState>(builder: (context, state) {
      if (state is EventsSuccess) {
        return SliverList(
          delegate: SliverChildBuilderDelegate((context, index) {
            final event = state.events[index];
            return AutoScrollTag(
              key: ValueKey(index),
              controller: widget.controller,
              index: index,
              child: AnimatedSizeAndFade(
                vsync: this,
                child: isExpanded(index)
                    ? EventExpandedItem(
                        item: event,
                        onClose: () => closeExpandedItem(),
                      )
                    : EventCollapsedItem(
                        item: event,
                        onClick: () => expandItem(index),
                      ),
              ),
            );
          }, childCount: state.events.length),
        );
      }
      return SliverPadding(padding: EdgeInsets.zero);
    });
  }

  void _onScroll() {
    final maxScroll = widget.controller.position.maxScrollExtent;
    final currentScroll = widget.controller.position.pixels;
    if (maxScroll - currentScroll <= _scrollThreshold) {
      _eventsBloc.add(EventsFetched());
    }
  }
}
