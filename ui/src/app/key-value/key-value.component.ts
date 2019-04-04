import { Component, OnInit, Input, Output} from '@angular/core';
import { ReadingService } from '../reading-service/reading.service';
import { updateBinding } from '@angular/core/src/render3/instructions';
import { timer } from 'rxjs';
import { EventEmitter } from '@angular/core';

@Component({
  selector: 'reading-key-value',
  templateUrl: './key-value.component.html',
  styleUrls: ['./key-value.component.css']
})
export class KeyValueComponent implements OnInit {

  @Input() readingKey: ReadingKey;
  @Input() stationId: number;
  @Output() lastUpdatedEmitter: EventEmitter<Date> = new EventEmitter<Date>();
  value: Reading;
  lastUpdated: Date;


  constructor(private readingService: ReadingService) { }

  ngOnInit() {
    timer(0, 10000)
    .subscribe(() => this.update())
  }

  update(){
    this.readingService.getLatestValueFor(this.stationId, this.readingKey.id).subscribe(val => {
      this.value = val;
      this.lastUpdated = new Date();
      this.lastUpdatedEmitter.emit(this.lastUpdated);
    });
  }

}
