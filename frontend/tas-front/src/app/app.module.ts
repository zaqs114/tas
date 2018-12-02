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
import { GameListComponent } from './pages/game-list/game-list.component';
import { GamePageComponent } from './pages/game-page/game-page.component';
import { GameReviewComponent } from './pages/game-review/game-review.component';
import { UserProfileComponent } from './pages/user-profile/user-profile.component';
import {GameItemComponent} from './pages/game-list/game-item/game-item.component';
import { LoginComponent } from './pages/login/login.component';

const appRoutes: Routes = [
  { path: '', component: MainComponent },
  { path: 'main', component: MainComponent },
  { path: 'game-page/:id', component: GamePageComponent },
  { path: 'user-profile/:id', component: UserProfileComponent },
  { path: 'game-list', component: GameListComponent }
];

@NgModule({
  declarations: [
    AppComponent,
    MainComponent,
    HeaderComponent,
    GameListComponent,
    GamePageComponent,
    GameReviewComponent,
    UserProfileComponent,
    GameItemComponent,
    LoginComponent
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
