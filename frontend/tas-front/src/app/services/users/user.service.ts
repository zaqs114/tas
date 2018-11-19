import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {User} from './user';

const apiUrl: string = 'http://localhost:8000';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  constructor(private http: HttpClient) {}

  getUsers() {
    return this.http.get<Array<User>>(apiUrl + '/users');
  }

  getUserDetails(id: number) {
    return this.http.get<User>(apiUrl + '/users/userDetails/' + id);
  }

  getLoggedUser() {
    return this.http.get(apiUrl + '/users/loggedUsername');
  }

  registerUser(user: User) {
    return this.http.post(apiUrl + '/register', user);
  }

}
