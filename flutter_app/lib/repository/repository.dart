import 'package:flutter_app/model/event.dart';
import 'package:flutter_app/repository/local_database.dart';
import 'package:flutter_app/repository/web_api.dart';

class Repository {
  final pageSize = 20;
  final WebApi webApi;
  final LocalDatabase localDatabase;

  int _currentPageNumber = 0;

  Repository(this.webApi, this.localDatabase);

  Future<List<Event>> getEvents() {
    return localDatabase.events();
  }

  Future<bool> loadMoreEvents() async {
    final events = await _getNextEventsPage();
    _currentPageNumber++;
    if (events.isEmpty) {
      return false;
    } else {
      await localDatabase.insertEvents(events);
      return true;
    }
  }

  Future<List<Event>> _getNextEventsPage() {
    final nextPageNumber = _currentPageNumber + 1;
    return webApi.getEventsPage(nextPageNumber, pageSize);
  }
}
