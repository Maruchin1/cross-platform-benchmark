import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_app/repository/repository.dart';

import 'home_page.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
          // This is the theme of your application.
          //
          // Try running your application with "flutter run". You'll see the
          // application has a blue toolbar. Then, without quitting the app, try
          // changing the primarySwatch below to Colors.green and then invoke
          // "hot reload" (press "r" in the console where you ran "flutter run",
          // or simply save your changes to "hot reload" in a Flutter IDE).
          // Notice that the counter didn't reset back to zero; the application
          // is not restarted.
          brightness: Brightness.light,
          primaryColor: Colors.grey.shade300,
          primaryColorDark: Colors.grey.shade400,
          primarySwatch: Colors.blue,
          backgroundColor: Colors.grey.shade50),
      home: Repository(
        child: HomePage(),
      ),
    );
  }
}

class CustomTextTheme {
  static TextStyle title(BuildContext context) {
    return Theme.of(context).textTheme.subtitle1.copyWith(fontSize: 20);
  }
}
