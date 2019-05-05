import { Component, OnInit, Input } from '@angular/core';
import { timer } from 'rxjs';
import { ReadingService } from '../reading-service/reading.service';

@Component({
  selector: 'station-card',
  templateUrl: './station-card.component.html',
  styleUrls: ['./station-card.component.css']
})
export class StationCardComponent implements OnInit {

  @Input() station: Station;
  stationLastReportedMinsAgo: number = 0;
  latestImgPath: string = "";

  constructor(private readingService: ReadingService) { }

  ngOnInit() {
    timer(0, 600000).subscribe(() => this.updatePic());
    timer(0, 300000).subscribe(() => this.updateMinsAgo());
  }

  updatePic() {
    var randomNum: number = Math.floor(Math.random() * 1000000) + 1;
    this.latestImgPath = "/station-images/" + this.station.uuid + "/latest.jpg?rand=" + randomNum;
  }

  updateMinsAgo() {
    this.readingService.getLastReading(this.station.id).subscribe((lastReading) => {
      if (lastReading != null) {
        let diffMs = Date.now().valueOf() - Date.parse(lastReading.time.toString());
        this.stationLastReportedMinsAgo = Math.floor((diffMs/1000)/60);
      }
    });
  }
}
