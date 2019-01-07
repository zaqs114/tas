import { Component, OnInit } from '@angular/core';
import {User} from '../../services/users/user';
import {UserService} from '../../services/users/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public user: User;

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.user = new User({});
  }

  onLogin() {
    this.userService.loginUser(this.user).subscribe();
  }

}
