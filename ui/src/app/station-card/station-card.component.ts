import { Component, OnInit, Input, ViewChild, AfterViewInit, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'station-card',
  templateUrl: './station-card.component.html',
  styleUrls: ['./station-card.component.css']
})
export class StationCardComponent implements OnInit {

  @Input() station: Station;
  readingsLastUpdated:Date = new Date();
  @Output() lastUpdatedEmitter:EventEmitter<Date> = new EventEmitter<Date>();

  constructor() { }

  ngOnInit() {
  }

  touchUpdateTime(lastUpdateTime:Date){
    this.readingsLastUpdated = lastUpdateTime;
  }
}
