import 'package:flutter/material.dart';
import 'package:flutter_app/common/cover_image.dart';
import 'package:flutter_app/common/text.dart';
import 'package:flutter_app/model/event.dart';

class EventCollapsedItem extends StatelessWidget {
  final Event item;
  final Function onClick;

  EventCollapsedItem({this.item, this.onClick});

  @override
  Widget build(BuildContext context) {
    return Card(
      margin: EdgeInsets.only(left: 16, right: 16, bottom: 24),
      elevation: 0,
      shape: RoundedRectangleBorder(
          side: BorderSide(color: Colors.grey.shade400, width: 2),
          borderRadius: BorderRadius.zero),
      child: InkWell(
        onTap: () => onClick(),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.stretch,
          children: [
            CoverImage(item.imageUrl),
            Padding(
              padding: EdgeInsets.all(20),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  TitleText(item.name),
                  SizedBox(height: 8),
                  SmallInfoText(item.date)
                ],
              ),
            )
          ],
        ),
      ),
    );
  }
}
