import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {GameService} from '../../services/games/game.service';
import {Game} from '../../services/games/game';

@Component({
  selector: 'app-game-page',
  templateUrl: './game-page.component.html',
  styleUrls: ['./game-page.component.css']
})
export class GamePageComponent implements OnInit {

  public game: Game = new Game({});

  constructor(private route: ActivatedRoute,
              private gameService: GameService) {
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.gameService.getGameByID(params['id']).subscribe(game => {
        this.game = game[0];
      })
    });
  }

}
