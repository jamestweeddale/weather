import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { ReadingService } from '../reading-service/reading.service';
import { forEach } from '@angular/router/src/utils/collection';

@Component({
  selector: 'station-card-latest-readings',
  templateUrl: './station-card-latest-readings.component.html',
  styleUrls: ['./station-card-latest-readings.component.css']
})
export class StationCardLatestReadingsComponent implements OnInit {

  @Input() stationId: number;
  readingKeys: Array<ReadingKey>;
  @Output() lastUpdatedEmitter:EventEmitter<Date> = new EventEmitter<Date>();

  constructor(private readingService: ReadingService) { }

  ngOnInit() {
    this.readingService.getReadingKeysForStation(this.stationId).subscribe(readingKeys => {
      this.readingKeys = readingKeys;
    });
  }

  fireLastUpdated(lastUpdated:Date){
    this.lastUpdatedEmitter.emit(new Date());
  }
}
