# CURDAPIProducts
```sql
create database productsdb;

use productsdb;

CREATE TABLE products (
  id bigint primary key auto_increment not null,
  name varchar(100) not null,
  description varchar(255) not null,
  price double not null, 
  istock int not null
  )

 insert into products (name, description, price, stock) values ("Yogurt", "El mejor yogurt del mundo", 1700, 1000);
 insert into products (name, description, price, stock) values ("Pan", "El mejor pan del mundo", 1000, 500);
 insert into products (name, description, price, stock) values ("Queso", "El mejor queso del mundo", 20000, 250);
 insert into products (name, description, price, stock) values ("Galleta", "La mejor galleta del mundo", 500, 125);
