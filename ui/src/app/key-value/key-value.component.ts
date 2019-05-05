import { Component, OnInit, Input, Output} from '@angular/core';
import { ReadingService } from '../reading-service/reading.service';
import { timer } from 'rxjs';
import { UnitConverionService } from '../unit-conversion-service/unit-converion.service';

@Component({
  selector: 'reading-key-value',
  templateUrl: './key-value.component.html',
  styleUrls: ['./key-value.component.css']
})
export class KeyValueComponent implements OnInit {

  @Input() readingKey: ReadingKey;
  @Input() stationId: number;
  value: Reading;

  constructor(private readingService: ReadingService, private unitConversionService: UnitConverionService) { }

  ngOnInit() {
    timer(0, 10000)
    .subscribe(() => this.update())
  }

  update(){
    this.readingService.getLatestValueFor(this.stationId, this.readingKey.id).subscribe(reading => {
      if(reading != null){
        reading = this.unitConversionService.convert(reading);
      }
      this.value = reading;
    });
  }

}
