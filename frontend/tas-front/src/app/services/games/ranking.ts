import {Message} from '../message';

export class Ranking extends Message{
  public score: number;
  public title: string;
  public gameid: number;
}
