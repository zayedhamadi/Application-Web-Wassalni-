import { AdminServiceService } from 'src/app/service/adminService/admin-service.service';
import { Component } from '@angular/core';
import { NgToastService } from 'ng-angular-popup';
import { Edit } from 'src/app/model/edit-user';
import { ToastrService } from 'ngx-toastr';
@Component({
  selector: 'app-list-conductors',
  templateUrl: './list-conductors.component.html',
  styleUrls: ['./list-conductors.component.css']
})
export class ListConductorsComponent {
  id: any;
  user: Edit = new Edit();
  conductor: Edit[] = [];
  constructor(
    private toastr: ToastrService,
    private toast: NgToastService,
    private service: AdminServiceService
  ) { }
  ngOnInit(): void {
    this.getConductors();
  }
  showError() {
    this.toast.error({ detail: 'ERROR', summary: 'Can t get Conductors conductors ', sticky: true, duration: 2500 });
  }
  showSuccess() {
    this.toast.success({ detail: 'SUCCESS', summary: 'get Conductors  successfully', sticky: true, duration: 2500 });
  }
  getConductors() {
    this.service.getAllConductors().subscribe((data) => {
      this.conductor = data;
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
    this.toastr.warning('Are you sure to delete this conductor ?', 'Confirmation', {
      closeButton: true,
      timeOut: 5000
    }).onTap.subscribe(() => {
      this.service.DeleteConductorByAdmin(id).subscribe(
        () => {
          this.toastr.success('Conductor deleted successfully.', 'Success');
          this.conductor = this.conductor.filter(user => user.idConductor !== id);
        },
        (error) => {
          this.showError();
          console.error(error);
        }
      );
    });
  }
}