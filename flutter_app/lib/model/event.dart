import 'package:equatable/equatable.dart';

class Event extends Equatable {
  final int id;
  final String imageUrl;
  final String name;
  final String date;
  final String place;
  final String description;
  final List<String> galleryImagesUrls;

  Event(this.id, this.imageUrl, this.name, this.date, this.place,
      this.description, this.galleryImagesUrls);

  factory Event.fromJson(Map<String, dynamic> json) {
    return Event(json['id'], json['imageUrl'], json['name'], json['date'],
        json['place'], json['description'], json['galleryImagesUrls']);
  }

  @override
  List<Object> get props => [id, imageUrl, name, date, place, description];
}
