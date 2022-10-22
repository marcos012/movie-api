INSERT INTO DIRECTOR (director_id, name)
VALUES (1, 'Joss Whedon');

INSERT INTO ACTOR (actor_id, name)
VALUES (1, 'Robert Downey Jr.'), (2, 'Chris Evans');

INSERT INTO MOVIE (movie_id, director_id, genre, imdb, plot, poster, producer, released, runtime, title, total_seasons, type, created_at, updated_at)
VALUES (1, 1, 'Action', '8', 'integration', 'poster.png', 'Josh', DATE '2012-04-03', '142min', 'Avengers', null, 'MOVIE', TIMESTAMP '2022-10-20 21:33:25.726843', TIMESTAMP '2022-10-20 21:33:25.726843');

INSERT INTO MOVIE_ACTOR (actor_id, movie_id)
VALUES (1, 1), (2, 1);
