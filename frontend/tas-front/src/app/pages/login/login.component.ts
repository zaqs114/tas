import {Component, OnInit} from '@angular/core';
import {User} from '../../services/users/user';
import {Router} from '@angular/router';
import {AlertService} from '../../tools/alert/alert.service';
import {AuthService} from '../../services/auth/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public user: User;

  constructor(private auth: AuthService,
              private router: Router,
              private alertService: AlertService) {
  }

  ngOnInit() {
    this.user = new User({});
  }

  onLogin() {
    this.auth.loginUser(this.user).subscribe((username : string) => {
      localStorage.setItem('logged', username);
      this.router.navigate(['/main']);
    }, error => {
      this.alertService.error('Błąd logowania. Sprawdź poprawność loginu i hasła.');
    });
  }

}
