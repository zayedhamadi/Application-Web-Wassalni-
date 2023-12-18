import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { Edit } from 'src/app/model/edit-user';
import { Voiture } from 'src/app/model/voiture';
import { VoitureService } from 'src/app/service/VoitureService/voiture.service';
import { AdminServiceService } from 'src/app/service/adminService/admin-service.service';
import { UserServiceService } from 'src/app/service/userService/user-service.service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent {
  constructor(private router: Router, private Adminservice: AdminServiceService, private voitureService: VoitureService, private toast: NgToastService, private service: UserServiceService) {
  }
  voitures: Voiture[] = [];
  conductor: Edit[] = [];
  edit: Edit = new Edit;
  editUser: any = {};
  ngOnInit(): void {
    const storedUserData = sessionStorage.getItem('authResponse');

    if (storedUserData) {
      this.editUser = JSON.parse(storedUserData);
      this.edit.id = this.editUser.idConductor;

    } else {

      this.router.navigate(['/login']);
    }
  }

  updateUser(): void {
    const sessionData = sessionStorage.getItem('authResponse');
    if (sessionData) {


      const sessionObj = JSON.parse(sessionData);
      this.edit.id = this.editUser.idConductor;
    }
    this.service.updateConductor(this.edit).subscribe(
      (data) => {

        console.log('User updated successfully:', data);
        sessionStorage.setItem('authResponse', JSON.stringify(data));
        console.log(data);

        this.showSuccess();
      },
      (error) => {
        this.showError();
        console.log(error);

        console.log(this.edit);


      });
  }

  getLastPartOfPath(path: string | null): string {
    if (path) {
      const parts = path.split('\\');
      return parts[parts.length - 1];
    }
    return '';
  }




  showSuccess() {
    this.toast.success({ detail: 'SUCCESS!', summary: 'Update User Successfully ', duration: 3500 });
  }

  showError() {
    this.toast.error({ detail: 'ERROR!', summary: 'Can t update User  ', sticky: true, duration: 3500 });
  }

  showInfo() {
    this.toast.info({ detail: 'Info', summary: 'You need to login first !', duration: 3500 })
  }

  showSuccessDelete() {
    this.toast.success({ detail: 'SUCCESS!', summary: 'Delete User Successfully ', duration: 3500 });
  }
  showErrorDelete() {
    this.toast.error({ detail: 'ERROR!', summary: 'Can t delete User  ', sticky: true, duration: 3500 });
  }
  delete() {

    const sessionData = sessionStorage.getItem('authResponse');
    if (sessionData) {
      const sessionObj = JSON.parse(sessionData);
      if (sessionObj.idConductor != undefined) {
        this.edit.id = sessionObj.idConductor;
        console.log(this.edit.id);
        this.service.deleteConductor(this.edit.id)
          .subscribe(
            (data) => {
              console.log(data);
              this.showSuccessDelete()
              sessionStorage.clear();
              this.router.navigate(['/login']);
            },
            (error) => {
              console.log(error);
              this.showErrorDelete();
            }
          );
      } else {
        this.showInfo();
        console.error('idConductor non trouvé dans les données de session.');
      }
    } else {
      this.showInfo();
      console.error('idConductor non trouvé dans les données de session.');
    }
  }


}