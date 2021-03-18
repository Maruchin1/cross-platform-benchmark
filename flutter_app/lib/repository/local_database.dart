import 'package:flutter_app/model/event.dart';
import 'package:sqflite/sqflite.dart';

class LocalDatabase {
  final Future<Database> database;

  LocalDatabase(this.database);

  Future<void> insertEvents(List<Event> events) async {
    final Database db = await database;
    for (var singleEvent in events) {
      await db.insert(
        'events',
        singleEvent.toMap(),
        conflictAlgorithm: ConflictAlgorithm.replace,
      );
    }
  }

  Future<List<Event>> events() async {
    final Database db = await database;
    final List<Map<String, dynamic>> events = await db.query('events');
    return List.generate(
      events.length,
      (index) => Event.fromDatabase(events[index]),
    );
  }

  Future<void> deleteEvents() async {
    final Database db = await database;
    await db.delete('events');
  }
}
