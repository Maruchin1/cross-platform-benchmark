import 'package:flutter/material.dart';
import 'package:flutter_app/bloc/events_event.dart';
import 'package:flutter_app/bloc/events_state.dart';
import 'package:flutter_app/repository/web_api.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:rxdart/rxdart.dart';

class EventsBloc extends Bloc<EventsEvent, EventsState> {
  final pageSize = 20;
  final WebApi webApi;

  EventsBloc({@required this.webApi}) : super(EventsInitial());

  @override
  Stream<Transition<EventsEvent, EventsState>> transformEvents(Stream<EventsEvent> events, transitionFn) {
    return super.transformEvents(
      events.debounceTime(const Duration(milliseconds: 500)),
      transitionFn,
    );
  }

  @override
  Stream<EventsState> mapEventToState(EventsEvent event) async* {
    final currentState = state;
    if (event is EventsFetched && !_hasReachedMax(currentState)) {
      yield* _loadMoreEvents(currentState);
    }
  }

  Stream<EventsState> _loadMoreEvents(EventsState state) async* {
    try {
      if (state is EventsInitial) {
        final events = await webApi.getEventsPage(1, pageSize);
        yield EventsSuccess(events: events, hasReachedMax: false);
      } else if (state is EventsSuccess) {
        final nextPageNumber = _getNextPageNumber(state);
        final events = await webApi.getEventsPage(nextPageNumber, pageSize);
        if (events.isEmpty) {
          yield state.copyWith(hasReachedMax: true);
        } else {
          yield EventsSuccess(
              events: state.events + events, hasReachedMax: false);
        }
      }
    } catch (e) {
      yield EventsFailure();
    }
  }

  bool _hasReachedMax(EventsState state) {
    return state is EventsSuccess && state.hasReachedMax;
  }

  int _getNextPageNumber(EventsSuccess state) {
    final numOfItems = state.events.length;
    final numOfPages = numOfItems ~/ pageSize;
    return numOfPages + 1;
  }
}
