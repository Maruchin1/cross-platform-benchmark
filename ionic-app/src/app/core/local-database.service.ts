import {Injectable} from '@angular/core';
import {Event} from './event.model';
import {BehaviorSubject, Observable} from 'rxjs';
import {Platform} from '@ionic/angular';
import {createConnection, getRepository, Repository} from 'typeorm';

@Injectable()
export class LocalDatabaseService {
  constructor(
    private platform: Platform
  ) {
  }

  private events$ = new BehaviorSubject<Event[]>([]);

  async initDatabase() {
    await this.platform.ready();
    if (this.platform.is('capacitor')) {
      await createConnection({
        type: 'cordova',
        database: 'test',
        location: 'default',
        logging: ['error', 'query', 'schema'],
        synchronize: true,
        entities: [Event]
      });
    } else {
      await createConnection({
        type: 'sqljs',
        autoSave: true,
        location: 'browser',
        logging: ['error', 'query', 'schema'],
        synchronize: true,
        entities: [Event]
      });
    }
  }

  async insertEvents(events: Event[]): Promise<void> {
    const repository = getRepository('event') as Repository<Event>;
    await repository.save(events);
    await this.loadEvents();
  }

  getAllEvents(): Observable<Event[]> {
    return this.events$;
  }

  private async loadEvents(): Promise<void> {
    const repository = getRepository('event') as Repository<Event>;
    const savedEvents: Event[] = await repository.find();
    this.events$.next(savedEvents);
  }
}
