import {Injectable} from '@angular/core';
import {Event} from './event.model';
import {BehaviorSubject, Observable} from 'rxjs';
import {Platform} from '@ionic/angular';
import {Connection, createConnection, getRepository, Repository} from 'typeorm';

@Injectable()
export class LocalDatabaseService {
  constructor(
    private platform: Platform
  ) {
  }

  private connection: Promise<Connection>;
  private events$ = new BehaviorSubject<Event[]>([]);

  async initDatabase() {
    await this.platform.ready();
    if (this.platform.is('capacitor')) {
      this.connection = createConnection({
        type: 'cordova',
        database: 'test',
        location: 'default',
        logging: ['error', 'query', 'schema'],
        synchronize: true,
        entities: [Event]
      });
    } else {
      this.connection = createConnection({
        type: 'sqljs',
        autoSave: true,
        location: 'browser',
        logging: ['error', 'query', 'schema'],
        synchronize: true,
        entities: [Event]
      });
    }
    await this.deleteEvents();
  }

  async insertEvents(events: Event[]): Promise<void> {
    const repository = await this.getEventRepo();
    await repository.save(events);
    await this.loadEvents();
  }

  getAllEvents(): Observable<Event[]> {
    return this.events$;
  }

  private async deleteEvents(): Promise<void> {
    const repository = await this.getEventRepo();
    await repository.clear();
  }

  private async loadEvents(): Promise<void> {
    const repository = await this.getEventRepo();
    const savedEvents: Event[] = await repository.find();
    this.events$.next(savedEvents);
  }

  private async getEventRepo(): Promise<Repository<Event>> {
    await this.connection;
    return getRepository('event') as Repository<Event>;
  }
}
