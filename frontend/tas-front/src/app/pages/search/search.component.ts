import { Component, OnInit } from '@angular/core';
import {GameService} from '../../services/games/game.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  public games = [];
  public text = '';

  constructor(private gameService: GameService) { }

  ngOnInit() {

  }

  search() {
    if (this.text.length > 0) {
      this.gameService.getGameByTitle(this.text).subscribe(games => {
        this.games = games;
      });
    }
  }

}

