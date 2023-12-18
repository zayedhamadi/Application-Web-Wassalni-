import { listpub } from 'src/app/model/ListPub';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import { Publication } from 'src/app/model/publication';
import { Filter } from 'src/app/model/FilterPub';

@Injectable({
  providedIn: 'root'
})
export class PublicationService {
  private readonly PUBLICATIONAPI = "http://localhost:8081/api/";


  constructor(private router: Router, private http: HttpClient) { }

  public publier(publication: Publication): Observable<any> {
    return this.http.post(this.PUBLICATIONAPI + 'user/publier', publication);
  }

  public getAllPublication(): Observable<any> {
    return this.http.get(this.PUBLICATIONAPI + "pub/Listpub");
  }

  public FilterPub(listpub: any) {
    return this.http.post(this.PUBLICATIONAPI + "pub/filterpub", listpub);
  }

}
