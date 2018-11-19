import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import {RouterModule, Routes} from '@angular/router';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';

import { AppComponent } from './app.component';
import { MainComponent } from './pages/main/main.component';
import { HeaderComponent } from './pages/header/header.component';
import {UserService} from './services/users/user.service';

const appRoutes: Routes = [
  { path: '', component: MainComponent }
];

@NgModule({
  declarations: [
    AppComponent,
    MainComponent,
    HeaderComponent
  ],
imports: [BrowserModule,
  HttpClientModule,
  FormsModule,
  RouterModule.forRoot(appRoutes),
  NgbModule
],
  providers: [UserService],
  bootstrap: [AppComponent]
})
export class AppModule { }
