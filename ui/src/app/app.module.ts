import { BrowserModule } from '@angular/platform-browser';
import { NgModule, NO_ERRORS_SCHEMA } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { MDBBootstrapModule } from 'angular-bootstrap-md';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { StationListComponent } from './station-list/station-list.component';
import { StationCardComponent } from './station-card/station-card.component';
import { StationCardLatestReadingsComponent } from './station-card-latest-readings/station-card-latest-readings.component';
import { KeyValueComponent } from './key-value/key-value.component';

@NgModule({
  declarations: [
    AppComponent,
    StationListComponent,
    StationCardComponent,
    StationCardLatestReadingsComponent,
    KeyValueComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    MDBBootstrapModule.forRoot()
  ],
  schemas: [ NO_ERRORS_SCHEMA ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
