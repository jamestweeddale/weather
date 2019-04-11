import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({ providedIn: 'root' })
export class StationService {

  constructor(private http: HttpClient) {
  }

  getAll(): Observable<any> { 
    return this.http.get(environment.hostUrlBase + '/station-list');
  }
}
