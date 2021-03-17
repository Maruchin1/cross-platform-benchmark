import 'package:animated_size_and_fade/animated_size_and_fade.dart';
import 'package:flutter/material.dart';
import 'package:flutter_app/page_events/event_collapsed_item.dart';
import 'package:flutter_app/page_events/event_expanded_item.dart';
import 'package:flutter_app/repository/repository.dart';
import 'package:scroll_to_index/scroll_to_index.dart';

class EventsList extends StatefulWidget {
  final eventsList = Repository().eventsList;
  final AutoScrollController controller;

  EventsList(this.controller);

  @override
  State<StatefulWidget> createState() {
    return _EventsListState();
  }
}

class _EventsListState extends State<EventsList> with TickerProviderStateMixin {
  var expandedItemPosition = -1;

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
    return SliverList(
      delegate: SliverChildBuilderDelegate((context, index) {
        final event = widget.eventsList[index];
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
      }),
    );
  }
}
