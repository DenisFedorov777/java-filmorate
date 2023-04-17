DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS friendships;
DROP TABLE IF EXISTS likes;
DROP TABLE IF EXISTS films;
DROP TABLE IF EXISTS films_genres;
DROP TABLE IF EXISTS genres;
DROP TABLE IF EXISTS mpa;

CREATE TABLE IF NOT EXISTS users
(
    id       BIGINT primary key auto_increment,
    email    varchar(50) not null unique,
    login    varchar(50) not null,
    name     varchar(50) not null,
    birthday date
);

CREATE TABLE IF NOT EXISTS friendships
(
    user_id   BIGINT,
    friend_id BIGINT,
    approved  boolean,
    foreign key (user_id) references users (id),
    foreign key (friend_id) references users (id),
    primary key (user_id, friend_id)
);

CREATE TABLE IF NOT EXISTS mpa
(
    id   BIGINT primary key auto_increment,
    name varchar(5) not null unique
);

INSERT INTO mpa (name)
VALUES ('G'),
       ('PG'),
       ('PG-13'),
       ('R'),
       ('NC-17');

CREATE TABLE IF NOT EXISTS genres
(
    id   BIGINT primary key auto_increment,
    name varchar(50) not null unique
);

INSERT INTO genres (name)
VALUES ('Комедия'),
       ('Драма'),
       ('Мультфильм'),
       ('Триллер'),
       ('Документальный'),
       ('Боевик');

CREATE TABLE IF NOT EXISTS likes
(
    film_id BIGINT,
    user_id BIGINT,
    primary key (film_id, user_id)
);

CREATE TABLE IF NOT EXISTS films
(
    id           BIGINT primary key auto_increment,
    name         varchar(50)  not null,
    description  varchar(200) not null,
    release_date date,
    duration     int,
    mpa_id       BIGINT,
    foreign key (mpa_id) references mpa (id)
);

CREATE TABLE IF NOT EXISTS films_genres
(
    film_id  BIGINT,
    genre_id BIGINT,
    primary key (film_id, genre_id),
    foreign key (film_id) references films (id),
    foreign key (genre_id) references genres (id)
);
