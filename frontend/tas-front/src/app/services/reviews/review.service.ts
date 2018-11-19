import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Review} from './review';

const apiUrl: string = 'http://localhost:8000';

@Injectable({
  providedIn: 'root'
})
export class ReviewService {

  constructor(private http: HttpClient) { }

  getReviews() {
    return this.http.get<Array<Review>>(apiUrl + '/reviews');
  }

  getReviews(gameID: number) {
    return this.http.get<Array<Review>>(apiUrl + '/reviews/' + gameID);
  }

  getReviews(userID: string) {
    return this.http.get<Array<Review>>(apiUrl + '/reviews/user/' + userID);
  }

  registerUser(review: Review) {
    return this.http.post(apiUrl + '/addreview', review);
  }
}
