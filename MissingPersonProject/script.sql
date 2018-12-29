create table users
(
  id           int auto_increment
    primary key,
  email        varchar(255)                       not null,
  username     varchar(64)                        not null,
  password     varchar(64)                        not null,
  firstName    varchar(64)                        not null,
  lastName     varchar(64)                        not null,
  gender       varchar(64)                        not null,
  role         varchar(64)                        not null,
  birthday     varchar(10)                        not null,
  verified     bit                                not null,
  creationDate datetime default CURRENT_TIMESTAMP null,
  constraint users_email_uindex
  unique (email),
  constraint users_username_uindex
  unique (username)
);

create table missed_persons
(
  id           int auto_increment
    primary key,
  firstName    varchar(64)                        not null,
  lastName     varchar(64)                        not null,
  gender       varchar(64)                        not null,
  phoneNumber  varchar(64)                        not null,
  eyeColor     varchar(64)                        not null,
  hairColor    varchar(64)                        not null,
  nationality  varchar(64)                        not null,
  history      longtext                           not null,
  lastSeen     varchar(10)                        not null,
  creationDate datetime default CURRENT_TIMESTAMP null,
  isFound      bit                                null,
  userId       int                                not null,
  constraint missed_persons_users_id_fk
  foreign key (userId) references users (id)
);

create table feedbacks
(
  id             int auto_increment
    primary key,
  content        longtext                           not null,
  issuedDate     datetime default CURRENT_TIMESTAMP null,
  missedPersonId int                                not null,
  constraint feedbacks_missed_persons_id_fk
  foreign key (missedPersonId) references missed_persons (id)
);


