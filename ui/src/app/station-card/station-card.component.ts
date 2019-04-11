import { Component, OnInit, Input, ViewChild, AfterViewInit, Output, EventEmitter } from '@angular/core';
import { environment } from '../../environments/environment';

@Component({
  selector: 'station-card',
  templateUrl: './station-card.component.html',
  styleUrls: ['./station-card.component.css']
})
export class StationCardComponent implements OnInit {

  @Input() station: Station;
  readingsLastUpdated: Date = new Date();
  @Output() lastUpdatedEmitter: EventEmitter<Date> = new EventEmitter<Date>();

  latestImgPath:string;

  constructor() { }

  ngOnInit() {
    this.latestImgPath = environment.hostUrlBase +"/station-images/"+ this.station.uuid + "/latest.jpg";
  }

  touchUpdateTime(lastUpdateTime: Date) {
    this.readingsLastUpdated = lastUpdateTime;
  }

}
