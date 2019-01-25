import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {User} from '../users/user';

const apiUrl: string = 'https://projekttasy.herokuapp.com';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  constructor(private http: HttpClient) {

  }

  getLoggedUser() {
    let options = {
      withCredentials: true
    };
    return this.http.get(apiUrl + '/loggedUsername', options);
  }

  loginUser(user: User) {
    let body = `login=${user.login}&password=${user.password}`;

    return this.http.post(apiUrl + '/login', body.toString(), {withCredentials: true, headers: new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded'), responseType: 'text'});
  }

  logout() {
    let options = {
      headers: new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded'),
      withCredentials: true
    };

    localStorage.clear();

    return this.http.get(apiUrl + '/logout', options);
  }

  isAdmin(username) {
    let options = {
      headers: new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded'),
      withCredentials: true
    };

    return this.http.get(apiUrl + '/user/' + username +'/isAdmin', options);
  }

}
