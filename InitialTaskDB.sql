create database if not exists InitialTaskDB
character set utf8;

use InitialTaskDB;

drop table if exists `employees`;
drop table if exists `department`;

create table if not exists `department`(
`id` int not null auto_increment,
`name` varchar(40) not null unique,
primary key(id)
);
insert into `department` values(default,'Development');
insert into `department` values(default,'Testing');
insert into `department` values(default,'Managment');
insert into `department` values(default,'Managme');


create table if not exists `employees`(
`id` int not null auto_increment,
`name` varchar(30) not null,
`surname` varchar(30) not null,
`experience` int not null,
`email` varchar(40) unique,
`dateOfBirth` date not null,
`departmentId` int,
primary key(id),
foreign key (departmentId) references `department`(id)
	on delete restrict on update restrict
);
insert into `employees` values(default,'Egor','Pupkin',1,'ZhyzhkoEgor@gmail.com','1992-12-26',1);
insert into `employees` values(default,'Vasua','Ivanov',2,'Vasua@gmail.com','1994-06-12',2);
insert into `employees` values(default,'Sveta','Belova',0,'Sveta18@gmail.com','1996-04-02',3);


select * from employees;
select * from department;