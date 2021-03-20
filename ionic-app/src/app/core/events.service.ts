import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {Event} from './event.model';
import {WebApiService} from './web-api.service';
import {LocalDatabaseService} from './local-database.service';

@Injectable({providedIn: 'root'})
export class EventsService {
  constructor(
    private webApiService: WebApiService,
    private localDatabaseService: LocalDatabaseService
  ) {
    this.loadNextEventsPage();
  }

  private currentPageNumber = 0;

  events$: Observable<Event[]> = this.localDatabaseService.getAllEvents();

  async loadNextEventsPage(): Promise<void> {
    const loadedEvents = await this.webApiService.getEventsPage(this.currentPageNumber + 1, 20);
    if (loadedEvents.length > 0) {
      await this.localDatabaseService.insertEvents(loadedEvents);
    }
    this.currentPageNumber++;
  }
}
