insert into app_user(id,name,username,password) values (1,'Maks Pohuliailo','admin','admin');
insert into app_user(id,name,username,password) values (2,'Just User','user','user');

insert into app_user_roles(app_user_id,roles) values (1,'ADMIN');
insert into app_user_roles(app_user_id,roles) values (2,'USER');
insert into app_user_roles(app_user_id,roles) values (2,'USER');
