import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { SignupComponent } from './components/signup/signup.component';
import { HomeComponent } from './components/home/home.component';
import { FooterComponent } from './components/footer/footer.component';
import { ContactComponent } from './components/contact/contact.component';
import { UserProfileComponent } from './components/user-profile/user-profile.component';
import { PublicationComponent } from './components/publication/publication.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { LoginComponent } from './components/login/login.component';
import { VoitureComponent } from './components/voiture/voiture.component';
import { EditVoitureComponent } from './components/edit-voiture/edit-voiture.component';
import { NewVoyageModalComponent } from './components/new-voyage-modal/new-voyage-modal.component';
import { NarvbarComponent } from './components/narvbar/narvbar.component';
import { EditAdminComponent } from './components/edit-admin/edit-admin.component';
import { ListConductorsComponent } from './components/list-conductors/list-conductors.component';
import { LoginAdminComponent } from './components/login-admin/login-admin.component';
import { ListeVoituresComponent } from './components/liste-voitures/liste-voitures.component';
import { ListPublicationsComponent } from './components/list-publications/list-publications.component';
import { EditContactComponent } from './components/edit-contact/edit-contact.component';

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },

  { path: 'listedesPublications', component: ListPublicationsComponent },
  { path: 'editContact', component: EditContactComponent },

  { path: 'listevoitures', component: ListeVoituresComponent },
  { path: 'navbar', component: NarvbarComponent },
  { path: 'logina', component: LoginAdminComponent },
  { path: 'editAdmin', component: EditAdminComponent },
  { path: 'listConductors', component: ListConductorsComponent },
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'newVoyage', component: NewVoyageModalComponent },
  { path: 'contact', component: ContactComponent },
  { path: 'signup', component: SignupComponent },
  { path: 'editU', component: UserProfileComponent },
  { path: 'editV', component: EditVoitureComponent },

  { path: 'footer', component: FooterComponent },
  { path: 'publier', component: PublicationComponent },
  { path: 'side', component: SidebarComponent },
  { path: 'voiture', component: VoitureComponent },

  { path: '**', component: HomeComponent },


];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
