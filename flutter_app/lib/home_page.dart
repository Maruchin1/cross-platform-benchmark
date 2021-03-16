import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_app/event_item.dart';
import 'package:flutter_app/main.dart';
import 'package:flutter_app/repository/repository.dart';

class HomePage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    var theme = Theme.of(context);
    var systemStyle = SystemUiOverlayStyle.dark.copyWith(
        statusBarColor: theme.backgroundColor,
        systemNavigationBarColor: theme.primaryColor);
    SystemChrome.setSystemUIOverlayStyle(systemStyle);

    var repository = Repository.of(context);

    return Scaffold(
      body: NestedScrollView(
        headerSliverBuilder: (context, innerBoxIsScrolled) {
          return <Widget>[
            SliverAppBar(
                backgroundColor: theme.backgroundColor,
                floating: false,
                pinned: false,
                expandedHeight: 300,
                flexibleSpace: FlexibleSpaceBar(
                  collapseMode: CollapseMode.pin,
                  background: Center(
                    child: Text(
                      "25-lecie\nWydziału Grafiki\ni Sztuki Mediów",
                      textAlign: TextAlign.center,
                      style: theme.textTheme.headline4
                          .copyWith(color: Colors.black),
                    ),
                  ),
                )),
            SliverAppBar(
              backgroundColor: theme.backgroundColor,
              floating: false,
              pinned: true,
              title: Text(
                "Wydarzenia",
                style: CustomTextTheme.title(context),
              ),
              centerTitle: true,
            )
          ];
        },
        body: ListView.builder(
          itemCount: repository.eventsList.length,
          itemBuilder: (context, index) {
            return EventItem(repository.eventsList[index]);
          },
        ),
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
}
