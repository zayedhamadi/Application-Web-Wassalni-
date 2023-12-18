import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { NgToastModule } from 'ng-angular-popup'
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SignupComponent } from './components/signup/signup.component';
import { HomeComponent } from './components/home/home.component';
import { FooterComponent } from './components/footer/footer.component';
import { ContactComponent } from './components/contact/contact.component';
import { UserProfileComponent } from './components/user-profile/user-profile.component';
import { PublicationComponent } from './components/publication/publication.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LoginComponent } from './components/login/login.component';
import { ToastrModule } from 'ngx-toastr';
import { VoitureComponent } from './components/voiture/voiture.component';
import { ButtonModule } from 'primeng/button';
import { DividerModule } from 'primeng/divider';
import { PasswordModule } from 'primeng/password';
import { EditVoitureComponent } from './components/edit-voiture/edit-voiture.component';
import { NewVoyageModalComponent } from './components/new-voyage-modal/new-voyage-modal.component';
import { FilterPubComponent } from './components/filter-pub/filter-pub.component';
import { NarvbarComponent } from './components/narvbar/narvbar.component';
import { EditAdminComponent } from './components/edit-admin/edit-admin.component';
import { ListConductorsComponent } from './components/list-conductors/list-conductors.component';
import { LoginAdminComponent } from './components/login-admin/login-admin.component';
import { ListeVoituresComponent } from './components/liste-voitures/liste-voitures.component';
import { ListPublicationsComponent } from './components/list-publications/list-publications.component';
import { EditContactComponent } from './components/edit-contact/edit-contact.component';

@NgModule({
  declarations: [
    AppComponent,
    SignupComponent,
    HomeComponent,
    FooterComponent,
    ContactComponent,
    UserProfileComponent,
    PublicationComponent,
    SidebarComponent,
    LoginComponent,
    VoitureComponent,
    EditVoitureComponent,
    NewVoyageModalComponent,
    FilterPubComponent,
    NarvbarComponent,
    EditAdminComponent,
    ListConductorsComponent,
    LoginAdminComponent,
    ListeVoituresComponent,
    ListPublicationsComponent,
    EditContactComponent],

  imports: [FormsModule,
    PasswordModule,
    NgToastModule,
    BrowserModule,
    ButtonModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserModule,
    ReactiveFormsModule,
    DividerModule,
    FormsModule, ToastrModule.forRoot()
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
