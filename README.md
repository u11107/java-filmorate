# java-filmorate
Template repository for Filmorate project.

Filmorate database

The database contains six tables

Link to er-diagram image: https://dbdiagram.io/d/629c7e2054ce26352760ad30

1.user stores user data
- id - PK
- email
- login
- name
- birthday

2. friend stores information about friendship between two users
- user_id
- friends_id
   -status - issuing an enum that has two values
   - CONFIRMED - confirmed friend
   - UNCONFIRMED - unconfirmed

3.like table stores information about like users and movies
- user_like_id
- film_like_id

4.film table contains about films
- id - PK
- name
- description
- duration
- releaseDate

5.genre table contains information about the genre of the film
- genre_id
- genre - use enum with values
  - COMEDY,
  - DRAMA,
  - CARTOON,
  - THRILLER,
  - DOCUMENTARY,
  - ACTION

6.reiting table contains information about the rating of the movie

- reiting_id
- reiting - use enum with values
  - G
  - PG
  - PG13
  - R
  - NC17
