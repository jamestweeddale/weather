import { Component, OnInit, Input, Output} from '@angular/core';
import { ReadingService } from '../reading-service/reading.service';
import { updateBinding } from '@angular/core/src/render3/instructions';
import { timer } from 'rxjs';

@Component({
  selector: 'reading-key-value',
  templateUrl: './key-value.component.html',
  styleUrls: ['./key-value.component.css']
})
export class KeyValueComponent implements OnInit {

  @Input() readingKey: ReadingKey;
  @Input() stationId: number;
  value: Reading;

  constructor(private readingService: ReadingService) { }

  ngOnInit() {
    timer(0, 10000)
    .subscribe(() => this.update())
  }

  update(){
    this.readingService.getLatestValueFor(this.stationId, this.readingKey.id).subscribe(val => {
      this.value = val;
    });
  }

}
