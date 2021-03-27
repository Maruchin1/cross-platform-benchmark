import {Component} from '@angular/core';
import {EventsService} from '../core/events.service';
import {Observable} from 'rxjs';
import {Event} from '../core/event.model';

@Component({
  selector: 'app-tab1',
  templateUrl: 'events.page.html',
  styleUrls: ['events.page.scss']
})
export class EventsPage {
  constructor(
    private eventsService: EventsService
  ) {
  }

  events$: Observable<Event[]> = this.eventsService.events$;

  private expandedEventId = -1;

  async expandEvent(event: Event) {
    this.expandedEventId = event.id;
  }

  isExpanded(event: Event): boolean {
    return this.expandedEventId === event.id;
  }

  async loadMoreEvents(event) {
    await this.eventsService.loadNextEventsPage();
    event.target.complete();
  }
}
