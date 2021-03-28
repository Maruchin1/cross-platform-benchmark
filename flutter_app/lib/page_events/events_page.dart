import 'package:flutter/material.dart';
import 'package:flutter_app/page_events/events_list.dart';
import 'package:scroll_to_index/scroll_to_index.dart';
import '../main.dart';

class EventsPage extends StatelessWidget {
  final scrollDirection = Axis.vertical;

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);

    final controller = AutoScrollController(
        viewportBoundaryGetter: () =>
            Rect.fromLTRB(0, 0, 0, MediaQuery.of(context).padding.bottom),
        axis: scrollDirection);

    return CustomScrollView(
      scrollDirection: scrollDirection,
      controller: controller,
      slivers: [
        SliverToBoxAdapter(
          child: Padding(
            padding: EdgeInsets.only(top: 156, bottom: 100),
            child: Text(
              "25-lecie\nWydziału Grafiki\ni Sztuki Mediów",
              textAlign: TextAlign.center,
              style: theme.textTheme.headline4.copyWith(color: Colors.black),
            ),
          ),
        ),
        SliverAppBar(
          backgroundColor: theme.backgroundColor,
          floating: false,
          pinned: true,
          title: Text(
            "Wydarzenia",
            style: CustomTextTheme.title(context),
          ),
          centerTitle: true,
        ),
        SliverToBoxAdapter(
          child: SizedBox(height: 48),
        ),
        EventsList(controller)
      ],
    );
  }
}
