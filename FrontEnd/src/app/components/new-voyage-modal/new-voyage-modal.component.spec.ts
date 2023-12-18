import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewVoyageModalComponent } from './new-voyage-modal.component';

describe('NewVoyageModalComponent', () => {
  let component: NewVoyageModalComponent;
  let fixture: ComponentFixture<NewVoyageModalComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [NewVoyageModalComponent]
    });
    fixture = TestBed.createComponent(NewVoyageModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
