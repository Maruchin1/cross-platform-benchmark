import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_app/main.dart';
import 'package:flutter_app/page_department/department_page.dart';
import 'package:flutter_app/page_messages/messages_page.dart';
import 'package:flutter_app/page_timeline/timeline_page.dart';
import 'package:flutter_hooks/flutter_hooks.dart';
import 'page_events/events_page.dart';

enum TabsOption { events, messages, department, timeline }

class Tabs extends HookWidget {
  @override
  Widget build(BuildContext context) {
    final selectedOption = useState(TabsOption.events);
    final theme = Theme.of(context);

    _setSystemStyles(context);
    return Scaffold(
      body: _page(selectedOption),
      bottomNavigationBar: Container(
        height: 60,
        decoration: BoxDecoration(
          border: Border(
            top: BorderSide(color: theme.primaryColorDark, width: 2),
          ),
        ),
        child: Row(
          children: [
            _bottomNavOption(
                context, TabsOption.events, 'wydarzenia', selectedOption),
            _bottomNavOption(
                context, TabsOption.messages, 'komunikaty', selectedOption),
            _bottomNavOption(
                context, TabsOption.department, 'wydział', selectedOption),
            _bottomNavOption(
                context, TabsOption.timeline, '25 lat', selectedOption)
          ],
        ),
      ),
    );
  }

  Widget _page(ValueNotifier<TabsOption> selectedOption) {
    switch (selectedOption.value) {
      case TabsOption.events:
        return EventsPage();
      case TabsOption.messages:
        return MessagesPage();
      case TabsOption.department:
        return DepartmentPage();
      case TabsOption.timeline:
        return TimelinePage();
    }
  }

  Widget _bottomNavOption(
    BuildContext context,
    TabsOption option,
    String text,
    ValueNotifier<TabsOption> selectedOption,
  ) {
    final theme = Theme.of(context);
    return Flexible(
      flex: 1,
      child: Material(
        color: theme.primaryColor,
        child: InkWell(
          onTap: () => selectedOption.value = option,
          child: Center(
            child: Text(
              text,
              style: selectedOption.value == option
                  ? CustomTextTheme.body(context)
                      .copyWith(fontWeight: FontWeight.bold)
                  : CustomTextTheme.body(context),
            ),
          ),
        ),
      ),
    );
  }

  void _setSystemStyles(BuildContext context) {
    final theme = Theme.of(context);
    var systemStyle = SystemUiOverlayStyle.dark.copyWith(
        statusBarColor: theme.backgroundColor,
        systemNavigationBarColor: theme.primaryColor);
    SystemChrome.setSystemUIOverlayStyle(systemStyle);
  }
}
