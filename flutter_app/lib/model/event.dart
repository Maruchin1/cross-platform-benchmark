import 'package:equatable/equatable.dart';

class Event extends Equatable {
  final int id;
  final String imageUrl;
  final String name;
  final String date;
  final String place;
  final String description;
  final List<String> galleryImagesUrls;

  Event({
    this.id,
    this.imageUrl,
    this.name,
    this.date,
    this.place,
    this.description,
    this.galleryImagesUrls,
  });

  Map<String, dynamic> toMap() {
    return {
      'id': id,
      'image_url': imageUrl,
      'name': name,
      'date': date,
      'place': place,
      'description': description,
      'gallery_images_urls': _encodeGalleyImagesUrls()
    };
  }

  String _encodeGalleyImagesUrls() {
    return galleryImagesUrls.join(',');
  }

  static List<String> _decodeGalleryImagesUrls(String value) {
    return value.split(',');
  }

  factory Event.fromJson(Map<String, dynamic> json) {
    List<dynamic> galleryImagesUrlsDynamic = json['galleryImagesUrls'];
    List<String> galleryImagesUrls =
        galleryImagesUrlsDynamic.map((e) => e as String).toList();
    return Event(
      id: json['id'] as int,
      imageUrl: json['imageUrl'] as String,
      name: json['name'] as String,
      date: json['date'] as String,
      place: json['place'] as String,
      description: json['description'] as String,
      galleryImagesUrls: galleryImagesUrls,
    );
  }

  factory Event.fromDatabase(Map<String, dynamic> record) {
    final String galleryImagesUrlsValue = record['gallery_images_urls'];
    final List<String> galleryImagesUrls =
        _decodeGalleryImagesUrls(galleryImagesUrlsValue);
    return Event(
      id: record['id'] as int,
      imageUrl: record['image_url'] as String,
      name: record['name'] as String,
      date: record['date'] as String,
      place: record['place'] as String,
      description: record['description'] as String,
      galleryImagesUrls: galleryImagesUrls,
    );
  }

  @override
  List<Object> get props => [id, imageUrl, name, date, place, description];
}
