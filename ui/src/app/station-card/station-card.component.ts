import { Component, OnInit, Input, ViewChild, AfterViewInit, Output, EventEmitter } from '@angular/core';
import { environment } from '../../environments/environment';
import { timer } from 'rxjs';

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
        this.updatePic();
        timer(0, 10000).subscribe(() => this.updatePic());
  }

  updatePic(){
    var randomNum: number = Math.floor(Math.random() * 1000000) + 1;
    this.latestImgPath = environment.hostUrlBase + "/station-images/"+ this.station.uuid + "/latest.jpg?rand=" + randomNum;
  }


  touchUpdateTime(lastUpdateTime: Date) {
    this.readingsLastUpdated = lastUpdateTime;
  }

}
