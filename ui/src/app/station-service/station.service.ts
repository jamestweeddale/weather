import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({providedIn: 'root'})
export class StationService {

  constructor(private http: HttpClient) {
  }

  getAll(): Observable<any> { //todo type to station
    return this.http.get('//localhost:8080/station-list');
  }
}
