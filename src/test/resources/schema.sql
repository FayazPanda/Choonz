DROP TABLE album;
DROP TABLE artist;
DROP TABLE genre;
DROP TABLE playlist;
DROP TABLE track;
DROP TABLE user;
CREATE TABLE IF NOT EXISTS album (id bigint generated by default as identity, cover varchar(255), name varchar(100) not null, artist_id bigint, genre_id bigint);
CREATE TABLE IF NOT EXISTS artist (id bigint generated by default as identity, name varchar(100) not null);
CREATE TABLE IF NOT EXISTS genre (id bigint generated by default as identity, description varchar(250) not null, name varchar(100) not null);
CREATE TABLE IF NOT EXISTS playlist (id bigint generated by default as identity, artwork varchar(1000) not null, description varchar(500) not null, name varchar(100) not null, user_id bigint not null);
CREATE TABLE IF NOT EXISTS playlist_tracks (playlist_id bigint not null, tracks_id bigint not null);
CREATE TABLE IF NOT EXISTS track (id bigint generated by default as identity, duration integer not null, lyrics varchar(255), name varchar(100) not null, album_id bigint);
CREATE TABLE IF NOT EXISTS user (id bigint generated by default as identity, password varchar(255), username varchar(255));