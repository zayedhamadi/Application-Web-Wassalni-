import { Component } from '@angular/core';
import { NgToastService } from 'ng-angular-popup';
import { ToastrService } from 'ngx-toastr';
import { Voiture } from 'src/app/model/voiture';
import { AdminServiceService } from 'src/app/service/adminService/admin-service.service';

@Component({
  selector: 'app-liste-voitures',
  templateUrl: './liste-voitures.component.html',
  styleUrls: ['./liste-voitures.component.css']
})
export class ListeVoituresComponent {
  id: any;
  car: Voiture = new Voiture();
  voiture: Voiture[] = [];
  constructor(
    private toastr: ToastrService,
    private toast: NgToastService,
    private service: AdminServiceService
  ) { }
  ngOnInit(): void {
    this.getConductors();
  }
  showError() {
    this.toast.error({ detail: 'ERROR', summary: 'Can t get cars  ', sticky: true, duration: 2500 });
  }
  showSuccess() {
    this.toast.success({ detail: 'SUCCESS', summary: 'get cars  successfull', sticky: true, duration: 2500 });
  }
  getConductors() {
    this.service.getListCarsByAdmin().subscribe((data) => {
      this.voiture = data;
      console.log(data);

      this.showSuccess();

    },
      (error) => {
        this.showError();
        console.error('Login error:', error);
      }
    );
  }



  deleteEmployee(id: number) {
    this.toastr.warning('Are you sure to delete this car ?', 'Confirmation', {
      closeButton: true,
      timeOut: 5000
    }).onTap.subscribe(() => {
      this.service.DeleteVoitureByAdmin(id).subscribe(
        () => {
          this.toastr.success('Car deleted successfully.', 'Success');
          this.voiture = this.voiture.filter(car => car.idVoiture !== id);
        },
        (error) => {
          this.showError();
          console.error(error);
        }
      );
    });
  }

}
