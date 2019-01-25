import { Component, OnInit } from '@angular/core';
import {GameService} from '../../services/games/game.service';

@Component({
  selector: 'app-ranking',
  templateUrl: './ranking.component.html'
})
export class RankingComponent implements OnInit {

  public ranking = [];

  constructor(private gameService: GameService) { }

  ngOnInit() {

    this.gameService.getRanking().subscribe(ranking => {
      this.ranking = ranking;
    });
  }

}
