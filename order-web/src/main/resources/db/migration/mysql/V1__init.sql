# if create table manually, the table name must be lower case, because hibernate auto create table name is lower,
# if you create table name by upper case, the table will be duplicated. -- test environment: MySQL

create table sys_user(
  id bigint not null,
  user_name VARCHAR(255),
  password VARCHAR(255),
  enable bit(1) DEFAULT 1,
  CONSTRAINT USER_PK PRIMARY KEY (id)
);

create table sys_role(
  id bigint not null,
  name varchar(255),
  constraint role_pk primary key (id)
);

create table sys_user_roles(
  sys_user_id bigint not null,
  roles_id bigint not null
);

insert into sys_user(id,user_name,password,enable) values (1,'super','5f4dcc3b5aa765d61d8327deb882cf99',1);
insert into sys_user(id,user_name,password,enable) values (2,'normal','5f4dcc3b5aa765d61d8327deb882cf99',1);

insert into sys_role(id,name) values (1,'role_admin');
insert into sys_role(id,name) values (2,'role_user');

insert into sys_user_roles(sys_user_id,roles_id) values(1,1);
insert into sys_user_roles(sys_user_id,roles_id) values(2,2);