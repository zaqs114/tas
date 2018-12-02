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
    this.games.push(new Game({title: 'gra nr 1', score: 4}));
    this.games.push(new Game({title: 'gra nr 2', score: 3}));
    this.games.push(new Game({title: 'gra nr 3', score: 5}));
  }

}
