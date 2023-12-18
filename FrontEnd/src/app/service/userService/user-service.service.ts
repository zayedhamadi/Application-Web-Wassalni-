import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import { login } from 'src/app/model/login';
import { Edit } from 'src/app/model/edit-user';

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  constructor(private router: Router, private http: HttpClient) { }
  private readonly AuthAPI = "http://localhost:8081/api/auth/";


  signupCustomer(registerform: any): Observable<any> {
    return this.http.post(this.AuthAPI + "signupConductor", registerform)
  }

  public loginCustomer(login: login): Observable<any> {

    return this.http.post(this.AuthAPI + 'signinConductor', login);
  }

  logout() {
    sessionStorage.removeItem('authResponse');
  }

  isLoggedIn(): boolean {
    return !!sessionStorage.getItem('authResponse');
  }

  CONDUCTORAPI = "http://localhost:8081/api/user/";
  public getConductorByID(id: number): Observable<any> {
    return this.http.get<any>(this.CONDUCTORAPI + "specificConductor/" + id);
  }






  public updateConductor(c: Edit): Observable<any> {
    return this.http.put(this.CONDUCTORAPI + 'updateConductor', c);
  }


  public deleteConductor(c: number): Observable<any> {
    return this.http.delete(this.CONDUCTORAPI + 'deleteCustomer/' + c);
  }


  public Reserver(Reserve: any): Observable<any> {
    return this.http.post(this.CONDUCTORAPI + "Reserve", Reserve)
  }

}
