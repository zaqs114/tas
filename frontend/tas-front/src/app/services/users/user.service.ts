import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {User} from './user';

const apiUrl: string = 'http://projekttasy.herokuapp.com';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  constructor(private http: HttpClient) {

  }

  getUsers() {
    return this.http.get<Array<User>>(apiUrl + '/users');
  }

  getUserDetails(login: string) {
    return this.http.get<User>(apiUrl + '/users/userDetails/' + login);
  }

  getLoggedUser() {
    return this.http.get(apiUrl + '/users/loggedUsername');
  }

  registerUser(user: User) {
    let body = new FormData();
    body.append('user', user.login);
    body.append('password', user.password);

    let options = {
      headers: new HttpHeaders().set('Content-Type', 'application/form-data')
    };

    return this.http.post(apiUrl + '/register', body.toString(), options);
  }

  loginUser(user: User) {
    let body = new URLSearchParams();
    body.set('user', user.login);
    body.set('password', user.password);

    let options = {
      headers: new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded')
    };

    return this.http.post(apiUrl + '/login', body, options);
  }

}
