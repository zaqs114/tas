import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Game} from './game';

const apiUrl: string = 'http://projekttasy.herokuapp.com';

@Injectable({
  providedIn: 'root'
})
export class GameService {

  constructor(private http: HttpClient) { }

  getAllGames() {
    return this.http.get<Array<Game>>(apiUrl + '/games');
  }

  getGameByTitle(title: string) {
    return this.http.get<Game>(apiUrl + '/games/title/' + title);
  }

  getGameByID() {
    return this.http.get<Game>(apiUrl + '/games/ranking');
  }

  getGameRanking(title: string) {
    return this.http.get<Game>(apiUrl + '/games/title/' + title);
  }

  registerUser(game: Game) {
    return this.http.post(apiUrl + '/addgame', game);
  }
}
