import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Voiture } from 'src/app/model/voiture';
import { VoitureService } from 'src/app/service/VoitureService/voiture.service';

@Component({
  selector: 'app-edit-voiture',
  templateUrl: './edit-voiture.component.html',
  styleUrls: ['./edit-voiture.component.css']
})
export class EditVoitureComponent implements OnInit {
  voiture: Voiture = new Voiture();
  voitures: Voiture[] = [];
  editedCar: Voiture = new Voiture();

  constructor(private router: Router, private voitureService: VoitureService, private toastr: ToastrService,) { }

  ngOnInit() {
    this.getVoiture();
  }
  getVoiture() {
    const sessionData = sessionStorage.getItem('authResponse');

    if (sessionData) {
      const sessionObj = JSON.parse(sessionData);

      if (sessionObj.idConductor !== undefined) {
        const idConductor = sessionObj.idConductor;

        this.voitureService.getVoitureByID(idConductor).subscribe(
          (data: Voiture[]) => {
            if (data && data.length > 0) {


              this.voitures = data;

              console.log('Voitures récupérées :', this.voitures);
            } else {
              console.log('Aucune voiture trouvée pour cet utilisateur.');
            }
          },
          (error) => {
            console.log(error);
            alert('Erreur lors de la récupération des informations sur la voiture.');
          }
        );
      }
    }
  }

  deleteVoiture(idVoiture: number) {
    this.toastr.warning('Are you sure to delete this car ?', 'Confirmation', {
      closeButton: true,
      timeOut: 5000
    }).onTap.subscribe(() => {
      this.voitureService.DeleteVoiture(idVoiture).subscribe(
        () => {
          this.toastr.success('Car deleted successfully.', 'Succès');
          this.voitures = this.voitures.filter(voiture => voiture.idVoiture !== idVoiture);
        },
        (error) => {
          console.log(error);
          this.toastr.error('Sorry! can t delete this car ', 'Erreur');
        }
      );
    });
  }

  fillFormWithCarDetails(car: Voiture) {
    this.voiture.idVoiture = car.idVoiture;
    this.voiture.marqueVoiture = car.marqueVoiture;
    this.voiture.descriptionVoiture = car.descriptionVoiture;
  }

  /* updateVoiture() {
     this.voitureService.updateVoiture(this.voiture).subscribe(
       (response) => {
         this.toastr.success('Car details updated successfully.', 'Success');
         this.voiture = new Voiture();
         this.getVoiture();
       },
       (error) => {
         console.error('Error updating car details:', error);
         this.toastr.error('Failed to update car details.', 'Error');
       }
     );
   }*/



  updateVoiture() {
    const sessionData = sessionStorage.getItem('authResponse');
    if (sessionData) {
      const sessionObj = JSON.parse(sessionData);
      if (sessionObj.idConductor !== undefined) {
        this.voiture.idConductor = sessionObj.idConductor;


        this.voitureService.updateVoiture(this.voiture).subscribe(
          (data) => {
            console.log(data);
            this.toastr.success('Car  updated successfully.', 'Success');
            this.getVoiture();
          },
          (error) => {
            console.log(error);
            this.toastr.error('Failed ');
          }
        );
      } else {
        this.toastr.error('Failed to update car .', 'Error');
        console.error('idConductor non trouvé dans les données de session.');
      }
    }
    else {
      this.toastr.info('Failed to update car details.', 'Error');
      console.error('idConductor non trouvé dans les données de session.');
    }
  }
}