import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StationCardLatestReadingsComponent } from './station-card-latest-readings.component';

describe('StationCardLatestReadingsComponent', () => {
  let component: StationCardLatestReadingsComponent;
  let fixture: ComponentFixture<StationCardLatestReadingsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StationCardLatestReadingsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StationCardLatestReadingsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
