import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {GameService} from '../../services/games/game.service';
import {Game} from '../../services/games/game';
import {Review} from '../../services/reviews/review';
import {ReviewService} from '../../services/reviews/review.service';
import {forkJoin} from 'rxjs';

@Component({
  selector: 'app-game-page',
  templateUrl: './game-page.component.html',
  styleUrls: ['./game-page.component.css']
})
export class GamePageComponent implements OnInit {

  public game: Game = new Game({});
  public reviews: Array<Review>;

  constructor(private route: ActivatedRoute,
              private gameService: GameService,
              private reviewService: ReviewService) {
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      forkJoin(this.reviewService.getReviewsByGame(params['id']), this.gameService.getGameByID(params['id'])).subscribe(data => {
        this.reviews = data[0];
        this.game = data[1][0];
      })
    });
  }

}
