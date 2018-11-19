import {Message} from '../message';

export class User extends Message {
  login: string;
  password: string;
  avatar: string;
  admin_perm: number;
}
