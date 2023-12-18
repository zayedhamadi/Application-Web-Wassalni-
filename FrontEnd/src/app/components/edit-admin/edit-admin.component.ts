import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { ToastModule } from 'primeng/toast';
import { ButtonModule } from 'primeng/button';
import { PrimeNGConfig } from 'primeng/api';
import {
  FormBuilder,
  ReactiveFormsModule,
  Validators,
  FormGroup,
  FormControl,
} from '@angular/forms';
import { AdminServiceService } from 'src/app/service/adminService/admin-service.service';
import { SignUp } from 'src/app/model/signup';
@Component({
  selector: 'app-edit-admin',
  templateUrl: './edit-admin.component.html',
  styleUrls: ['./edit-admin.component.css']
})
export class EditAdminComponent {


  constructor(private primengConfig: PrimeNGConfig, private toast: NgToastService, private router: Router, public FormBuilder: FormBuilder, private service: AdminServiceService) { }

  validation!: FormGroup;

  ngOnInit() {
    this.primengConfig.ripple = true;
  }

  visible: boolean = true;
  changetype: boolean = true;

  view() {
    this.visible = !this.visible;
    this.changetype = !this.changetype;
  }
  signup: SignUp = new SignUp;

  showSuccess() {
    this.toast.success({ detail: 'SUCCESS!', summary: 'Admin added successfully ', duration: 3500 });
  }

  showError() {
    this.toast.error({ detail: 'ERROR!', summary: 'Email or password is invalid  ', sticky: true, duration: 3500 });
  }

  showInfo() {
    this.toast.info({ detail: 'Info', summary: 'EMAIL ALREADY IN USE !', duration: 3500 })
  }

  register(registerform: any) {
    let response = this.service.signupAdmin(registerform.value);

    console.log(response);
    response.subscribe(
      (data) => {

        console.log(this.signup.fullname);

        console.log(data);
        sessionStorage.setItem('authResponse', JSON.stringify(data));
        this.showSuccess()



      }, (err) => {
        this.showError();
        console.log(err);
        if (err.status === 406) {
          this.showInfo();
        }
      }
    );
  }
  /* register(registerform: any) {
  
       let response = this.service.signupCustomer(registerform.value);
 
       console.log(response);
       response.subscribe(
         (data) => {
           console.log(data);
           sessionStorage.setItem('authResponse', JSON.stringify(data));
           this.showSuccess();
           this.router.navigate(['/voiture']);
         },
         (err) => {
           this.showError();
           console.log(err);
           if (err.status === 406) {
             this.showInfo();
           }
         
       );
     } else {
       this.toast.error({ detail: 'Passwords do not match!', summary: 'Error', duration: 3500 });
     }
   }
 */
}

