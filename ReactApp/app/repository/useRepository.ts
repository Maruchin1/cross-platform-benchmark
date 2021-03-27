import {useState} from 'react';
import WebApi from './WebApi';
import {useLocalDatabase} from './LocalDatabase';

const PAGE_SIZE = 20;

export const useRepository = () => {
  const {events, insertEvents} = useLocalDatabase();

  const [currentPage, setCurrentPage] = useState(0);

  const [loading, setLoading] = useState(false);

  const loadNextEventsPage = async () => {
    if (loading) return;
    setLoading(true);
    try {
      const loadedEvents = await WebApi.getEventsPage(
        currentPage + 1,
        PAGE_SIZE,
      );
      if (loadedEvents.length > 0) {
        await insertEvents(loadedEvents);
        setCurrentPage(currentPage + 1);
      }
    } catch (e) {
      console.error(e);
    }
    setLoading(false);
  };

  return {events, loading, loadNextEventsPage};
};
