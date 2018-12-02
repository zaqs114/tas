import {Component, Input, OnInit} from '@angular/core';
import {Game} from '../../../services/games/game';

@Component({
  selector: 'app-game-item',
  templateUrl: './game-item.component.html'
})
export class GameItemComponent implements OnInit {

  @Input() game:Game;

  constructor() { }

  ngOnInit() {
  }

}
