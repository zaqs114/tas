class User {
  login: string;
  password: string;
  avatar: string;
  admin_perm: number;

  constructor(json: Object) {
    for(let propName in json) {
      this[propName] = json[propName];
    }
    return this;
  }

}
