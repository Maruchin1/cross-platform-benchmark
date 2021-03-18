import 'package:sqflite/sqflite.dart';

class LocalDatabaseInitializer {
  final Database database;

  LocalDatabaseInitializer(this.database);

  Future<void> initialize() {
    return database.execute(_createEventsTable());
  }

  String _createEventsTable() => '''
    CREATE TABLE events(
            id INTEGER PRIMARY KEY,
            image_url TEXT,
            name TEXT,
            date TEXT,
            place TEXT,
            description TEXT,
            gallery_images_urls TEXT
          ) 
  ''';
}
