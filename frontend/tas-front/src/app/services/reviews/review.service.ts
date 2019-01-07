import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Review} from './review';

const apiUrl: string = 'http://projekttasy.herokuapp.com';

@Injectable({
  providedIn: 'root'
})
export class ReviewService {

  constructor(private http: HttpClient) { }

  getReviews() {
    return this.http.get<Array<Review>>(apiUrl + '/reviews');
  }

  getReviewsByGame(gameID: number) {
    return this.http.get<Array<Review>>(apiUrl + '/reviews/' + gameID);
  }

  getReviewsByUser(userID: string) {
    return this.http.get<Array<Review>>(apiUrl + '/reviews/user/' + userID);
  }

  registerUser(review: Review) {
    return this.http.post(apiUrl + '/addreview', review);
  }
}
