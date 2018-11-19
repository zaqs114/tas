export class Message {
  constructor(json: Object) {
    for(let propName in json) {
      this[propName] = json[propName];
    }
    return this;
  }
}
