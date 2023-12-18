import { Component, EventEmitter, Output } from '@angular/core';
import { Router } from '@angular/router';
import { filter } from 'rxjs';
import { Filter } from 'src/app/model/FilterPub';
import { listpub } from 'src/app/model/ListPub';
import { PublicationService } from 'src/app/service/PublicationService/publication.service';

@Component({
  selector: 'app-filter-pub',
  templateUrl: './filter-pub.component.html',
  styleUrls: ['./filter-pub.component.css']
})
export class FilterPubComponent {
  @Output() filterChanged: EventEmitter<Filter> = new EventEmitter<Filter>();

  filter = {
    villeDepart: '',
    villeArrive: '',
   // dateDepart: ''
  };



  countries: string[] = ['Ariana', 'Béja', 'Ben_Arous', 'Bizerte', 'Gabès', 'Gafsa', 'Jendouba', 'Kairouan',
    'Kasserine', 'Kébili', 'Kef', 'Mahdia', 'Manouba', 'Médenine', 'Monastir', 'Nabeul',
    'Sfax', 'Sidi_Bouzid', 'Siliana', 'Sousse', 'Tataouine', 'Tozeur', 'Tunis', 'Zaghouan'];
  //declaration dune liste vide pour associer aux resultat de filtrage 
  listPub: listpub[] = [];


  constructor(private pubService: PublicationService,
    private router:Router) { }
  
  onSubmit() {
    console.log(this.filter)
    this.pubService.FilterPub(this.filter).subscribe(
      (data:any) => {
        console.log(data);
        this.listPub = data;
        this.router.navigate(["/publier"])
       
      },
      (error) => {
        console.log(error);
        this.router.navigate(["/publier"])
        
      }
    );
   
   
  }

}
