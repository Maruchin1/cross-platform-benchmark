import 'package:equatable/equatable.dart';
import 'package:flutter_app/model/event.dart';

abstract class EventsState extends Equatable {
  @override
  List<Object> get props => [];
}

class EventsInitial extends EventsState {}

class EventsFailure extends EventsState {}

class EventsSuccess extends EventsState {
  final List<Event> events;
  final bool hasReachedMax;

  EventsSuccess({this.events, this.hasReachedMax});

  EventsSuccess copyWith({List<Event> events, bool hasReachedMax}) {
    return EventsSuccess(
        events: events ?? this.events,
        hasReachedMax: hasReachedMax ?? this.hasReachedMax);
  }

  @override
  List<Object> get props => [events, hasReachedMax];
}
