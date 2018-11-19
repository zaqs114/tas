import {Component, OnInit} from '@angular/core';
import {UserService} from '../../services/users/user.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  public searchText: string;

  constructor(private userService: UserService) {
  }

  ngOnInit() {
  }

  signIn() {

  }

  signUp() {

  }
}
