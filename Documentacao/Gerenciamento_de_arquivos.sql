CREATE DATABASE gda;

use gda;

CREATE TABLE users(
	userID int auto_increment not null primary key,
	userName varchar(50) not null,
	userEmail varchar(50) not null,
	userPhone bigint(11) not null,
	userLogin varchar(10) not null,
	userPassword varchar(20) not null,
	userRoot boolean not null
);

CREATE TABLE archives(
	archiveID int auto_increment not null primary key,
	archive mediumblob not null,
	archiveName varchar(100) not null,
	archiveType varchar(30) not null,
	archiveDescription varchar (200),
	archiveDate date,
    user_userID int,
    agencia_agenciaID int
);

CREATE TABLE regional(
	regionalID int auto_increment not null primary key,
	regionalName varchar (30) not null
);

CREATE TABLE agencia(
	agenciaID int auto_increment not null primary key,
	agenciaName varchar (30) not null,
    regional_regionalID int
);

alter table agencia
add constraint regional_regionalID
foreign key (regional_regionalID)
references regional(regionalID);

alter table archives
add constraint user_userID
foreign key (user_userID)
references users(userID);

alter table archives
add constraint agencia_agenciaID
foreign key (agencia_agenciaID)
references agencia(agenciaID);

insert into users(userName, userEmail, userPhone, userLogin, userPassword, userRoot)
values ('Alisson', 'Alisson@uninter', 41998168456, 'alisson', 'alisson123', 0);

insert into users(userName, userEmail, userPhone, userLogin, userPassword, userRoot)
values ('Jhony', 'Jhony@uninter', 41999856758, 'jhony', 'jhony123', 0);

insert into users(userName, userEmail, userPhone, userLogin, userPassword, userRoot)
values ('Root', 'Root@uninter', 4132346256, 'Root', 'Root123', 1);

select * from users;