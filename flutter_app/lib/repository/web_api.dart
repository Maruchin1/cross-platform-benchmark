import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:flutter_app/model/event.dart';
import 'package:http/http.dart';

const AUTHORITY = '192.168.8.157:8080';

class WebApi {
  final Client httpClient;

  WebApi({@required this.httpClient});

  Future<List<Event>> getEventsPage(int pageNumber, int pageSize) async {
    final params = {
      'pageNumber': pageNumber.toString(),
      'pageSize': pageSize.toString()
    };
    final uri = Uri.http(AUTHORITY, 'events/page', params);
    final response = await httpClient.get(uri);
    if (response.statusCode == 200) {
      final List<dynamic> jsonList = jsonDecode(response.body);
      final List<Event> events = [];
      for (var json in jsonList) {
        final event = Event.fromJson(json);
        events.add(event);
      }
      return events;
    } else {
      throw Exception('Failed to load events page');
    }
  }
}
