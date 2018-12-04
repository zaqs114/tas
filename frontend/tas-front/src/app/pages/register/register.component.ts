import { Component, OnInit } from '@angular/core';
import {User} from '../../services/users/user';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  public user: User;

  constructor() { }

  ngOnInit() {
    this.user = new User({});
  }

}
