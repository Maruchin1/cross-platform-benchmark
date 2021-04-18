import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';

class CoverImage extends StatelessWidget {
  final String imageUrl;

  CoverImage(this.imageUrl);

  @override
  Widget build(BuildContext context) {
    return Container(
      color: Colors.grey.shade300,
      child: AspectRatio(
        aspectRatio: 4 / 3,
        child: CachedNetworkImage(
          imageUrl: imageUrl,
        ),
      ),
    );
  }
}
