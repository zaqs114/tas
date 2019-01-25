import {Component, OnInit} from '@angular/core';
import {AuthService} from '../../services/auth/auth.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private auth: AuthService,
              private router: Router) {
  }

  ngOnInit() {

  }

  logged(){
    return localStorage.getItem('logged');
  }

  logout() {
    this.auth.logout().subscribe();
    this.router.navigate(['/main']);
    window.location.reload();
  }
}
