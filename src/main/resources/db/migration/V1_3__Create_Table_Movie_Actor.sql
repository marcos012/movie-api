CREATE SEQUENCE SEQ_ACTOR_ID START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;

CREATE TABLE ACTOR (
  ACTOR_ID NUMBER(38) NOT NULL,
  NAME VARCHAR2(100) NOT NULL,
  CONSTRAINT PK_ACTOR PRIMARY KEY (ACTOR_ID)
);

CREATE SEQUENCE SEQ_DIRECTOR_ID START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;

CREATE TABLE DIRECTOR (
  DIRECTOR_ID NUMBER(38) NOT NULL,
  NAME VARCHAR2(100) NOT NULL,
  CONSTRAINT PK_DIRECTOR PRIMARY KEY (DIRECTOR_ID)
);

CREATE TABLE MOVIE_ACTOR (
  MOVIE_ID NUMBER(38) NOT NULL,
  ACTOR_ID NUMBER(38) NOT NULL
);

ALTER TABLE MOVIE
DROP DIRECTOR, ACTORS;

ALTER TABLE MOVIE ADD DIRECTOR_ID NUMBER(38) NULL;
