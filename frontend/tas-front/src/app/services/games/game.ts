import {Message} from '../message';

export class Game extends Message {
  public gameid: string;
  public icon: string;
  public title: string;
  public description: string;
  public launch_date: number;
  public publisher: string;
  public screen: string;
  public platform: string;
  public genre: string;
  public score: number;
}
