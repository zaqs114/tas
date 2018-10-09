--skrypt wygenerowny przez datagrip
create table users
(
  login      varchar(30)       not null
    constraint users_pkey
    primary key,
  password   varchar(300)       not null,
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
  screen      varchar(200)
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

create table platforms
(
  platformid    integer     not null
    constraint platfroms_pkey
    primary key,
  platform_name varchar(30) not null
);

create unique index platfroms_platformid_uindex
  on platforms (platformid);

create table game_platform
(
  gameid     integer
    constraint game_platform_games_gameid_fk
    references games,
  platformid integer
    constraint game_platform_platforms_platformid_fk
    references platforms
);

create table genre
(
  genreid    integer not null
    constraint genre_pkey
    primary key,
  genre_name varchar(30)
);

create unique index genre_genreid_uindex
  on genre (genreid);

create table game_genre
(
  genreid integer
    constraint game_genre_genre_genreid_fk
    references genre,
  gameid  integer
    constraint game_genre_games_gameid_fk
    references games
);

