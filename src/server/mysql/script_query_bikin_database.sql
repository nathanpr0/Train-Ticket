-- BIKIN DATABASE
CREATE DATABASE train_ticket;

USE train_ticket;

-- BIKIN USER
CREATE TABLE USER(
	id VARCHAR(10) NOT NULL PRIMARY KEY,
	NAME VARCHAR(50) NOT NULL,
	PASSWORD VARCHAR(50) NOT NULL
);

INSERT INTO USER(id, NAME, PASSWORD)
VALUES ("A001", "Yanto", "admin123"),
("A002", "Burhan", "burhan123");

-- BIKIN SCHEDULE TIKET
CREATE TABLE schedule (
   id_schedule INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
   train_number VARCHAR(10) NOT NULL,
   machinist VARCHAR(50) NOT NULL,
   origin ENUM("Tangerang") NOT NULL,
   destination VARCHAR(50) NOT NULL,
   departure_time VARCHAR(10) NOT NULL,
   carriages INT NOT NULL,
   class ENUM("Economy", "Business", "Executive") NOT NULL,
   price INT NOT NULL
);


ALTER TABLE schedule
ADD CONSTRAINT unique_schedule UNIQUE (train_number, departure_time);

ALTER TABLE schedule
ADD CONSTRAINT unique_machinist_time UNIQUE (machinist, departure_time);

INSERT INTO SCHEDULE (train_number, machinist, carriages, destination, departure_time, class, price)
VALUES
('1007DF','Ari',10,'Jakarta','06:30','Economy',3000),
('1008DF','Dedi',10,'Jakarta','06:30','Economy',3000),
('1009DF','Eko',12,'Jakarta','06:30','Business',5000),
('1010DF','Hidayat',12,'Jakarta','06:30','Business',5000),
('1015DF','Nero',8,'Jakarta','06:30','Executive',10000);

-- BIKIN BOOKING TIKET
CREATE TABLE booking (
   booking_code VARCHAR(20) PRIMARY KEY,
   user_name VARCHAR(100) NOT NULL,
   qty INT NOT NULL,
   booking_date DATE NOT NULL,
   total_cost INT NOT NULL,
   status VARCHAR(20) NOT NULL,
   id_schedule INT NOT NULL,
   
   FOREIGN KEY (id_schedule) REFERENCES schedule(id_schedule)
   ON DELETE CASCADE
   ON UPDATE CASCADE
);

-- HAPUS TABLEtrain_ticket
DROP TABLE USER;
DROP TABLE SCHEDULE;
DROP TABLE booking;

-- HAPUS DATABASE
DROP DATABASE train_ticket;