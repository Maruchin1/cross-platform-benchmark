import {createContext, useContext, useEffect} from 'react';
import {Event} from '../model/Event';
import {Connection, createConnection, getRepository} from 'typeorm';

interface LocalDatabaseModel {
  events: Event[];
}

export const LocalDatabaseContext = createContext<LocalDatabaseModel>({
  events: [],
});

let connection: Promise<Connection>;

export const useLocalDatabase = () => {
  const localDatabase = useContext(LocalDatabaseContext);

  useEffect(() => {
    if (!connection) {
      setupConnection();
    }
  }, []);

  const setupConnection = async () => {
    connection = createConnection({
      type: 'react-native',
      database: 'test',
      location: 'default',
      logging: ['error'],
      synchronize: true,
      entities: [Event],
    });
    await clearEvents();
  };

  const clearEvents = async () => {
    await connection;
    const repo = getRepository(Event);
    await repo.clear();
  };

  const insertEvents = async (newEvents: Event[]) => {
    await connection;
    const repo = getRepository(Event);
    await repo.save(newEvents);
    await refreshEvents();
  };

  const refreshEvents = async () => {
    const repo = getRepository(Event);
    localDatabase.events = await repo.find();
  };

  return {events: localDatabase.events, insertEvents};
};
