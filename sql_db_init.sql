CREATE EXTENSION IF NOT EXISTS pgcrypto;

create sequence tours_seq;

create sequence hotel_types_seq;

create table if not exists hotel_types (
  id int primary key default nextval ('hotel_types_seq'),
  name varchar(50) not null unique
);

create sequence tour_types_seq;

create table if not exists tour_types (
  id int primary key default nextval ('tour_types_seq'),
  name varchar(50) not null unique
);

create table if not exists tours (
    id int primary key default nextval ('tours_seq'),
    name varchar(100) not null,
    description varchar(200) not null,
    country varchar(30) not null,
    peoples int not null,
    hotel_type_id int not null,
    tour_type_id int not null,
    is_hot boolean NOT NULL,
    discount int not null,
    price int not null,
	
      CONSTRAINT tour_type_pk FOREIGN KEY (tour_type_id)
		  REFERENCES tour_types (id) MATCH SIMPLE
		  ON UPDATE CASCADE ON DELETE CASCADE,
      CONSTRAINT hotel_type_pk FOREIGN KEY (hotel_type_id)
		  REFERENCES hotel_types (id) MATCH SIMPLE
		  ON UPDATE CASCADE ON DELETE CASCADE
);

create sequence users_seq;

create table if not exists users (
  id int primary key  default nextval ('users_seq'),
  email varchar(100) not null unique,
  name varchar(200) not null,
  password varchar(200) not null,
  aboutme varchar(200),
  fullname varchar(200)
);

create sequence roles_seq;

create table if not exists roles (
  id int primary key default nextval ('roles_seq'),
  name varchar(50) not null unique
);

create sequence user_roles_seq;

create table if not exists user_roles (
  id int primary key default nextval ('user_roles_seq'),
  user_id int not null,
  role_id int not null,
		CONSTRAINT user_pk FOREIGN KEY (user_id)
			  REFERENCES users (id) MATCH SIMPLE
			  ON UPDATE CASCADE ON DELETE CASCADE,
		CONSTRAINT role_pk FOREIGN KEY (role_id)
			  REFERENCES roles (id) MATCH SIMPLE
			  ON UPDATE CASCADE ON DELETE CASCADE
);

create sequence countries_seq;

create table if not exists countries (
    id int primary key default nextval ('countries_seq'),
    name varchar(100) not null
);

create sequence statuses_types_seq;

create table if not exists statuses (
  id int primary key default nextval ('statuses_types_seq'),
  title varchar(50) not null unique
);

create sequence orders_seq;

create table if not exists orders (
  id int primary key default nextval ('orders_seq'),
  customer_id int not null,
  tour_id int not null,
  status_id int not null,
		CONSTRAINT status_pk FOREIGN KEY (status_id)
			  REFERENCES statuses (id) MATCH SIMPLE
			  ON UPDATE CASCADE ON DELETE CASCADE,
		CONSTRAINT customer_pk FOREIGN KEY (customer_id)
			  REFERENCES users (id) MATCH SIMPLE
			  ON UPDATE CASCADE ON DELETE CASCADE,
		CONSTRAINT tour_pk FOREIGN KEY (tour_id)
			  REFERENCES tours (id) MATCH SIMPLE
			  ON UPDATE CASCADE ON DELETE CASCADE
);