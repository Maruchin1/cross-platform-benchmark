import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_app/bloc/events_bloc.dart';
import 'package:flutter_app/bloc/events_event.dart';
import 'package:flutter_app/page_events/events_page.dart';
import 'package:flutter_app/repository/web_api.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:http/http.dart' as http;

class MainContainer extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    var theme = Theme.of(context);
    var systemStyle = SystemUiOverlayStyle.dark.copyWith(
        statusBarColor: theme.backgroundColor,
        systemNavigationBarColor: theme.primaryColor);
    SystemChrome.setSystemUIOverlayStyle(systemStyle);

    return Scaffold(
      body: BlocProvider(
        create: (context) => _createEventsBloc(),
        child: EventsPage(),
      ),
      bottomNavigationBar: BottomNavigationBar(
        type: BottomNavigationBarType.fixed,
        backgroundColor: theme.primaryColor,
        selectedItemColor: Colors.black,
        items: [
          BottomNavigationBarItem(icon: Icon(Icons.event), label: "wydarzenia"),
          BottomNavigationBarItem(
              icon: Icon(Icons.message), label: "komunikaty"),
          BottomNavigationBarItem(icon: Icon(Icons.school), label: "Wydział"),
          BottomNavigationBarItem(icon: Icon(Icons.timeline), label: "25 lat")
        ],
      ),
    );
  }

  EventsBloc _createEventsBloc() =>
      EventsBloc(webApi: WebApi(httpClient: http.Client()))
        ..add(EventsFetched());
}
