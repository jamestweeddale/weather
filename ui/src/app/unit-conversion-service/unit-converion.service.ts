import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UnitConverionService {

  constructor() { }

  convert(reading: Reading) : Reading{

    if(reading.units.toLowerCase() == "mps"){
      reading.value = reading.value * 2.236936;
      reading.units = "mph";
    }
    
    return reading;
  }
}
