import { login } from './../../model/login';
import { Component, OnInit, Input, Output } from '@angular/core';
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
import { Feedback } from 'src/app/model/contact';
import { FeedbackServiceService } from 'src/app/service/FeeddBackService/feedback-service.service';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent {
  Userlogin = {
    commantaire: '',
    emailUser: ''
  };
  feedback: Feedback = new Feedback();
  showForm: boolean = false;
  emailControl = new FormControl('', [Validators.required, Validators.email]);
  passwordControl = new FormControl('', [Validators.required]);

  constructor(private router: Router,
    private toast: NgToastService,
    private FormBuilder: FormBuilder,
    private service: FeedbackServiceService) { }

  validationForm!: FormGroup;
  showSuccess() {
    this.toast.success({ detail: 'SUCCESS', summary: 'Send Feedbacl    successfully', sticky: true, duration: 2500 });
  }
  register() {
    this.showForm = true;
  }

  showError() {
    this.toast.error({ detail: 'ERROR', summary: 'Feedback failed ', sticky: true, duration: 2500 });
  }
  ngOnInit(): void {

  }


  userLogin() {
    this.service.ajouterFeddback(this.feedback)
      .subscribe(
        (data) => {

          console.log(data);
          this.showSuccess()

          this.router.navigate(['/home']);

        },
        (error) => {
          this.showError();
          console.error(error);
        }
      );
  }

}
