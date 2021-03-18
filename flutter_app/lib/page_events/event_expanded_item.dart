import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:flutter_app/common/cover_image.dart';
import 'package:flutter_app/common/text.dart';
import 'package:flutter_app/model/event.dart';
import 'package:flutter_app/common/gallery_item.dart';

class EventExpandedItem extends StatelessWidget {
  final Event item;
  final Function onClose;

  EventExpandedItem({this.item, this.onClose});

  @override
  Widget build(BuildContext context) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Stack(
          children: [
            CoverImage(item.imageUrl),
            IconButton(
                icon: Icon(Icons.close),
                color: Colors.white,
                onPressed: () => onClose.call())
          ],
        ),
        Padding(
          padding: EdgeInsets.only(left: 32, right: 32),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              SizedBox(height: 34),
              TitleText(item.name),
              SizedBox(height: 8),
              SmallInfoText(item.date),
              SizedBox(height: 8),
              SmallInfoText('Miejsce: ' + item.place),
              SizedBox(height: 16),
              BodyText(item.description),
              SizedBox(height: 24),
              SmallInfoText('Galeria'),
              SizedBox(height: 16),
            ],
          ),
        ),
        Container(
          height: 220,
          child: ListView.builder(
            scrollDirection: Axis.horizontal,
            itemCount: item.galleryImagesUrls.length,
            itemBuilder: (context, index) {
              return GalleryItem(item.galleryImagesUrls[index]);
            },
          ),
        ),
        SizedBox(height: 32)
      ],
    );
  }
}
