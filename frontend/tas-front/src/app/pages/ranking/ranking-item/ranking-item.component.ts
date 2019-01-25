import {Component, Input, OnInit} from '@angular/core';
import {Game} from '../../../services/games/game';
import {Ranking} from '../../../services/games/ranking';

@Component({
  selector: 'app-ranking-item',
  templateUrl: './ranking-item.component.html'
})
export class RankingItemComponent implements OnInit {

  @Input() ranking: Ranking;
  @Input() position: number;

  constructor() {
  }

  ngOnInit() {
  }

}
