import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import { login } from 'src/app/model/login';

@Injectable({
  providedIn: 'root'
})
export class AdminServiceService {
  private readonly APIADMIN = "http://localhost:8081/api/admin/auth/"
  constructor(private http: HttpClient) { }

  public signupAdmin(registerform: any): Observable<any> {
    return this.http.post(this.APIADMIN + "signupAdmin", registerform)
  }
  public loginAdmin(login: login): Observable<any> {

    return this.http.post(this.APIADMIN + 'signinAdmin', login);
  }


  public getAllConductors() {
    return this.http.get<any[]>(this.APIADMIN + 'listConductors');
  }

  public DeleteConductorByAdmin(n: number): Observable<any> {
    return this.http.delete(this.APIADMIN + "deleteCustomerByAdmin/" + n)
  }

  public getListCarsByAdmin(): Observable<any> {
    return this.http.get<any[]>(this.APIADMIN + "getListVoitureByAdmin");
  }

  public DeleteVoitureByAdmin(c: number): Observable<any> {
    return this.http.delete(this.APIADMIN + 'deleteVoitureByAdmin/' + c);
  }

  public getAllPublication(): Observable<any> {
    return this.http.get(this.APIADMIN + "getListPublicationByAdmin");
  }
  public DeletePublicationByAdmin(c: number): Observable<any> {
    return this.http.delete(this.APIADMIN + 'deletePublicationByAdmin/' + c);
  }
}




