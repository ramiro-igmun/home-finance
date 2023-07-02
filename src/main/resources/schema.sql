create table if not exists POSITION
(
    ID          DECFLOAT(40) auto_increment
        primary key,
    DATE        DATE,
    AMOUNT      NUMERIC(14, 2),
    CATEGORY    CHARACTER VARYING(20),
    TYPE        CHARACTER VARYING(20),
    DESCRIPTION CHARACTER VARYING
);
