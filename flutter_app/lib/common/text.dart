import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';

class TitleText extends StatelessWidget {
  final String data;

  TitleText(this.data);

  @override
  Widget build(BuildContext context) {
    return Text(
      data,
      style: Theme.of(context).textTheme.subtitle1.copyWith(fontSize: 20),
    );
  }
}

class SmallInfoText extends StatelessWidget {
  final String data;

  SmallInfoText(this.data);

  @override
  Widget build(BuildContext context) {
    return Text(
      data,
      style: Theme.of(context).textTheme.caption.copyWith(color: Colors.black),
    );
  }
}

class BodyText extends StatelessWidget {
  final String data;

  BodyText(this.data);

  @override
  Widget build(BuildContext context) {
    return Text(
      data,
      style: Theme.of(context).textTheme.bodyText1,
    );
  }
}
