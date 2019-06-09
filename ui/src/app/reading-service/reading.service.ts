import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ReadingService {

  constructor(private http: HttpClient) {
  }

  getReadingKeysForStation(stationId: number): Observable<any> {
    return this.http.get('/station/' + stationId + '/reading-keys');
  }

  getAllReadingKeys(): Observable<any> {
    return this.http.get('/reading-keys');
  }

  getLatestValueFor(stationId: number, readingKeyId: number): Observable<Reading> {
    return this.http.get<Reading>('/station/' + stationId + '/latest-reading/' + readingKeyId);
  }

  getLastReading(stationId: number): Observable<Reading> {
    return this.http.get<Reading>('/station/' + stationId + '/last-reading');
  }
}
