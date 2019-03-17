import { Component, OnInit } from '@angular/core';
import { StationService } from '../weather/station.service';

@Component({
  selector: 'station-list',
  templateUrl: './station-list.component.html',
  styleUrls: ['./station-list.component.css']
})
export class StationListComponent implements OnInit {

  stations: Array<any>;

  constructor(private stationService: StationService) { }

 ngOnInit() {
    this.stationService.getAll().subscribe(data => {
        console.log("test");
      this.stations = data;
    });
  }

}
