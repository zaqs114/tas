import { Component, OnInit } from '@angular/core';
import {User} from '../../services/users/user';
import {UserService} from '../../services/users/user.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public user: User;

  constructor(private userService: UserService,
              private router: Router) { }

  ngOnInit() {
    this.user = new User({});
  }

  onLogin() {
    this.userService.loginUser(this.user).subscribe();
    //window.location.href = 'http://localhost:4200';
  }

}
