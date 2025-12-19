-- BIKIN DATABASE
CREATE DATABASE train_ticket;

USE train_ticket;

-- BIKIN admin
CREATE TABLE admin(
	id VARCHAR(10) NOT NULL PRIMARY KEY,
	NAME VARCHAR(50) NOT NULL,
	PASSWORD VARCHAR(50) NOT NULL
);

INSERT INTO admin(id, NAME, PASSWORD)
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

-- BIKIN BOOKING TIKET
CREATE TABLE booking (
   booking_code VARCHAR(20) PRIMARY KEY,
   name VARCHAR(100) NOT NULL,
   booking_date DATE NOT NULL,
   status VARCHAR(20) NOT NULL,
   additional_cost INT NOT NULL,
   total_cost INT NOT NULL,
   id_schedule INT NOT NULL,
   id_admin VARCHAR(10) NOT NULL,
   
   FOREIGN KEY (id_schedule) REFERENCES schedule(id_schedule)
	   ON DELETE CASCADE
	   ON UPDATE CASCADE,	
	   
	FOREIGN KEY (id_admin) REFERENCES admin(id)
		ON DELETE CASCADE
	   ON UPDATE CASCADE
);

ALTER TABLE booking
ADD CONSTRAINT uq_booking_code UNIQUE (booking_code);

-- TESTING MEMASUKKAN DATA KE TABLE SCHEDULE & MENAMPILKAN DATA KE APLIKASI
INSERT INTO SCHEDULE (train_number, machinist, origin, carriages, destination, departure_time, class, price)
VALUES
-- JAM 06:00: SKENARIO KHUSUS (Tujuan Jakarta, Kelas Economy, Jam Sama, Gerbong Beda)
('TRN-801', 'Ari', 'Tangerang', 11, 'Jakarta', '06:00', 'Economy', 6000),
('TRN-802', 'Budi', 'Tangerang', 13, 'Jakarta', '06:00', 'Economy', 6000),
('TRN-803', 'Cici', 'Tangerang', 15, 'Jakarta', '06:00', 'Economy', 6000),

-- JAM 07:00: Tujuan Bogor (Kelas Business)
('TRN-804', 'Dedi', 'Tangerang', 12, 'Bogor', '07:00', 'Business', 15000),
('TRN-805', 'Eko', 'Tangerang', 14, 'Bogor', '07:00', 'Business', 15000),

-- JAM 08:30: Tujuan Yogyakarta
('TRN-806', 'Fajar', 'Tangerang', 11, 'Yogyakarta', '08:30', 'Executive', 25000),
('TRN-807', 'Gani', 'Tangerang', 15, 'Yogyakarta', '08:30', 'Business', 20000),

-- JAM 10:00: Tujuan Jakarta (Kelas Business Sama, Jam Sama, Gerbong Beda)
('TRN-808', 'Hana', 'Tangerang', 12, 'Jakarta', '10:00', 'Business', 8000),
('TRN-809', 'Irfan', 'Tangerang', 14, 'Jakarta', '10:00', 'Business', 8000),

-- JAM 11:30: Tujuan Bogor
('TRN-810', 'Joko', 'Tangerang', 13, 'Bogor', '11:30', 'Economy', 13000),
('TRN-811', 'Kiki', 'Tangerang', 11, 'Bogor', '11:30', 'Executive', 20000),

-- JAM 12:00: SKENARIO KHUSUS (Tujuan Yogyakarta, Kelas Executive Sama, Gerbong Beda)
('TRN-812', 'Luluk', 'Tangerang', 11, 'Yogyakarta', '12:00', 'Executive', 25000),
('TRN-813', 'Maya', 'Tangerang', 13, 'Yogyakarta', '12:00', 'Executive', 25000),
('TRN-814', 'Nana', 'Tangerang', 15, 'Yogyakarta', '12:00', 'Executive', 25000),

-- JAM 13:30 s/d 15:00
('TRN-815', 'Oky', 'Tangerang', 12, 'Jakarta', '13:30', 'Economy', 6000),
('TRN-816', 'Putra', 'Tangerang', 14, 'Jakarta', '13:30', 'Business', 8000),
('TRN-817', 'Rani', 'Tangerang', 11, 'Bogor', '14:15', 'Economy', 13000),
('TRN-818', 'Santi', 'Tangerang', 13, 'Bogor', '14:15', 'Business', 15000),
('TRN-819', 'Tio', 'Tangerang', 15, 'Yogyakarta', '15:00', 'Economy', 18000),
('TRN-820', 'Umar', 'Tangerang', 12, 'Yogyakarta', '15:00', 'Business', 20000),

