import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_app/repository/local_database.dart';
import 'package:flutter_app/repository/local_database_initializer.dart';
import 'package:flutter_app/repository/repository.dart';
import 'package:flutter_app/repository/web_api.dart';
import 'package:http/http.dart';
import 'package:path/path.dart';
import 'package:provider/provider.dart';
import 'package:sqflite/sqflite.dart';
import 'tabs.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();

  final Future<Database> database = openDatabase(
    join(await getDatabasesPath(), 'local_database.db'),
    onCreate: (db, version) {
      return LocalDatabaseInitializer(db).initialize();
    },
    version: 1,
  );

  runApp(MyApp(database));
}

class MyApp extends StatelessWidget {
  final Future<Database> database;

  MyApp(this.database);

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
      home: ChangeNotifierProvider(
        create: (context) => _createRepository(database),
        child: Tabs(),
      ),
    );
  }
}

class CustomTextTheme {
  static TextStyle title(BuildContext context) {
    return Theme.of(context).textTheme.subtitle1.copyWith(fontSize: 20);
  }

  static TextStyle body(BuildContext context) {
    return Theme.of(context).textTheme.caption.copyWith(color: Colors.black);
  }
}

Repository _createRepository(Future<Database> database) {
  final webApi = WebApi(httpClient: Client());
  final localDatabase = LocalDatabase(database);
  return Repository(webApi, localDatabase);
}
