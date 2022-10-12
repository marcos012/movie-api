CREATE SEQUENCE SEQ_ID_MOVIE START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;

CREATE TABLE MOVIE(
    OID_MOVIE NUMBER(38) NOT NULL,
    TITLE VARCHAR2(100) NOT NULL,
    PLOT VARCHAR2(255) NULL,
    GENRE VARCHAR2(50) NOT NULL,
    IMDB NUMBER(5) NOT NULL,
    RELEASED TIMESTAMP NOT NULL,
    PRODUCER VARCHAR2(20) NOT NULL,
    POSTER VARCHAR2(255) NOT NULL,
    TYPE VARCHAR2(10) NOT NULL,
    ACTORS VARCHAR2(50) NULL,
    DIRECTOR VARCHAR2(20) NULL,
    RUNTIME VARCHAR2(20) NULL,
    TOTAL_SEASONS VARCHAR2(3) NULL,
    CREATED_AT TIMESTAMP NOT NULL,
    UPDATED_AT TIMESTAMP NOT NULL,
    CONSTRAINT PK_MOVIE PRIMARY KEY (OID_MOVIE)
);