import { Component, OnInit } from '@angular/core';
import {UserService} from '../../services/users/user.service';
import {ReviewService} from '../../services/reviews/review.service';
import {Review} from '../../services/reviews/review';
import {User} from '../../services/users/user';
import {forkJoin} from 'rxjs';
import {AuthService} from '../../services/auth/auth.service';
import {Router} from '@angular/router';
import {Game} from '../../services/games/game';
import {GameService} from '../../services/games/game.service';
import {AlertService} from '../../tools/alert/alert.service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  public user : User;
  public reviews : Array<Review>;
  public currentPage : string = "profile";
  public isAdmin: boolean;
  public newPassword: string;
  public repeatPassword: string;
  public avatar: File;
  public icon: File;
  public screen: File;
  public users: Array<User> = [];
  public newGame: Game = new Game({});
  constructor(private userService: UserService,
              private reviewService: ReviewService,
              private gameService: GameService,
              private router: Router,
              private alertService: AlertService,
              private auth: AuthService) { }

  ngOnInit() {
    if (localStorage.getItem("logged")) {
      forkJoin(this.reviewService.getReviewsByUser(localStorage.getItem("logged")), this.userService.getUserDetails(localStorage.getItem("logged"))).subscribe(data => {
        this.reviews = data[0];
        this.user = data[1];
        this.auth.isAdmin(this.user.login).subscribe((bool: boolean) => {
            this.isAdmin = bool;
            this.userService.getUsers().subscribe(users => {
              this.users = users;
            });
          }
        );
      });
    } else {
      this.router.navigate(['/login']);
    }
  }

  setPage(page: string) {
    this.currentPage = page;
  }

  onFileChanged(event) {
    this.avatar = event.target.files[0]
  }

  onUpload() {
    if (this.avatar) {
      this.userService.changeAvatar(localStorage.getItem("logged"), this.avatar).subscribe(() => {
        this.userService.getUserDetails(localStorage.getItem("logged")).subscribe(user => {
          this.user = user;
          this.avatar = null;
          this.currentPage = 'profile';
        })
      });
    }
  }

  addGame() {
    this.gameService.addGame(this.newGame, this.icon, this.screen).subscribe(() =>{
      this.newGame = new Game({});
      this.alertService.success("Dodano nową grę.")
    })
  }
}
