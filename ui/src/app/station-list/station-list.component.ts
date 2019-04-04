import { Component, OnInit } from '@angular/core';
import { StationService } from '../station-service/station.service';

@Component({
  selector: 'station-list',
  templateUrl: './station-list.component.html',
  styleUrls: ['./station-list.component.css']
})
export class StationListComponent implements OnInit {

  stations: Array<Station>;

  constructor(private stationService: StationService) { }

 ngOnInit() {
    this.stationService.getAll().subscribe(stations => {
      this.stations = stations;
    });
  }

}
