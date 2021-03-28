import 'package:flutter/widgets.dart';
import 'package:flutter_app/model/event.dart';
import 'package:flutter_app/repository/local_database.dart';
import 'package:flutter_app/repository/web_api.dart';

class Repository extends ChangeNotifier {
  final pageSize = 20;
  final WebApi webApi;
  final LocalDatabase localDatabase;

  List<Event> _events = [];
  int _currentPageNumber = 0;

  Repository(this.webApi, this.localDatabase) {
    loadMoreEvents();
  }

  List<Event> getEvents() {
    return _events;
  }

  Future<void> purge() async {
    await localDatabase.deleteEvents();
  }

  Future<void> loadMoreEvents() async {
    final loadedEvents = await _getNextEventsPage();
    _currentPageNumber++;
    if (loadedEvents.isNotEmpty) {
      await localDatabase.insertEvents(loadedEvents);
      await _refreshEvents();
    }
  }

  Future<List<Event>> _getNextEventsPage() {
    final nextPageNumber = _currentPageNumber + 1;
    return webApi.getEventsPage(nextPageNumber, pageSize);
  }

  Future<void> _refreshEvents() async {
    final eventsFromDb = await localDatabase.events();
    _events.clear();
    _events.addAll(eventsFromDb);
    notifyListeners();
  }
}
