import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';
import {Event} from './event.model';

@Injectable({providedIn: 'root'})
export class LocalDatabaseService {

  private collectionMock = new BehaviorSubject<Event[]>([]);

  async insertEvents(events: Event[]): Promise<void> {
    const currentState = this.collectionMock.value;
    const newState = [...currentState, ...events];
    this.collectionMock.next(newState);
  }

  getAllEvents(): Observable<Event[]> {
    return this.collectionMock;
  }
}
