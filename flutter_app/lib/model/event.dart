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

  @override
  List<Object> get props => [id, imageUrl, name, date, place, description];
}
