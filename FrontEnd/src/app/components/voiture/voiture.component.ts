import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { Voiture } from 'src/app/model/voiture';
import { UserServiceService } from 'src/app/service/userService/user-service.service';
import { VoitureService } from 'src/app/service/VoitureService/voiture.service';


@Component({
  selector: 'app-voiture',
  templateUrl: './voiture.component.html',
  styleUrls: ['./voiture.component.css']
})
export class VoitureComponent {
  voitures: Voiture[] = [];
  voiture: Voiture = new Voiture();
  constructor(private a: NgToastService, private voitureService: VoitureService, private router: Router, private SERVICE: VoitureService, private toast: NgToastService, private service: UserServiceService) { }

  showSuccess() {
    this.a.success({ detail: 'SUCCESS!', summary: ' Car Added   successfully ', duration: 350000 });
  }

  showError() {
    this.a.error({ detail: 'ERROR!', summary: 'Error in adding car   ', sticky: true, duration: 650000 });
  }

  showInfo() {
    this.a.info({ detail: 'Info', summary: 'you must login first !', duration: 650000 })
  }

  postePublier() {
    const sessionData = sessionStorage.getItem('authResponse');

    if (sessionData) {
      const sessionObj = JSON.parse(sessionData);

      if (sessionObj.idConductor !== undefined) {
        this.voiture.idConductor = sessionObj.idConductor;

        console.log('Publication à publier:', this.voiture);

        this.SERVICE.publier(this.voiture).subscribe(
          (data) => {
            console.log(data);

            this.showSuccess()
            this.router.navigate(['/publier'])
          },
          (error) => {
            console.log(error);

            this.showError()
          }
        );
      }
    } else {

      this.showInfo()
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
