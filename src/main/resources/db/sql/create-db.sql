DROP TABLE bet_details IF EXISTS;

CREATE TABLE bet_details (
  betid  VARCHAR(10) PRIMARY KEY,
  bettimestamp BIGINT,
  selectionid  INT,
  selectionname  VARCHAR(30),
  stake  DOUBLE,
  price  DOUBLE,
  currency  VARCHAR(3),
  payout  DOUBLE,
  europrice DOUBLE,
  eurostake DOUBLE
);
