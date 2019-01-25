import {Component, OnInit} from '@angular/core';
import {User} from '../../services/users/user';
import {UserService} from '../../services/users/user.service';
import {AlertService} from '../../tools/alert/alert.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  public user: User;
  public reapeatPassword: string;

  constructor(private userService: UserService,
              private alertService: AlertService,
              private router: Router) {
  }

  ngOnInit() {
    this.user = new User({});
  }

  onRegister() {
    if (this.reapeatPassword && this.reapeatPassword.length > 0 && this.reapeatPassword == this.user.password) {
      this.userService.registerUser(this.user).subscribe(suc => {
        this.router.navigate(['/']);
      }, error =>{
        this.alertService.error("Błąd rejestracji. Spróbuj ponownie póżniej.")
      });
    } else {
      this.alertService.error("Wprowadzone hasła się różnią. Spróbuj ponowanie wpisać hasło.");
    }
  }
}
