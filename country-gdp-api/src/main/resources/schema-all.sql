DROP TABLE countryGdp IF EXISTS;

CREATE TABLE countryGdp (
	id BIGINT IDENTITY NOT NULL PRIMARY KEY,
	name VARCHAR(200),
	code VARCHAR(50),
	year int,
	value VARCHAR(200)
	);