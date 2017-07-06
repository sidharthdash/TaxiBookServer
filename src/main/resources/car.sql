drop TABLE car;
create table car (
  car_id INT PRIMARY KEY AUTO_INCREMENT,
  license_plate VARCHAR2(10) UNIQUE NOT NULL ,
  driver_id INT UNIQUE ,
  date_created TIMESTAMP AS CURRENT_TIMESTAMP NOT NULL,
  deleted BOOLEAN NOT NULL ,
  available BOOLEAN NOT NULL ,
  seat_count INT NOT NULL ,
  convertible BOOLEAN NOT NULL,
  rating INT ,
  engine_type VARCHAR2(20) NOT NULL);

INSERT INTO car (
  car_id ,
  license_plate ,
  driver_id  ,
  date_created,
  deleted  ,
  available  ,
  seat_count   ,
  convertible  ,
  rating ,
  engine_type)
VALUES (1,123,8,now(),FALSE ,FALSE ,4,FALSE ,5,'petrol');

INSERT INTO car (
  car_id ,
  license_plate ,
  date_created,
  deleted  ,
  available  ,
  seat_count   ,
  convertible  ,
  rating ,
  engine_type)
VALUES (2,223,now(),FALSE ,TRUE ,4,TRUE ,5,'petrol');
SELECT * FROM car;