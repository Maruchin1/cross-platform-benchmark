import {useState} from 'react';
import WebApi from './WebApi';
import {Event} from '../model/Event';

const PAGE_SIZE = 20;

export const useRepository = () => {
  const [events, setEvents] = useState<Event[]>([]);
  const [currentPage, setCurrentPage] = useState(0);
  const [allEventsLoaded, setAllEventsLoaded] = useState(false);
  const [loading, setLoading] = useState(false);

  const loadNextEventsPage = async () => {
    console.log('loadNextEventsPage');
    if (loading) return;
    setLoading(true);
    try {
      const loadedEvents = await WebApi.getEventsPage(
        currentPage + 1,
        PAGE_SIZE,
      );
      console.log('loadedEvents: ', loadedEvents);
      if (loadedEvents.length > 0) {
        setEvents([...events, ...loadedEvents]);
        setCurrentPage(currentPage + 1);
      } else {
        setAllEventsLoaded(true);
      }
    } catch (e) {
      console.error(e);
    }
    setLoading(false);
  };

  return {events, loading, loadNextEventsPage};
};
