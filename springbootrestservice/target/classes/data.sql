DROP TABLE IF EXISTS book;

CREATE TABLE book (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  title VARCHAR(250) NOT NULL,
  page_size INT NOT NULL,
  isbn VARCHAR(10),
  rating INT
  
);

CREATE TABLE bookseller (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  book_id INT,
  name VARCHAR(250) NOT NULL,
  address VARCHAR(250) NOT NULL,
  quantity INT,
  age INT  
);

ALTER TABLE bookseller ADD FOREIGN KEY (book_id) REFERENCES book(ID);

	INSERT INTO book (title, page_size, isbn, rating) VALUES  ('REST Doksi', 22, 434244, 7);
INSERT INTO book (title, page_size, isbn, rating) VALUES  ('Spring Boot', 25, 44444, 8);
INSERT INTO book (title, page_size, isbn, rating) VALUES  ('REST Doksi 4', 20, 434244, 7);
INSERT INTO book (title, page_size, isbn, rating) VALUES  ('Spring Boot 5', 25, 41111, 8);
INSERT INTO book (title, page_size, isbn, rating) VALUES  ('REST Doksi 54', 22, 422222, 7);
INSERT INTO book (title, page_size, isbn, rating) VALUES  ('Spring Boot ttew', 25, 43333, 8);

INSERT INTO bookseller (book_id, name, address, quantity, age) VALUES  (1, 'Bende seller', 'Csáky u 7a', 2322, 49);
INSERT INTO bookseller (book_id, name, address, quantity, age) VALUES  (1, 'Saskői seller', 'Felsőszéktó', 23222, 43);
INSERT INTO bookseller (book_id, name, address, quantity, age) VALUES  (1, 'Bende Feri seller', 'Csáky u 7a', 232233, 52);
INSERT INTO bookseller (book_id, name, address, quantity, age) VALUES  (1, 'Kulman seller', 'Csáky u 7a', 2322, 50);
INSERT INTO bookseller (book_id, name, address, quantity, age) VALUES  (2, 'Bende seller', 'Csáky u 7a', 2322, 49);