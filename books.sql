create database books;
use books;

set sql_safe_updates = 0;

drop table books;

create table admins(
	id BIGINT auto_increment,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL unique,
    password VARCHAR(255) NOT NULL,
    primary key(id)
);

create table users(
	id BIGINT auto_increment primary key,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL unique,
    password VARCHAR(255) NOT NULL,
    role varchar(100) NOT NULL,
    created_at datetime NOT NULL,
    updated_at datetime NOT NULL
);

drop table users;

insert into admins(name, email, password) values 
('Tu Nguyen', 'trantranchian@gmail.com', '123456'),
('Chi An', 'chiantrannguyen@gmail.com', '654321'),
('Hoang Vy', 'phanhoangvy@gmail.com', '654321'),
('Duong Minh', 'luuduongminh@gmail.com', '132654'),
('Thanh Hoang', 'lethanhhoang@gmail.com', '132654');

create table customers(
	id BIGINT auto_increment primary key,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL unique,
    password VARCHAR(255) NOT NULL,
    phone VARCHAR(10) NOT NULL,
    gender INT NOT NULL,
    address TEXT NOT NULL
);



insert into customers(name, email, password, phone, gender, address) values 
('Bao Chau', 'nguyenbaochau@gmail.com', 'guess?', '0123456789', 0, 'TP.HCM'),
('Hoang Dung', 'phanhoangdung@gmail.com', 'clgtbaochau?', '0123456789', 1, 'TP.HCM'),
('Thanh Tu', 'caothanhtu@gmail.com', 'nghiemtuchotao', '0987654321', 0, 'TP.HCM'),
('Le Tien', 'tranletien@gmail.com', 'datpasskieugiday', '01679460283', 1, 'HN'),
('Tong Tran', 'tongtrankhonkho@gmail.com', 'conanancut', '0868888666', 1, 'HN');

select * from customers;

create table genres (
	id BIGINT(15) auto_increment primary key,
    name VARCHAR(100) NOT NULL
);

insert into genres (name) VALUES 
('Horror'),
('Romance'),
('Humour'),
('Science'),
('Fiction');
select * from genres;
update genres set name = Manga where id = 5;



create table books (
	id BIGINT(15) auto_increment primary key,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    quantity INT(10) NOT NULL,
    genre_id bigint (10) NOT NULL,
    summary TEXT NOT NULL,
    price DECIMAL (10, 2) NOT NULL,
    page_number INT(11) NOT NULL,
    foreign key (genre_id) references genres(id)
);

select * from books;
select * from genres;
alter table books add foreign key (genre_id) references genres(id);

insert into books (title, author, quantity, genre_id, summary, price, page_number) VALUES 
('Doctor Hannibal', 'Guess', 548, 1, 'He eats humans', 98.94, 548),
('The Sheep', 'Wolf', 200, 3,'The Main Sheep dead', 55.34, 212), 
('Keyboard', 'Kate', 600, 1,'How to use your keyboard', 10.68, 120), 
('Three Fingers', 'Nam Phuong', 200, 4, 'only has 2 fingers', 30.99, 200), 
('The Fat Cat', 'Nam Phuong', 600, 1,'Fat cat but that is not cat', 82.54, 318),
('Apple Meo Meo', 'Kate Smith', 300, 2, 'A love story of two cats', 15.68, 140 );

UPDATE books SET summary = 'He is eaten by a ..' WHERE id = 1;
UPDATE books SET summary = 'An ant going crazy.' WHERE id = 2;
UPDATE books SET summary = 'Human tatse quite..' WHERE id = 3;
UPDATE books SET summary = 'Hunt monst in cave.' WHERE id = 4;
UPDATE books SET summary = 'Only has 2 fingers.' WHERE id = 5;
UPDATE books SET summary = 'Love story for cat.' WHERE id = 7;

select * from books;

create table orders(
	id BIGINT auto_increment primary key,
    date_buy DATE NOT NULL,
    status INT NOT NULL,
    customer_id BIGINT,
    admin_id BIGINT,
    payment_method INT NOT NULL,
    receiver_name VARCHAR(255) NOT NULL,
    receiver_phone VARCHAR(10) NOT NULL,
    receiver_address TEXT NOT NULL
);
alter table orders add foreign key (customer_id) references customers(id) on delete cascade;
alter table orders add foreign key (admin_id) references admins(id) on delete cascade;

select * from books;
insert into orders (date_buy, status, customer_id, admin_id, payment_method, receiver_name, receiver_phone, receiver_address) VALUES 
('2023-11-15', 1, 3, 2, 2, 'Hoang Dung', '0123456789', 'TP.HCM'),
('2023-11-16', 1, 2, 3, 3, 'Thanh Tu', '0987654321', 'TP.HCM' ),
('2023-11-17', 0, 1, 1, 3, 'Bao Chau', '0123456789', 'TP.HCM'),
('2023-11-18', 0, 4, 4, 1, 'Le Tien', '016794628', 'HN'),
('2023-11-19', 0, 3, 1, 1, 'Hoang Dung', '0123456789', 'TP.HCM');


create table order_details(
	book_id BIGINT NOT NULL,
    order_id BIGINT NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    quantity INT NOT NULL,
    primary key (book_id, order_id)
);
alter table order_details add foreign key (book_id) references books(id) on delete cascade;
alter table order_details add foreign key (order_id) references orders(id) on delete cascade;


insert into order_details(book_id, order_id, quantity, price) VALUES
(1, 3, 2, 31.36),
(2, 4, 4, 221.36),
(4, 2, 1, 10.68),
(6, 1, 3, 247.62),
(3, 5, 2, 21.36);

select * from order_details;
select * From orders;


select * from publishers;

SELECT books.id AS book_id, books.title AS book_title, books.quantity AS quantity, 
		order_details.quantity AS sold_book, (books.quantity - order_details.quantity) AS remain_book
FROM order_details
INNER JOIN books ON books.id = order_details.book_id;

SELECT orders.date_buy, order_details.quantity * order_details.price AS revenue
FROM order_details
INNER JOIN orders ON orders.id = order_details.order_id
GROUP BY orders.date_buy;



SELECT books.id AS book_id, books.title AS book_title, books.quantity, books.price, genres.name AS genre_name
FROM books 
INNER JOIN genres ON genres.id = books.genre_id
ORDER BY books.genre_id ASC;

/* SET 1*/
SELECT * FROM publishers; 

/* SET 2*/
INSERT INTO customers(name, email, password, phone, gender, address) VALUES ('Bao Chau', 'nguyenbaochau@gmail.com', 'guess?', '0123456789', 0, 'TP.HCM');

/*SET 3*/
UPDATE customers SET email = 'duongminh567@gmail.com' WHERE email = 'caothanhtu@gmail.com';

/* SET 4*/
/* SET 5*/
/* SET 6*/
/* SET 7*/

/* SET 8*/
CREATE VIEW customers_information as
    SELECT id, name, address, phone, email
    FROM customers;
    
/* SET 9*/
SELECT id, name, phone
FROM customers_information;

select * from books where id = 1;

UPDATE books
INNER JOIN order_details ON order_details.book_id= books.id
SET books.quantity = books.quantity - order_details.quantity
WHERE book_id = 1;

select customers.name AS customer_name, orders.*
from orders
INNER JOIN customers ON customers.id = orders.customer_id
WHERE customers.id = 3;

select * From orders;

