import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditVoitureComponent } from './edit-voiture.component';

describe('EditVoitureComponent', () => {
  let component: EditVoitureComponent;
  let fixture: ComponentFixture<EditVoitureComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EditVoitureComponent]
    });
    fixture = TestBed.createComponent(EditVoitureComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
