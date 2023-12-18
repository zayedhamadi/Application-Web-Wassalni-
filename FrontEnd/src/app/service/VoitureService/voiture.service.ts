import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import { Voiture } from 'src/app/model/voiture';
@Injectable({
  providedIn: 'root'
})
export class VoitureService {
  private readonly PUBLICATIONAPI = "http://localhost:8081/api/user/";


  constructor(private router: Router, private http: HttpClient) { }
  public publier(v: Voiture): Observable<any> {

    return this.http.post(this.PUBLICATIONAPI + 'ajouterVoiture', v);
  }


  public getlistVoiture(): Observable<any> {

    return this.http.get(this.PUBLICATIONAPI + 'listVoiture');
  }

  public getVoitureByID(id: number): Observable<any> {
    return this.http.get<any[]>(this.PUBLICATIONAPI + "carNames/" + id);
  }

  public DeleteVoiture(c: number): Observable<any> {
    return this.http.delete(this.PUBLICATIONAPI + 'deleteVoitureById/' + c);
  }

  /* public updateConductor(c: Voiture): Observable<any> {
     return this.http.put(this.PUBLICATIONAPI + 'updateVoiture', c);
   }*/


  public updateVoiture(Voiture: any): Observable<any> {
    return this.http.put(this.PUBLICATIONAPI + "updateVoiture", Voiture);
  }

}
