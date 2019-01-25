import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {GameService} from '../../services/games/game.service';
import {Game} from '../../services/games/game';
import {Review} from '../../services/reviews/review';
import {ReviewService} from '../../services/reviews/review.service';
import {forkJoin} from 'rxjs';
import {AlertService} from '../../tools/alert/alert.service';
import {AuthService} from '../../services/auth/auth.service';

@Component({
  selector: 'app-game-page',
  templateUrl: './game-page.component.html',
  styleUrls: ['./game-page.component.css']
})
export class GamePageComponent implements OnInit {

  public game: Game = new Game({});
  public reviews: Array<Review>;
  public newReview: Review = new Review({});
  public logged: boolean = false;

  constructor(private route: ActivatedRoute,
              private gameService: GameService,
              private alertService: AlertService,
              private auth: AuthService,
              private reviewService: ReviewService) {
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      forkJoin(this.reviewService.getReviewsByGame(params['id']), this.gameService.getGameByID(params['id'])).subscribe(data => {
        this.reviews = data[0];
        this.game = data[1][0];
        if (localStorage.getItem('logged')) {
          this.logged = true;
        }
      });
    });
  }

  addReview() {
    if (this.newReview.rate && this.newReview && this.newReview.title && this.newReview.content) {
      if (this.newReview.rate > 10) {
        this.alertService.error('Ocena nie może być większa od 10.');
      } else {
        this.newReview.gameID = this.game.gameid;
        this.newReview.userID = localStorage.getItem('logged');
        this.reviewService.addReview(this.newReview).subscribe(res => {
          forkJoin(this.reviewService.getReviewsByGame(this.game.gameid), this.gameService.getGameByID(this.game.gameid)).subscribe(data => {
            this.newReview = new Review({});
            this.reviews = data[0];
            this.game = data[1][0];
          });
        }, error => {
          this.alertService.error('Wystąpił błąd podczas dodawania recenzji.');
        });
      }
    } else {
      this.alertService.error('Uzupełnij brakujące dane.');
    }
  }

}
