import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FeedbackServiceService {

  private readonly APIADMIN = "http://localhost:8081/api/feedback/"
  constructor(private http: HttpClient) { }

  public ajouterFeddback(registerform: any): Observable<any> {
    return this.http.post(this.APIADMIN + "ajouterFeddback", registerform)
  }
  public DeleteFeddback(n: number): Observable<any> {
    return this.http.delete(this.APIADMIN + "deleteFeddbackById/" + n)
  }

  public getListFeddback(): Observable<any> {
    return this.http.get<any[]>(this.APIADMIN + "getListFeeddback");
  }
}
