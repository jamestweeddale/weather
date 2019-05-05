import { Component, OnInit, Input } from '@angular/core';
import { ReadingService } from '../reading-service/reading.service';

@Component({
  selector: 'station-card-latest-readings',
  templateUrl: './station-card-latest-readings.component.html',
  styleUrls: ['./station-card-latest-readings.component.css']
})
export class StationCardLatestReadingsComponent implements OnInit {

  @Input() stationId: number;
  readingKeys: Array<ReadingKey>;

  constructor(private readingService: ReadingService) { }

  ngOnInit() {
    this.readingService.getReadingKeysForStation(this.stationId).subscribe(readingKeys => {
      this.readingKeys = readingKeys;
    });
  }

}
