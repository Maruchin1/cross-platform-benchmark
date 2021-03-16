class Event {
  final int id;
  final String imageUrl;
  final String name;
  final String date;
  final String place;
  final String description;
  final List<String> galleryImagesUrls;

  Event(this.id, this.imageUrl, this.name, this.date, this.place,
      this.description, this.galleryImagesUrls);
}
