-- BIKIN DATABASE
CREATE DATABASE train_ticket;

USE train_ticket;

DROP DATABASE train_ticket;

-- BIKIN USER
CREATE TABLE USER(
	id VARCHAR(10) NOT NULL PRIMARY KEY,
	NAME VARCHAR(50) NOT NULL,
	PASSWORD VARCHAR(50) NOT NULL
);

INSERT INTO USER(id, NAME, PASSWORD)
VALUES ("A001", "Yanto", "admin123"),
("A002", "Burhan", "burhan123");

DROP TABLE user;

-- BIKIN SCHEDULE TIKET
CREATE TABLE schedule(
	id_schedule VARCHAR(10) NOT NULL PRIMARY KEY,
	train_number VARCHAR(10) NOT NULL,
	machinist VARCHAR(30) NOT NULL,
	carriages INT NOT NULL,
	seats_available INT NOT NULL,
	origin VARCHAR(20) NOT NULL,
	destination VARCHAR(20) NOT NULL,
	depature_time VARCHAR(10) NOT NULL,
	class ENUM("Economy", "Business", "Executive") NOT NULL,
	price INT NOT NULL
);

INSERT INTO SCHEDULE (id_schedule, train_number, machinist, carriages, seats_available,
origin, destination, depature_time, class, price)
VALUES
('SCH001','1007DF','Ari',10,200,'Jakarta','Tangerang','06:30','Economy',3000),
('SKE001','1008DF','Dedi',10,180,'Jakarta','Tangerang','06:30','Economy',3000),
('SOP001','1009DF','Eko',12,150,'Jakarta','Tangerang','06:30','Business',5000),
('SKW001','1010DF','Hidayat',12,120,'Jakarta','Tangerang','06:30','Business',5000),
('TRN001','1015DF','Nero',8,100,'Tangerang','Jakarta','06:30','Executive',10000);

DROP TABLE schedule;

-- BIKIN BOOKING TIKET
CREATE TABLE booking (
   booking_code VARCHAR(20) PRIMARY KEY,
   user_name VARCHAR(100) NOT NULL,
   qty INT NOT NULL,
   booking_date DATE NOT NULL,
   total_cost INT NOT NULL,
   status VARCHAR(20) NOT NULL,
   id_schedule VARCHAR(10) NOT NULL
   
   FOREIGN KEY (id_schedule) REFERENCES schedule(id_schedule)
   ON DELETE CASCADE
   ON UPDATE CASCADE
);

DROP TABLE booking;