import {Message} from '../message';

export class Review extends Message {
  public reviewID: number;
  public title: string;
  public content: string;
  public rate: number;
  public userID: string;
  public gameID: number;
}
