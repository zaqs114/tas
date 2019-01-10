import { Component, OnInit } from '@angular/core';
import {Game} from '../../services/games/game';
import {GameService} from '../../services/games/game.service';

@Component({
  selector: 'app-game-list',
  templateUrl: './game-list.component.html',
  styleUrls: ['./game-list.component.css']
})
export class GameListComponent implements OnInit {

  public games = [];

  constructor(private gameService: GameService) { }

  ngOnInit() {

    this.gameService.getAllGames().subscribe(games => {
      console.log(games);
      this.games = games;
    });
  }

}
