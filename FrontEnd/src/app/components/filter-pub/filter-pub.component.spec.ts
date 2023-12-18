import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FilterPubComponent } from './filter-pub.component';

describe('FilterPubComponent', () => {
  let component: FilterPubComponent;
  let fixture: ComponentFixture<FilterPubComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FilterPubComponent]
    });
    fixture = TestBed.createComponent(FilterPubComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
