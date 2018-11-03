import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';

const apiUrl: string = 'http://localhost:63343'

@Injectable()
export class UserService {
  constructor(private http: HttpClient) {}

  getUsers(): Observable<any> {
    return this.http.get(`${apiUrl}/users`);
  }

  registerUser(user: User) {
    return this.http.post(`${apiUrl}/register`, user).subscribe(res => {
      console.log(res);
    });
  }

}
