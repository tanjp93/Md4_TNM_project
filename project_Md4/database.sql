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
orderTime Date,
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
create procedure PROC_User_getListUser()
begin
    select * from user;
end //
delimiter ;



delimiter //
create procedure PROC_User_login(userLogin varchar(255),pw varchar(255))
begin
    select * from user where userName like userLogin and password like pw;
end //


delimiter //
create  procedure  PROC_User_register(user varchar(255),pw varchar(255))
begin
    insert into user (userName, password) values (user, pw);
end //

delimiter //
create  procedure  PROC_User_findUserById (userId int)
begin
    select * from user where id=userId;
end //


delimiter //
create  procedure  PROC_User_findUserByName (userN varchar(255))
begin
    select * from user where userName like concat('%', userN, '%');
end //
delimiter ;


delimiter //
create procedure PROC_User_updateUser(idUpdate int,userNameUpdate varchar(255),passwordUpdate varchar(255),emailUpdate varchar(255),phoneUpdate varchar(255),addressUpdate varchar(255),avatarUpdate varchar(255),roleUpdate varchar(255))
begin
    update user set
userName=userNameUpdate,password=passwordUpdate,email=emailUpdate,phone=phoneUpdate,address=addressUpdate,avatar=avatarUpdate,role=roleUpdate where id=idUpdate;
end //

delimiter //
create procedure PROC_User_deleteUser(idDelete int)
begin
    delete from  user where id=idDelete;
end //
delimiter ;

insert into user (userName, password,role) values ("admin", "admin","admin");
insert into Category (categoryName) values ("Kính Cường Lực"), ("Kính An Toàn");
insert into product(productName,categoryId,price,stoke) 
values 
('kinh cuong luc',1,120000,200),
('Kính Low-E',2,200000,200),
('Kính Dán An Toàn',2,250000,200);


use md4_project_javaspring_tnmglass;
delimiter //
create PROCEDURE  PROC_Category_GetAll()
begin
    select * from category ;
end //

create PROCEDURE  PROC_Category_findById(idFind int)
begin
    select * from category where id=idFind;
end //
create PROCEDURE PROC_Category_save (newCategoryName varchar(255))
begin
    insert into category(categoryName) values (newCategoryName);
end //
create  PROCEDURE  PROC_Category_update(idUpdate int, categoryUpdate varchar(255))
begin
    UPDATE category set  categoryName=categoryUpdate where id=idUpdate;
end //

create  PROCEDURE PROC_Category_delete(idDel int)
begin
    delete from category where id=idDel;
end // 
delimiter ;

delimiter //
create PROCEDURE  PROC_Product_deleteProduct(idDel int)
begin 
    delete from product where id=idDel;
end //

delimiter //
create procedure PROC_Product_CreateNewProduct(productNameNew varchar(255),
                                                       categoryIdNew int,  priceNew mediumtext, stokeNew int,
                                                       titleNew varchar(255), imgNew varchar(255),
                                                       description1New varchar(255), description2New varchar(255),
                                                       description3New varchar(255), description4New varchar(255),
                                                        description5New varchar(255))
begin
    insert into product (productName, categoryId, price, stoke, title, img, description1, description2, description3, description4, description5)
        values ( productNameNew, categoryIdNew, priceNew, stokeNew, titleNew, imgNew, description1New, description2New, description3New, description4New, description5New);
end;

delimiter //
create procedure PROC_Product_findProductById(idDel int)
begin 
select * from product where id=idDel;
end //


delimiter //
create procedure PROC_Product_updateProduct(idUp int, productNameUp varchar(255), categoryIdUp int,  priceUp mediumtext,  stokeUp int,
  titleUp varchar(255),  imgUp varchar(255), description1Up varchar(255),  
  description2Up varchar(255),description3Up varchar(255), description4Up varchar(255),description5Up varchar(255))
 begin
 Update product set productName=productNameUp, categoryId=categoryIdUp, price=priceUp, stoke=stokeUp, title=titleUp,
 img=imgUp, description1=description1Up, description2=description2Up, description3=description3Up, description4=description4Up, description5=description5Up where id=idUp;
end //
                                                           
     delimiter //
create procedure  PROC_Product_findProductByName(productNameSer varchar(255))
begin
    select * from product where productName like concat('%'+productNameSer+'%');
end //                                                      


delimiter //
create procedure PROC_Product_getAllProduct()
begin
    select * from product ;
end //
delimiter ;

delete from certificates where id=1;

call PROC_Cer_save('chung chi a','khong co gi','anhcun.jpg',3);
select * from  certificates;

