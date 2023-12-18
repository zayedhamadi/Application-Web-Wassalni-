import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { ToastrService } from 'ngx-toastr';
import { Feedback } from 'src/app/model/contact';
import { FeedbackServiceService } from 'src/app/service/FeeddBackService/feedback-service.service';

@Component({
  selector: 'app-edit-contact',
  templateUrl: './edit-contact.component.html',
  styleUrls: ['./edit-contact.component.css']
})
export class EditContactComponent {
  feed: Feedback[] = [];
  user: Feedback = new Feedback();


  showSuccess() {
    this.toast.success({ detail: 'SUCCESS', summary: 'getFeedback  successfully', sticky: true, duration: 2100 });
  }

  showError() {
    this.toast.error({ detail: 'ERROR', summary: 'getListFeedback failed ', sticky: true, duration: 2100 });
  }
  constructor(private router: Router, private service: FeedbackServiceService, private toast: NgToastService, private toastr: ToastrService,) { }

  ngOnInit() {
    this.getFeddbacks();
  }


  getFeddbacks() {

    let response = this.service.getListFeddback();
    response.subscribe((data) => {
      this.feed = data;
      this.showSuccess()

    },
      (Error) => {
        this.showError();

        console.error(Error);
      })
  }


  id: any;
  deleteFeedback(id: any) {

    this.toastr.warning('Are you sure to delete this car ?', 'Confirmation', {
      closeButton: true,
      timeOut: 5000
    }).onTap.subscribe(() => {
      this.service.DeleteFeddback(id).subscribe(
        () => {
          this.toastr.success('Car deleted successfully.', 'Success');
          this.feed = this.feed.filter(user => user.idFeedback !== id);
        },
        (error) => {
          this.showError();
          console.error(error);
        }
      );
    });
  }
}


