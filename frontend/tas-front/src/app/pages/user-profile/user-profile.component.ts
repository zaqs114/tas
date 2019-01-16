import { Component, OnInit } from '@angular/core';
import {UserService} from '../../services/users/user.service';
import {ReviewService} from '../../services/reviews/review.service';
import {Review} from '../../services/reviews/review';
import {User} from '../../services/users/user';
import {forkJoin} from 'rxjs';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  public user : User;
  public reviews : Array<Review>;
  public currentPage : string = "password";

  constructor(private userService : UserService,
              private reviewService : ReviewService) { }

  ngOnInit() {
    console.log(this.currentPage);
    this.userService.getLoggedUser().subscribe((username : string) => {
      forkJoin(this.reviewService.getReviewsByUser(username), this.userService.getUserDetails(username)).subscribe(data => {
        this.reviews = data[0];
        this.user = data[1];
      })
    })
  }

  changePassword() {

  }

}
