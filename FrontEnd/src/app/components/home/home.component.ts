import { Component } from '@angular/core';
import { Publication } from 'src/app/model/publication';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  showSuccessAlert: boolean | undefined = false;
  public onUpdatePub(pub: Publication): void {
    console.log('Modifier button clicked');
  }

  showForm: boolean = false;

  toggleForm() {
    this.showForm = !this.showForm;
  }


  public onOpenModal(pub: Publication | null, mode: string): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'reserver') {
      button.setAttribute('data-target', '#reservationPubModal');
    }
    if (mode === 'edit') {
      button.setAttribute('data-target', '#updatePubModal');
    }
    container!.appendChild(button);
    button.click();
  }

}
