import 'package:flutter/widgets.dart';
import 'package:flutter_app/model/event.dart';

class Repository extends InheritedWidget {
  Repository({@required Widget child}) : super(child: child);

  final List<Event> eventsList = [
    new Event(1, "", "Test 1", "2021.01.23", "Miejsce", "Opis", []),
    new Event(1, "", "Test 1", "2021.01.23", "Miejsce", "Opis", []),
    new Event(1, "", "Test 1", "2021.01.23", "Miejsce", "Opis", []),
    new Event(1, "", "Test 1", "2021.01.23", "Miejsce", "Opis", []),
    new Event(1, "", "Test 1", "2021.01.23", "Miejsce", "Opis", []),
    new Event(1, "", "Test 1", "2021.01.23", "Miejsce", "Opis", []),
    new Event(1, "", "Test 1", "2021.01.23", "Miejsce", "Opis", []),
    new Event(1, "", "Test 1", "2021.01.23", "Miejsce", "Opis", []),
    new Event(1, "", "Test 1", "2021.01.23", "Miejsce", "Opis", []),
  ];

  @override
  bool updateShouldNotify(covariant Repository oldWidget) {
    return eventsList != oldWidget.eventsList;
  }

  static Repository of(BuildContext context) {
    final Repository result =
        context.dependOnInheritedWidgetOfExactType<Repository>();
    assert(result != null, 'No Repository found in context');
    return result;
  }
}
