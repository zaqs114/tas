import {Component, OnInit} from '@angular/core';
import {UserService} from '../../services/user.service';
import {User} from '../../models/user';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  public user: User = new User({});

  constructor(private userService: UserService) {
  }

  ngOnInit() {
  }

  login() {

  }

  register() {
    this.userService.registerUser(this.user);
  }
}
