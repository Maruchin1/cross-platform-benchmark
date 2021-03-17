import 'dart:convert';

import 'package:flutter_app/model/event.dart';
import 'package:http/http.dart' as http;

const AUTHORITY = '192.168.0.143:8080';

class WebApi {
  Future<List<Event>> getEventsPage(int pageNumber, int pageSize) async {
    final params = {'pageNumber': pageNumber, 'pageSize': pageSize};
    final uri = Uri.http(AUTHORITY, 'events/page', params);
    final response = await http.get(uri);
    if (response.statusCode == 200) {
      final List<Map<String, dynamic>> jsonList = jsonDecode(response.body);
      return jsonList.map((e) => Event.fromJson(e));
    } else {
      throw Exception('Failed to load events page');
    }
  }
}
