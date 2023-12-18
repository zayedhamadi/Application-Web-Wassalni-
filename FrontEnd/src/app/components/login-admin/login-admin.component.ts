import { login } from './../../model/login';
import { Component } from '@angular/core';
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

import { AdminServiceService } from 'src/app/service/adminService/admin-service.service';

@Component({
  selector: 'app-login-admin',
  templateUrl: './login-admin.component.html',
  styleUrls: ['./login-admin.component.css']
})
export class LoginAdminComponent {

  Userlogin = {
    email: '',
    password: ''
  };
  showForm: boolean = false;
  emailControl = new FormControl('', [Validators.required, Validators.email]);
  passwordControl = new FormControl('', [Validators.required]);

  constructor(private router: Router,
    private toast: NgToastService,
    private FormBuilder: FormBuilder,
    private service: AdminServiceService) { }
  validationForm!: FormGroup;
  showSuccess() {
    this.toast.success({ detail: 'SUCCESS', summary: 'login is  successfull', sticky: true, duration: 60000000000000 });
  }
  rise() {
    this.showForm = true;
  }

  showError() {
    this.toast.error({ detail: 'ERROR', summary: 'login failed ', sticky: true, duration: 60000000000000 });
  }
  ngOnInit(): void {

  }


  userLogin() {
    this.service.loginAdmin(this.Userlogin)
      .subscribe(
        (data) => {
          if (data && data.message === 'Success') {
            console.log(data);
            this.showSuccess()
            sessionStorage.setItem('authResponse', JSON.stringify(data));
            this.router.navigate(['/editAdmin']);
          } else {
            this.showError()

            console.error('Login failed! Check Your Email and Password', data);
          }
        },
        (error) => {
          alert('Login failed');
          console.error('Login error:', error);
        }
      );
  }

}







/*  userLogin() {
    console.log(this.Userlogin);
    this.service.loginCustomer(this.Userlogin)
      .subscribe(
        (data) => {
          console.log(data);
          //  sessionStorage.setItem('authResponse', JSON.stringify(data));
 
 
          alert('Login successful');
 
        },
        (error) => {
          alert('Login failed');
          console.log(this.Userlogin.email);
          console.log(this.Userlogin.password);
          console.error('Login error:', error);
 
        }
      );
  }*/


