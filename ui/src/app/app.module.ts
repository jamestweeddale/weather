import { BrowserModule } from '@angular/platform-browser';
import { NgModule, NO_ERRORS_SCHEMA } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { MDBBootstrapModule } from 'angular-bootstrap-md';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { StationListComponent } from './station-list/station-list.component';
import { StationCardComponent } from './station-card/station-card.component';
import { ChartingComponent } from './charting/charting.component';
import { StationCardLatestReadingsComponent } from './station-card-latest-readings/station-card-latest-readings.component';
import { KeyValueComponent } from './key-value/key-value.component';
import { ChartModule } from 'angular-highcharts';
import { RouterModule, Routes } from '@angular/router';

const appRoutes: Routes = [
  { path: 'charting', component: ChartingComponent },
  { path: 'current',  component: StationListComponent},
  { path: '', component: StationListComponent }
];

@NgModule({
  declarations: [
    AppComponent,
    StationListComponent,
    StationCardComponent,
    StationCardLatestReadingsComponent,
    KeyValueComponent,
    ChartingComponent
  ],
  imports: [
    RouterModule.forRoot(
      appRoutes,
      { enableTracing: true } // <-- debugging purposes only
    ),
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    MDBBootstrapModule.forRoot(),
    ChartModule
  ],
  schemas: [ NO_ERRORS_SCHEMA ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
