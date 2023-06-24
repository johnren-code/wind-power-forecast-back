/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2023/5/21 21:33:51                           */
/*==============================================================*/
drop database if exists windpowerforecast; -- 数据库名不能用中划线-

create database windpowerforecast charset utf8;

use windpowerforecast;

drop table if exists history;

drop table if exists post;

drop table if exists reply;

drop table if exists user;

drop table if exists wind_farm;

/*==============================================================*/
/* Table: history                                               */
/*==============================================================*/
create table history
(
   id           bigint not null auto_increment,
   user_id              bigint not null,
   operation            varchar(255) not null,
   operation_time       datetime not null,
   export_url           varchar(255),
   picture_url          varchar(255),
   before_process_url   varchar(255),
   after_process_url    varchar(255),
   type                 int not null,
   is_deleted           tinyint not null default 0,
   primary key (id)
);

/*==============================================================*/
/* Table: post                                                  */
/*==============================================================*/
create table post
(
   id              bigint not null auto_increment,
   user_id              bigint not null,
   title                varchar(255) not null,
   content              varchar(2000),
   create_time          datetime not null,
   picture_url          varchar(255),
   is_deleted           tinyint not null default 0,
   primary key (id)
);

/*==============================================================*/
/* Table: reply                                                 */
/*==============================================================*/
create table reply
(
   id             bigint not null auto_increment,
   user_id              bigint not null,
   post_id              bigint not null,
   content              varchar(2000) not null,
   create_time          datetime not null,
   is_deleted           tinyint not null default 0,
   primary key (id)
);

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
create table user
(
   id              bigint not null auto_increment,
   name                 varchar(255) not null,
   gender               bool not null,
   age                  int,
   account              varchar(255) not null,
   password             varchar(255) not null,
   phone_number         bigint not null,
   email                varchar(255) not null,
   description          varchar(255),
   is_deleted           tinyint not null default 0,
   primary key (id)
);

/*==============================================================*/
/* Table: wind_farm                                             */
/*==============================================================*/
create table wind_farm
(
   id         bigint not null auto_increment,
   farm_id              bigint not null,
   location             varchar(255) not null,
   wind_speed_pred      float not null,
   wind_direction       int not null,
   temperature          float not null,
   humidity             int not null,
   pressure             int not null,
   prepower             int,
   wind_speed_real      float,
   round_power          int,
   yd15                 int,
   type                 int not null default 1,
   is_deleted           tinyint not null default 0,
   primary key (id)
);

alter table history add constraint FK_Relationship_3 foreign key (user_id)
      references user (id) on delete restrict on update restrict;

alter table post add constraint FK_Relationship_1 foreign key (user_id)
      references user (id) on delete restrict on update restrict;

alter table reply add constraint FK_Relationship_2 foreign key (post_id)
      references post (id) on delete restrict on update restrict;

alter table reply add constraint FK_Relationship_4 foreign key (user_id)
      references user (id) on delete restrict on update restrict;

