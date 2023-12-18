import { AdminServiceService } from 'src/app/service/adminService/admin-service.service';
import { Component } from '@angular/core';
import { NgToastService } from 'ng-angular-popup';
import { ToastrService } from 'ngx-toastr'; import { Publication } from 'src/app/model/publication';
@Component({
  selector: 'app-list-publications',
  templateUrl: './list-publications.component.html',
  styleUrls: ['./list-publications.component.css']
})
export class ListPublicationsComponent {
  id: any;
  status: Publication = new Publication();
  publication: Publication[] = [];
  constructor(
    private toastr: ToastrService,
    private toast: NgToastService,
    private service: AdminServiceService
  ) { }
  ngOnInit(): void {
    this.getConductors();
  }
  showError() {
    this.toast.error({ detail: 'ERROR', summary: 'Can t get Publication conductors ', sticky: true, duration: 1500 });
  }
  showSuccess() {
    this.toast.success({ detail: 'SUCCESS', summary: 'get Publication  successfully', sticky: true, duration: 1500 });
  }
  getConductors() {
    this.service.getAllPublication().subscribe((data) => {
      this.publication = data;
      console.log(data);

      this.showSuccess();

    },
      (error) => {
        this.showError();
        console.error('Login error:', error);
      }
    );
  }


  deletePublication(id: number) {
    this.toastr.warning('Are you sure to delete this publication ?', 'Confirmation', {
      closeButton: true,
      timeOut: 2500
    }).onTap.subscribe(() => {
      this.service.DeletePublicationByAdmin(id).subscribe(
        () => {
          this.toastr.success('Publication deleted successfully.', 'Success');
          this.publication = this.publication.filter(status => this.status.idpub !== id);
        },
        (error) => {
          this.showError();
          console.error(error);
        }
      );
    });
  }



}
