import 'package:flutter_app/bloc/events_event.dart';
import 'package:flutter_app/bloc/events_state.dart';
import 'package:flutter_app/repository/repository.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:rxdart/rxdart.dart';

class EventsBloc extends Bloc<EventsEvent, EventsState> {
  final Repository repository;

  EventsBloc(this.repository) : super(EventsInitial());

  @override
  Stream<Transition<EventsEvent, EventsState>> transformEvents(
      Stream<EventsEvent> events, transitionFn) {
    return super.transformEvents(
      events.debounceTime(const Duration(milliseconds: 1000)),
      transitionFn,
    );
  }

  @override
  Stream<EventsState> mapEventToState(EventsEvent event) async* {
    final currentState = state;
    if (event is EventsFetched && !_hasReachedMax(currentState)) {
      yield* _loadEvents(currentState);
    }
  }

  bool _hasReachedMax(EventsState state) {
    return state is EventsSuccess && state.hasReachedMax;
  }

  Stream<EventsState> _loadEvents(EventsState state) async* {
    try {
      if (state is EventsInitial) {
        yield* _loadInitialEvents();
      } else if (state is EventsSuccess) {
        yield* _loadMoreEvents(state);
      }
    } catch (e) {
      yield EventsFailure();
    }
  }

  Stream<EventsState> _loadInitialEvents() async* {
    final events = await repository.getEvents();
    final initialState = EventsSuccess(events: events, hasReachedMax: false);
    yield initialState;
    yield* _loadMoreEvents(initialState);
  }

  Stream<EventsState> _loadMoreEvents(EventsSuccess state) async* {
    final moreEventsLoaded = await repository.loadMoreEvents();
    if (moreEventsLoaded) {
      final events = await repository.getEvents();
      yield EventsSuccess(events: events, hasReachedMax: false);
    } else {
      yield EventsSuccess(events: state.events, hasReachedMax: true);
    }
  }
}
