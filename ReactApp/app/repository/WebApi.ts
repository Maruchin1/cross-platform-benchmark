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
    const events: Event[] = body.map(singleEventBody => {
      const event = new Event();
      event.id = singleEventBody.id;
      event.imageUrl = singleEventBody.imageUrl;
      event.name = singleEventBody.name;
      event.date = singleEventBody.date;
      event.place = singleEventBody.place;
      event.description = singleEventBody.description;
      event.galleryImagesUrls = JSON.stringify(
        singleEventBody.galleryImagesUrls,
      );
      return event;
    });
    return Promise.resolve(events);
  } else {
    return Promise.reject(body);
  }
};

export default {getEventsPage};
