CREATE TABLE Usuario (
  id int NOT NULL AUTO_INCREMENT, 
  name varchar(255) NOT NULL,
  password varchar(255) NOT NULL,
  PRIMARY KEY (id)
);

INSERT INTO Usuario VALUES ('0001', 'admin', '123');
select * from usuario u 