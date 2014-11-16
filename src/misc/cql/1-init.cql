CREATE KEYSPACE testBigData
WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };

USE testBigData;

CREATE TYPE user (
	first_name varchar,
	last_name varchar
);

CREATE TABLE message (
	user_id int, // partition key
	id timeuuid, // cluster key
	creator_id int,
	creator frozen<user>,
	body text,
	last_read_date timestamp static, // this field will be static for a specific user
	PRIMARY KEY (user_id, id)
)
WITH CLUSTERING ORDER BY (id DESC);

CREATE TABLE user_data (
	user_id int PRIMARY KEY,
	total_unread counter
);