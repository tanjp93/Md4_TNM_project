drop database md4_project_javaSpring_TNMGlass;
create database md4_project_javaSpring_TNMGlass;
use md4_project_javaSpring_TNMGlass;
create table Category(
id int primary key auto_increment,
categoryName varchar(255) not null
);
create table `User`(
 id int primary key auto_increment,
 userName varchar(255) not null ,
 `password` varchar(255) not null ,
 email varchar(255) ,
 phone varchar(255) ,
 address varchar(255) ,
 avatar varchar(255) ,
 role varchar(255) default "user"
);


create table product(
id int primary key auto_increment,
productName varchar(255) not null,
categoryId int ,
price long,
stoke int,
title varchar(255),
img varchar(255),
description1 varchar(255),
description2 varchar(255),
description3 varchar(255),
description4 varchar(255),
description5 varchar(255),
foreign key(categoryId) references Category(id)
);

create table `order`(
id int primary key auto_increment,
userId int not null ,
productId int not null ,
quantity int not null ,
oderPay long,
foreign key (userId) references `User`(id),
foreign key (productId) references product(id)
);

create table `ListOrder`(
id int primary key auto_increment,
orderId int ,
Total  long ,
foreign key(orderId) references `order`(id)
);

delimiter //
create procedure PROC_getListUser()
begin
    select * from user;
end //
delimiter ;

delimiter //
create procedure PROC_login(user varchar(255),pw varchar(255))
begin
    select * from user where userName like user and password like pw;
end //


delimiter //
create  procedure  PROC_register(user varchar(255),pw varchar(255))
begin
    insert into user (userName, password) values (user, pw);
end //

delimiter //
create  procedure  PROC_findUserById (userId int)
begin
    select * from user where id=userId;
end //
delimiter //
create  procedure  PROC_findUserByName (userN varchar(255))
begin
    select * from user where userName like concat('%', userN, '%');
end //
delimiter ;

delimiter //
create procedure PROC_updateUser(idUpdate int,userNameUpdate varchar(255),passwordUpdate varchar(255),emailUpdate varchar(255),phoneUpdate varchar(255),addressUpdate varchar(255),avatarUpdate varchar(255),roleUpdate varchar(255))
begin
    update user set
userName=userNameUpdate,password=passwordUpdate,email=emailUpdate,phone=phoneUpdate,address=addressUpdate,avatar=avatarUpdate,role=roleUpdate where id=idUpdate;
end //
delimiter //
create procedure PROC_deleteUser(idDelete int)
begin
    delete from  user where id=idDelete;
end //
delimiter ;

insert into user (userName, password,role) values ("admin", "admin","admin");