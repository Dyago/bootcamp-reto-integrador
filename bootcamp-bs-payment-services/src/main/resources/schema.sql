drop table if exists product;
create table product (
id int auto_increment PRIMARY KEY, 
product_code varchar(4), 
product_name varchar(250), 
channel_code varchar(50));

drop table if exists transaction;
create table transaction (
id int auto_increment PRIMARY KEY, 
product_code varchar(4),
supply_number varchar(20),
amount decimal,
transaction_date datetime
);