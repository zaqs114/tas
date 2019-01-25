import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Game} from './game';
import {Ranking} from './ranking';

const apiUrl: string = 'https://projekttasy.herokuapp.com';

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

  getGameByID(gameid: number) {
    return this.http.get<Game>(apiUrl + '/games/' + gameid);
  }

  getRanking() {
    return this.http.get<Array<Ranking>>(apiUrl + '/games/ranking');
  }

  addGame(game: Game, icon: File, screen: File) {
    let headers = new HttpHeaders();
    //this is the important step. You need to set content type as null
    headers.set('Content-Type', null);
    headers.set('Accept', 'multipart/form-data');
    let params = new HttpParams();
    const formData: FormData = new FormData();
    formData.append('icon', icon);
    formData.append('title', game.title);
    formData.append('description', game.description);
    formData.append('launch_date', game.launch_date);
    formData.append('publisher', game.publisher);
    formData.append('screen', screen);
    formData.append('platform', game.platform);
    formData.append('genre', game.genre);
    return this.http.post(apiUrl + '/addgame', formData, {params, headers});
  }
}
