import {Injectable} from '@angular/core';
import {Event} from './event.model';
import {Storage} from '@ionic/storage-angular';
import {BehaviorSubject, Observable} from 'rxjs';

const KEY_EVENTS = 'key_events';

@Injectable()
export class LocalDatabaseService {
  constructor(
    storage: Storage
  ) {
    this.storage = this.initStorage(storage);
  }

  private readonly storage: Promise<Storage>;
  private events$ = new BehaviorSubject<Event[]>([]);

  async insertEvents(events: Event[]): Promise<void> {
    const storage = await this.storage;
    const savedEvents: Event[] = (await storage.get(KEY_EVENTS)) ?? [];
    const updatedEvents = [...savedEvents, ...events];
    await storage.set(KEY_EVENTS, updatedEvents);
    await this.loadEvents();
  }

  getAllEvents(): Observable<Event[]> {
    return this.events$;
  }

  private async initStorage(storage: Storage): Promise<Storage> {
    const createdStorage = await storage.create();
    await createdStorage.clear();
    return createdStorage;
  }

  private async loadEvents(): Promise<void> {
    const storage = await this.storage;
    const savedEvents: Event[] = (await storage.get(KEY_EVENTS)) ?? [];
    this.events$.next(savedEvents);
  }
}