-- JAM 16:00: Tujuan Jakarta (Kelas Executive Sama, Gerbong Beda)
('TRN-821', 'Vina', 'Tangerang', 11, 'Jakarta', '16:00', 'Executive', 13000),
('TRN-822', 'Wawan', 'Tangerang', 15, 'Jakarta', '16:00', 'Executive', 13000),

-- JAM 17:00 s/d 19:00
('TRN-823', 'Xena', 'Tangerang', 14, 'Bogor', '17:00', 'Economy', 13000),
('TRN-824', 'Yuda', 'Tangerang', 12, 'Bogor', '17:00', 'Business', 15000),
('TRN-825', 'Zaki', 'Tangerang', 11, 'Yogyakarta', '18:00', 'Economy', 18000),
('TRN-826', 'Amir', 'Tangerang', 13, 'Yogyakarta', '18:00', 'Executive', 25000),
('TRN-827', 'Bunga', 'Tangerang', 15, 'Jakarta', '19:00', 'Economy', 6000),
('TRN-828', 'Citra', 'Tangerang', 12, 'Jakarta', '19:00', 'Business', 8000),
('TRN-829', 'Doni', 'Tangerang', 14, 'Bogor', '19:30', 'Executive', 20000),
('TRN-830', 'Elsa', 'Tangerang', 11, 'Bogor', '19:30', 'Business', 15000),

-- JAM 20:00: SKENARIO KHUSUS (Tujuan Jakarta, Kelas Business Sama, Gerbong Beda)
('TRN-831', 'Fani', 'Tangerang', 11, 'Jakarta', '20:00', 'Business', 8000),
('TRN-832', 'Gilang', 'Tangerang', 13, 'Jakarta', '20:00', 'Business', 8000),
('TRN-833', 'Hesti', 'Tangerang', 15, 'Jakarta', '20:00', 'Business', 8000),

-- JAM 21:00 s/d Akhir
('TRN-834', 'Indra', 'Tangerang', 12, 'Bogor', '21:00', 'Economy', 13000),
('TRN-835', 'Juna', 'Tangerang', 14, 'Bogor', '21:00', 'Business', 15000),
('TRN-836', 'Karin', 'Tangerang', 11, 'Yogyakarta', '21:30', 'Executive', 25000),
('TRN-837', 'Liem', 'Tangerang', 13, 'Yogyakarta', '21:30', 'Business', 20000),
('TRN-838', 'Maman', 'Tangerang', 15, 'Jakarta', '22:00', 'Economy', 6000),
('TRN-839', 'Nita', 'Tangerang', 12, 'Jakarta', '22:00', 'Business', 8000),
('TRN-840', 'Owan', 'Tangerang', 14, 'Bogor', '22:30', 'Executive', 20000),
('TRN-841', 'Puji', 'Tangerang', 11, 'Bogor', '22:30', 'Economy', 13000),
('TRN-842', 'Qori', 'Tangerang', 15, 'Yogyakarta', '23:00', 'Economy', 18000),
('TRN-843', 'Raka', 'Tangerang', 13, 'Yogyakarta', '23:00', 'Business', 20000),
('TRN-844', 'Susi', 'Tangerang', 12, 'Jakarta', '23:15', 'Executive', 13000),
('TRN-845', 'Tono', 'Tangerang', 14, 'Jakarta', '23:15', 'Business', 8000),
('TRN-846', 'Uli', 'Tangerang', 11, 'Bogor', '23:30', 'Economy', 13000),
('TRN-847', 'Vino', 'Tangerang', 15, 'Bogor', '23:30', 'Business', 15000),
('TRN-848', 'Wina', 'Tangerang', 13, 'Yogyakarta', '23:45', 'Executive', 25000),
('TRN-849', 'Yadi', 'Tangerang', 12, 'Yogyakarta', '23:45', 'Economy', 18000),
('TRN-850', 'Zaza', 'Tangerang', 14, 'Jakarta', '23:55', 'Economy', 6000);booking`schedule`

-- HAPUS TABLE train_ticket
DROP TABLE USER;
DROP TABLE SCHEDULE;
DROP TABLE booking;

-- HAPUS DATABASE
DROP DATABASE train_ticket;