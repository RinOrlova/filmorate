CREATE TABLE IF NOT EXISTS "film"
(
    "id"          int auto_increment PRIMARY KEY ,
    "name"        varchar,
    "description" varchar,
    "duration"    int,
    "releaseDate" date,
    "rating_id"   int
);
CREATE TABLE IF NOT EXISTS "genre"
(
    "id"   int auto_increment PRIMARY KEY,
    "name" varchar
);
CREATE TABLE IF NOT EXISTS "film_x_genre"
(
    "film_id"  int,
    "genre_id" int
);
CREATE TABLE IF NOT EXISTS "mpa_rating"
(
    "id"   int auto_increment PRIMARY KEY,
    "name" varchar
);
CREATE TABLE IF NOT EXISTS "app_user"
(
    "id"       int auto_increment PRIMARY KEY,
    "name"     varchar,
    "login"    varchar,
    "email"    varchar,
    "birthday" date
);
CREATE TABLE IF NOT EXISTS "film_x_user"
(
    "film_id" int,
    "user_id" int
);
CREATE TABLE IF NOT EXISTS "connection"
(
    "user1_id" int,
    "user2_id" int,
    "status" BOOLEAN NOT NULL
);

ALTER TABLE "film_x_genre" ADD FOREIGN KEY ("genre_id") REFERENCES "genre" ("id");

ALTER TABLE "film_x_genre" ADD FOREIGN KEY ("film_id") REFERENCES "film" ("id");

ALTER TABLE "film" ADD FOREIGN KEY ("rating_id") REFERENCES "mpa_rating" ("id");

ALTER TABLE "film_x_user" ADD FOREIGN KEY ("film_id") REFERENCES "film" ("id");

ALTER TABLE "film_x_user" ADD FOREIGN KEY ("user_id") REFERENCES "app_user" ("id");

ALTER TABLE "connection" ADD FOREIGN KEY ("user1_id") REFERENCES "app_user" ("id");

ALTER TABLE "connection" ADD FOREIGN KEY ("user2_id") REFERENCES "app_user" ("id");