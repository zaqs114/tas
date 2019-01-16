import {Component, OnInit} from '@angular/core';
import {UserService} from '../../services/users/user.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  public searchText: string;
  public logged: boolean;
  public loggedUsername: string;

  constructor(private userService: UserService) {
  }

  ngOnInit() {
    this.userService.getLoggedUser().subscribe((username: string) => {
      if(username) {
        this.logged = true;
        this.loggedUsername = username;
      }
    })
  }
}
