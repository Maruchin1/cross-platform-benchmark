import {Injectable} from '@angular/core';
import {Event} from './event.model';
import {HttpClient, HttpParams} from '@angular/common/http';

const BASE_URL = 'http://192.168.0.143:8080';

@Injectable({providedIn: 'root'})
export class WebApiService {
  constructor(
    private httpClient: HttpClient
  ) {
  }

  getEventsPage(pageNumber: number, pageSize: number): Promise<Event[]> {
    const paramsObject = {
      pageNumber: pageNumber.toString(),
      pageSize: pageSize.toString()
    };
    return this.httpClient.get<Event[]>(BASE_URL + '/events/page', {
      observe: 'body',
      params: new HttpParams({fromObject: paramsObject})
    }).toPromise();
  }

}
