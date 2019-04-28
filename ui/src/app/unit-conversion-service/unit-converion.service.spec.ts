import { TestBed } from '@angular/core/testing';

import { UnitConverionService } from './unit-converion.service';

describe('UnitConverionService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: UnitConverionService = TestBed.get(UnitConverionService);
    expect(service).toBeTruthy();
  });
});
