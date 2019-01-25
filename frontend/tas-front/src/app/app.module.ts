import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import {RouterModule, Routes} from '@angular/router';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';

import { AppComponent } from './app.component';
import { MainComponent } from './pages/main/main.component';
import { HeaderComponent } from './pages/header/header.component';
import { UserService } from './services/users/user.service';
import { GameListComponent } from './pages/game-list/game-list.component';
import { GamePageComponent } from './pages/game-page/game-page.component';
import { UserProfileComponent } from './pages/user-profile/user-profile.component';
import { GameItemComponent } from './pages/game-list/game-item/game-item.component';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { AlertComponent } from './tools/alert/alert.component';
import {ReviewService} from './services/reviews/review.service';
import {GameService} from './services/games/game.service';
import {AlertService} from './tools/alert/alert.service';
import {AuthService} from './services/auth/auth.service';
import {RankingItemComponent} from './pages/ranking/ranking-item/ranking-item.component';
import {RankingComponent} from './pages/ranking/ranking.component';
import { SearchComponent } from './pages/search/search.component';

const appRoutes: Routes = [
  { path: '', component: MainComponent },
  { path: 'main', component: MainComponent },
  { path: 'game-page/:id', component: GamePageComponent },
  { path: 'profile/:id', component: UserProfileComponent },
  { path: 'game-list', component: GameListComponent },
  { path: 'ranking', component: RankingComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'login', component: LoginComponent },
  { path: 'search', component: SearchComponent }
];

@NgModule({
  declarations: [
    AppComponent,
    MainComponent,
    HeaderComponent,
    GameListComponent,
    GamePageComponent,
    UserProfileComponent,
    GameItemComponent,
    LoginComponent,
    RegisterComponent,
    AlertComponent,
    RankingItemComponent,
    RankingComponent,
    SearchComponent
  ],
imports: [BrowserModule,
  HttpClientModule,
  FormsModule,
  RouterModule.forRoot(appRoutes),
  NgbModule
],
  providers: [
    UserService,
    ReviewService,
    GameService,
    AlertService,
    AuthService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
