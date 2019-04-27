import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ReadingService {

  constructor(private http: HttpClient) {
  }

  getReadingKeysForStation(stationId: number): Observable<any> {
    return this.http.get(environment.hostUrlBase + '/station/' + stationId + '/reading-keys');
  }

  getLatestValueFor(stationId: number, readingKeyId: number): Observable<Reading> {
    return this.http.get<Reading>(environment.hostUrlBase + '/station/' + stationId + '/latest-reading/' + readingKeyId);
  }

  getLastReading(stationId: number): Observable<Reading> {
    return this.http.get<Reading>(environment.hostUrlBase + '/station/' + stationId + '/last-reading');
  }
}
