import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Route, Router } from '@angular/router';

@Component({
  selector: 'app-narvbar',
  templateUrl: './narvbar.component.html',
  styleUrls: ['./narvbar.component.css']
})
export class NarvbarComponent {
  constructor(private route: Router) { }
  ngOnInit(): void { }
  @Output() sideNavToggled = new EventEmitter();
  menuStatus: boolean = false;

  sideNavToggle() {
    this.menuStatus = !this.menuStatus;
    this.sideNavToggled.emit(this.menuStatus);
  }
  alert() {
    if (confirm('êtes-vous sûr de vous déconnecter ?')) {
      this.route.navigate(['/login']);
    }
  }
  searchQuery: string = '';
  isLoading: boolean = false;

  onSubmit() {
    //listparticipant
    if (this.searchQuery.toLowerCase().indexOf('listparticipant') >= 0) {
      this.isLoading = true;
      this.route.navigate(['/ListParticipant']).then(() => {
        this.isLoading = false;
      });
    }
    //formateur
    else if (this.searchQuery.toLowerCase().indexOf('presence') >= 0) {
      this.isLoading = true;
      this.route.navigate(['/Presence']).then(() => {
        this.isLoading = false;
      });
    }
    else if (this.searchQuery.toLowerCase().indexOf('formateur') >= 0) {
      this.isLoading = true;
      this.route.navigate(['/formateur']).then(() => {
        this.isLoading = false;
      });
    }
    //profile
    else if (this.searchQuery.toLowerCase().indexOf('profile') >= 0) {
      this.isLoading = true;
      this.route.navigate(['/profile']).then(() => {
        this.isLoading = false;
      });
    }
    //formulaire
    else if (this.searchQuery.toLowerCase().indexOf('formulaire') >= 0) {
      this.isLoading = true;
      this.route.navigate(['/formulaire']).then(() => {
        this.isLoading = false;
      });
    }
    else if (this.searchQuery.toLowerCase().indexOf('welcome') >= 0) {
      this.isLoading = true;
      this.route.navigate(['/Welcome']).then(() => {
        this.isLoading = false;
      });
    }
  }


  on_click() {
    this.route.navigate(['/profile']);
  }

}
