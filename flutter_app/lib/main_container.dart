import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_app/page_events/events_page.dart';

class MainContainer extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    var theme = Theme.of(context);
    var systemStyle = SystemUiOverlayStyle.dark.copyWith(
        statusBarColor: theme.backgroundColor,
        systemNavigationBarColor: theme.primaryColor);
    SystemChrome.setSystemUIOverlayStyle(systemStyle);

    return Scaffold(
      body: EventsPage(),
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
}
