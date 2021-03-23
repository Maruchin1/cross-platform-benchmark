import {Event} from '../model/Event';

const BASE_URL = 'http://192.168.0.143:8080';

const getEventsPage = async (
  pageNumber: number,
  pageSize: number,
): Promise<Event[]> => {
  const response = await fetch(
    BASE_URL + `/events/page?pageNumber=${pageNumber}&pageSize=${pageSize}`,
  );
  const body = await response.json();
  if (response.status === 200) {
    return Promise.resolve(body);
  } else {
    return Promise.reject(body);
  }
};

export default {getEventsPage};
