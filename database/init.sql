create table users
(
  login      varchar(30)       not null
    constraint users_pkey
    primary key,
  password   varchar(30)       not null,
  avatar     varchar(200),
  admin_perm integer default 0 not null
);



create unique index users_login_uindex
  on users (login);

create table games
(
  gameid      integer      not null
    constraint games_pkey
    primary key,
  icon        varchar(200),
  title       varchar(30)  not null,
  description varchar(500) not null,
  launchdate  date         not null,
  publisher   varchar(30)  not null,
  screen      varchar(200),
  platform    varchar(100) not null,
  genre       varchar(50)
);



create unique index games_gameid_uindex
  on games (gameid);

create table reviews
(
  reviewid integer       not null
    constraint reviews_pkey
    primary key,
  title    varchar(120)  not null,
  content  varchar(1000) not null,
  rate     integer       not null,
  userid   varchar(30)   not null
    constraint reviews_users_login_fk
    references users,
  gameid   integer       not null
    constraint reviews_games_gameid_fk
    references games
);



create unique index reviews_reviewid_uindex
  on reviews (reviewid);

