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

create table if not exists CATEGORY
(
  ID  DECFLOAT(40) auto_increment
    primary key,
  TAG CHARACTER VARYING unique
);

create index if not exists tag_index on CATEGORY(TAG);
create index if not exists description_index on POSITION(DESCRIPTION);
