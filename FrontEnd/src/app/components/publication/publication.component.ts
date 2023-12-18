import { Component } from '@angular/core';
import { Publication } from 'src/app/model/publication';
import { Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { UserServiceService } from 'src/app/service/userService/user-service.service';
import { PublicationService } from 'src/app/service/PublicationService/publication.service';
import { Voiture } from 'src/app/model/voiture';
import { VoitureService } from 'src/app/service/VoitureService/voiture.service';
import { listpub } from 'src/app/model/ListPub';
import { Reserver } from 'src/app/model/Reserver';
@Component({
  selector: 'app-publication',
  templateUrl: './publication.component.html',
  styleUrls: ['./publication.component.css']
})
export class PublicationComponent {
  aa: any;
  voitures: Voiture[] = [];
  listPub: listpub[] = [];
  list: any;
  showModal = false;
  publication: Publication = new Publication();
  reserve: Reserver = new Reserver();
  villes: string[] = [
    'Ariana', 'Béja', 'Ben_Arous', 'Bizerte', 'Gabès', 'Gafsa', 'Jendouba', 'Kairouan',
    'Kasserine', 'Kébili', 'Kef', 'Mahdia', 'Manouba', 'Médenine', 'Monastir', 'Nabeul',
    'Sfax', 'Sidi_Bouzid', 'Siliana', 'Sousse', 'Tataouine', 'Tozeur', 'Tunis', 'Zaghouan'
  ];
  filters = {
    villeDepart: '',
    villeArrive: '',
    // dateDepart: ''
  };

  constructor(
    private voitureService: VoitureService,
    private router: Router,
    private SERVICE: PublicationService,
    private toast: NgToastService,
    private service: UserServiceService) {

  }


  ngOnInit() {
    this.getVoituresForUser();
    this.getListPub();
    this.onSubmit()
  }



  openNewVoyageModal() {
    this.showModal = true; // Show the modal
  }

  userLogout() {
    this.service.logout();
    this.router.navigate(['/signup']);
  }
  onSubmit() {
    console.log(this.filters)
    this.SERVICE.FilterPub(this.filters).subscribe(
      (data: any) => {
        console.log(data);
        this.listPub = data;

      },
      (error) => {
        console.log(error);

      }
    );
  }

  showSuccess() {
    this.toast.success({ detail: 'SUCCESS!', summary: 'add publication successfully ', duration: 5000 });
  }

  showError() {
    this.toast.error({ detail: 'ERROR!', summary: 'can t add publciation', sticky: true, duration: 3500 });
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


  getListPub() {
    this.SERVICE.getAllPublication().subscribe(
      (data) => {

        this.listPub = data;
        console.log(this.listPub);

      },
      (error) => {
        console.log(error);
      }
    )
  }
  showSidebar = false;

  toggleSidebar() {
    this.showSidebar = !this.showSidebar;
  }


  reserverPublication(idPublication: number) {
    const sessionData = sessionStorage.getItem('authResponse');

    if (sessionData) {
      const sessionObj = JSON.parse(sessionData);
      if (sessionObj.idConductor !== undefined) {
        this.reserve.idConductor = sessionObj.idConductor;

        const reserveData = { idPublication: idPublication, idConductor: this.reserve.idConductor };

        this.service.Reserver(reserveData).subscribe(
          (response) => {
            console.log('Publication réservée avec succès !');
            console.log(response);
            console.log(reserveData);


          },
          (error) => {
            console.error('Erreur lors de la réservation de la publication :', error);
          }
        );
      }
    }
  }
}

