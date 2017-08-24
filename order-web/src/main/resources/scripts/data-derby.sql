insert into SYS_USER(id,USER_NAME,password) values (1,'zj','zj');
insert into SYS_USER(id,USER_NAME,password) values (2,'eric','eric');

insert into SYS_ROLE(id,name) VALUES (1,'ROLE_ADMIN');
insert into SYS_ROLE(id,name) VALUES (2,'ROLE_USER');

insert into SYS_USER_ROLES(SYS_USER_ID,ROLES_ID) values(1,1);
insert into SYS_USER_ROLES(SYS_USER_ID,ROLES_ID) values(2,2);