import {useEffect, useMemo, useState} from 'react';
import {Connection, createConnection, getRepository} from 'typeorm/browser';
import {Event} from '../model/Event';

export const useLocalDatabase = () => {
  const [connection, setConnection] = useState<Connection | null>(null);
  const [events, setEvents] = useState<Event[]>([]);

  const isInitialized = useMemo(() => {
    return connection !== null;
  }, [connection]);

  useEffect(() => {
    if (connection === null) {
      setupConnection();
    }
  }, [connection]);

  const setupConnection = async () => {
    console.log('## setupConnection');
    try {
      const createdConnection = await createConnection({
        type: 'react-native',
        database: 'test',
        location: 'default',
        logging: ['query', 'error', 'warn'],
        synchronize: true,
        entities: [Event],
      });
      setConnection(createdConnection);
      await clearEvents();
    } catch (e) {
      console.log(e);
    }
  };

  const clearEvents = async () => {
    console.log('## clearEvents');
    const repo = getRepository(Event);
    await repo.clear();
  };

  const insertEvents = async (newEvents: Event[]) => {
    console.log('## insertEvents');
    if (!connection) {
      throw new Error('Connection not initialized');
    }
    const repo = getRepository(Event);
    console.log('## repo');
    for (const event of newEvents) {
      await repo.save(event);
      console.log('## event saved');
    }
    console.log('## all events saved');
    await refreshEvents();
    console.log('## refresh');
  };

  const refreshEvents = async () => {
    console.log('## refreshEvents');
    if (!connection) {
      throw new Error('Connection not initialized');
    }
    const repo = getRepository(Event);
    const loadedEvents = await repo.find();
    console.log('## eventsFromLocalDb: ', loadedEvents);
    setEvents(loadedEvents);
  };

  return {isInitialized, events, setupConnection, insertEvents};
};
