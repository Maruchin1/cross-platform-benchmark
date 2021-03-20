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

  async loadMoreEvents(event) {
    await this.eventsService.loadNextEventsPage();
    event.target.complete();
  }
}
