import { Component, OnInit } from '@angular/core';
import { ReadingService } from '../../reading-service/reading.service';
import { StationService } from 'src/app/station-service/station.service';

@Component({
  selector: 'weather-chart-control-form',
  templateUrl: './control-form.component.html',
  styleUrls: ['./control-form.component.css']
})
export class ControlFormComponent implements OnInit {

 readingKeys: Array<ReadingKey>;
 stations: Array<Station>;
 showDateRange:boolean = false;

 constructor(private readingService: ReadingService, private stationService: StationService) { }

  ngOnInit() {
    this.readingService.getAllReadingKeys().subscribe(readingKeys => {
      this.readingKeys = readingKeys;
    });

    this.stationService.getAll().subscribe(stations => {
      this.stations = stations;
    });
  }

  toggleDateRange(value:string){
    if(value == 'specific'){
     this.showDateRange = true;
    }else{
      this.showDateRange = false;
    }
  }

}
