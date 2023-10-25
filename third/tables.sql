create table if not exists Entry(
  id serial primary key,
  hitResult boolean,
  xValue float,
  yValue float,
  rValue float,
  executiontime text,
  responsetime  float
);
