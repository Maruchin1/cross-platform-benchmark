import 'package:flutter/material.dart';
import 'package:flutter_app/main.dart';
import 'package:flutter_app/model/event.dart';

class EventItem extends StatelessWidget {
  final Event item;

  EventItem(this.item);

  @override
  Widget build(BuildContext context) {
    var theme = Theme.of(context);

    return Card(
      margin: EdgeInsets.only(left: 16, right: 16, bottom: 24),
      elevation: 0,
      shape: RoundedRectangleBorder(
          side: BorderSide(color: Colors.grey.shade400, width: 2),
          borderRadius: BorderRadius.zero),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.stretch,
        children: [
          Container(
            color: Colors.grey.shade300,
            child: AspectRatio(
              aspectRatio: 4 / 3,
              child: Image.network(item.imageUrl),
            ),
          ),
          Padding(
            padding: EdgeInsets.all(20),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text(
                  item.name,
                  style: CustomTextTheme.title(context),
                ),
                SizedBox(
                  height: 8,
                ),
                Text(item.date, style: theme.textTheme.caption)
              ],
            ),
          )
        ],
      ),
    );
  }
}
