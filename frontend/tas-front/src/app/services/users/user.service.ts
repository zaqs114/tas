import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {User} from './user';

const apiUrl: string = 'https://projekttasy.herokuapp.com';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  constructor(private http: HttpClient) {

  }

  getUsers() {
    let options = {
      withCredentials: true
    };
    return this.http.get<Array<User>>(apiUrl + '/users', options);
  }

  getUserDetails(login: string) {
    return this.http.get<User>(apiUrl + '/userDetails/' + login);
  }

  registerUser(user: User) {
    return this.http.post(apiUrl + '/register2', user);
  }

  changeAvatar(login: string, avatar: File) {
    let headers = new HttpHeaders();
    //this is the important step. You need to set content type as null
    headers.set('Content-Type', 'application/octet-stream');
    headers.set('Accept', 'multipart/form-data');
    let params = new HttpParams();
    const formData: FormData = new FormData();
    formData.append('avatar', avatar);
    formData.append('login', login);
    return this.http.put(apiUrl + '/user/' + login + '/avatarUpdate', formData, {params, headers});
  }
