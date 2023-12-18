import { Component, EventEmitter, Output } from '@angular/core';
import { Publication } from 'src/app/model/publication';
import { Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { ToastModule } from 'primeng/toast';
import { ButtonModule } from 'primeng/button';

import {
  FormBuilder,
  ReactiveFormsModule,
  Validators,
  FormGroup,
  FormControl,
} from '@angular/forms';
import { UserServiceService } from 'src/app/service/userService/user-service.service';
import { PublicationService } from 'src/app/service/PublicationService/publication.service';
import { Voiture } from 'src/app/model/voiture';
import { VoitureService } from 'src/app/service/VoitureService/voiture.service';

@Component({
  selector: 'app-new-voyage-modal',
  templateUrl: './new-voyage-modal.component.html',
  styleUrls: ['./new-voyage-modal.component.css']
})
export class NewVoyageModalComponent {
  showModal: boolean = false;

  // @Output() close = new EventEmitter<void>();
  open() {
    this.showModal = true;
  }

  // close() {
  //  this.showModal = false;
  // this.close.emit();
  // }


  voitures: Voiture[] = [];
  ngOnInit() {
    this.getVoituresForUser();

  }
  showPopup: boolean = false;

  // Méthode pour afficher ou masquer le formulaire
  togglePopup() {
    this.showPopup = !this.showPopup;
  }
  publication: Publication = new Publication();
  villes: string[] = [
    'Ariana', 'Béja', 'Ben_Arous', 'Bizerte', 'Gabès', 'Gafsa', 'Jendouba', 'Kairouan',
    'Kasserine', 'Kébili', 'Kef', 'Mahdia', 'Manouba', 'Médenine', 'Monastir', 'Nabeul',
    'Sfax', 'Sidi_Bouzid', 'Siliana', 'Sousse', 'Tataouine', 'Tozeur', 'Tunis', 'Zaghouan'
  ];
  constructor(private voitureService: VoitureService, private router: Router, private SERVICE: PublicationService, private toast: NgToastService, private service: UserServiceService) {

  }
  userLogout() {
    this.service.logout();
    this.router.navigate(['/signup']);
  }
  showSuccess() {
    this.toast.success({ detail: 'SUCCESS!', summary: 'add publication successfully ', duration: 10000 });
  }

  showError() {
    this.toast.error({ detail: 'ERROR!', summary: 'can t add publciation', sticky: true, duration: 10000 });
  }

  showInfo() {
    this.toast.info({ detail: 'Info', summary: 'select your car or login first !', duration: 3500 })
  }

  selectedCarId: string = '';
  postePublier() {
    const sessionData = sessionStorage.getItem('authResponse');

    if (sessionData) {
      const sessionObj = JSON.parse(sessionData);

      if (sessionObj.idConductor !== undefined && this.selectedCarId) {
        this.publication.idConductor = sessionObj.idConductor;
        this.publication.idVoiture = this.selectedCarId;

        console.log('Publication à publier:', this.publication);

        this.SERVICE.publier(this.publication).subscribe(
          (data) => {
            console.log(data);
            this.showSuccess();

          },
          (error) => {
            console.log(error);
            this.showError();
          }
        );
      } else {
        alert('Veuillez sélectionner une voiture et vous connecter.');
        console.error('idConductor non trouvé dans les données de session.');
      }
    } else {
      alert('Vous devez d\'abord vous connecter.');
      console.error('idConductor non trouvé dans les données de session.');
    }
  }

  getVoituresForUser() {
    const sessionData = sessionStorage.getItem('authResponse');

    if (sessionData) {
      const sessionObj = JSON.parse(sessionData);

      if (sessionObj.idConductor !== undefined) {
        this.voitureService.getVoitureByID(sessionObj.idConductor).subscribe(
          (data: Voiture[]) => {
            this.voitures = data;
            console.log('Voitures récupérées :', this.voitures);
          },
          (error) => {
            console.log(error);
            alert('Erreur lors de la récupération des voitures.');
          }
        );
      }
    }
  }
}
