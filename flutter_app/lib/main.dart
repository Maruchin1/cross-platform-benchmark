import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_app/bloc/events_bloc.dart';
import 'package:flutter_app/repository/local_database.dart';
import 'package:flutter_app/repository/local_database_initializer.dart';
import 'package:flutter_app/repository/repository.dart';
import 'package:flutter_app/repository/web_api.dart';
import 'package:flutter_app/transition_observer.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:http/http.dart';
import 'package:path/path.dart';
import 'package:sqflite/sqflite.dart';
import 'bloc/events_event.dart';
import 'main_container.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  TransitionObserver();

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
      home: MultiBlocProvider(
        providers: [
          BlocProvider<EventsBloc>(
              create: (context) => _createEventsBloc(database))
        ],
        child: MainContainer(),
      ),
    );
  }
}

class CustomTextTheme {
  static TextStyle title(BuildContext context) {
    return Theme
        .of(context)
        .textTheme
        .subtitle1
        .copyWith(fontSize: 20);
  }
}

EventsBloc _createEventsBloc(Future<Database> database) {
  final webApi = WebApi(httpClient: Client());
  final localDatabase = LocalDatabase(database);
  final repository = Repository(webApi, localDatabase);
  return EventsBloc(repository)
    ..add(EventsFetched());
}
