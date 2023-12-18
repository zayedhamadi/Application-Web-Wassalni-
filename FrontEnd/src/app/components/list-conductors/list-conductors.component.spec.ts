import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListConductorsComponent } from './list-conductors.component';

describe('ListConductorsComponent', () => {
  let component: ListConductorsComponent;
  let fixture: ComponentFixture<ListConductorsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ListConductorsComponent]
    });
    fixture = TestBed.createComponent(ListConductorsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
