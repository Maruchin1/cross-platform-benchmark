import 'package:flutter/material.dart';

class GalleryItem extends StatelessWidget {
  final String imageUrl;

  GalleryItem(this.imageUrl);

  @override
  Widget build(BuildContext context) {
    return Container(
      child: Card(
        margin: EdgeInsets.all(0),
        elevation: 0,
        shape: RoundedRectangleBorder(
            side: BorderSide(color: Colors.grey.shade400, width: 2),
            borderRadius: BorderRadius.zero),
        child: Container(
          color: Colors.grey.shade300,
          child: AspectRatio(
            aspectRatio: 3 / 4,
            child: SizedBox.expand(
              child: Image.network(
                imageUrl,
                errorBuilder: (context, error, stackTrace) {
                  return Container();
                },
              ),
            ),
          ),
        ),
      ),
    );
  }
}
