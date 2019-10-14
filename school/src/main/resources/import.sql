INSERT INTO tbl_role(id,role_name) VALUES (1,'ADMIN');
INSERT INTO tbl_role(id,role_name) VALUES (2,'USER');
INSERT INTO tbl_role(id,role_name) VALUES (3,'NOVICE_USER');

INSERT INTO config_menu(id,menu_order,icon,NAME,url)VALUES (1,10,'fa fa-fw fa-home','Home','#');
INSERT INTO config_menu(id,menu_order,icon,NAME,url)VALUES (2,20,'fa fa-fw fa-user','Account','#');
INSERT INTO config_menu(id,menu_order,icon,NAME,url)VALUES (3,30,'fa fa-fw fa-users','User managment','#');
INSERT INTO config_menu(id,menu_order,icon,NAME,url)VALUES (4,40,'fa fa-fw fa-file-powerpoint-o','Tutorial','userMangmentPage');
INSERT INTO config_menu(id,menu_order,icon,NAME,url)VALUES (5,50,'fa fa-fw fa-btc','Genarel Ladger','userMangmentPage');
INSERT INTO config_menu(id,menu_order,icon,NAME,url)VALUES (6,60,'fa fa-fw fa-list-alt','Product','#');
INSERT INTO config_menu(id,menu_order,icon,NAME,url)VALUES (7,70,'fa fa-fw fa-question-circle','Help','userMangmentPage');
INSERT INTO config_menu(id,menu_order,icon,NAME,url)VALUES (8,80,'fa fa-fw fa-legal','Legal','#');
INSERT INTO config_menu(id,menu_order,icon,NAME,url)VALUES (9,90,'fa fa-fw fa-comment','Frequently Asked Questions','userMangmentPage');
INSERT INTO config_menu(id,menu_order,icon,NAME,url)VALUES (10,71,'fa fa-fw fa-wrench','Setting','#');

INSERT INTO config_sub_menu(id,icon,NAME,url, menu_id) VALUES (1,'fa fa-cog fa-fw fa-spin','Add New user','#',3);
INSERT INTO config_sub_menu(id,icon,NAME,url, menu_id) VALUES (2,'fa fa-caret-right','View User','#',3);
INSERT INTO config_sub_menu(id,icon,NAME,url, menu_id) VALUES (3,'fa fa-caret-right','Profile','#',2);
INSERT INTO config_sub_menu(id,icon,NAME,url, menu_id) VALUES (4,'fa fa-caret-right','Activity Timeline','#',2);
INSERT INTO config_sub_menu(id,icon,NAME,url, menu_id) VALUES (5,'fa fa-caret-right','User Log','#',2);
INSERT INTO config_sub_menu(id,icon,NAME,url, menu_id) VALUES (6,'fa fa-caret-right','Reset Password','#',2);
INSERT INTO config_sub_menu(id,icon,NAME,url, menu_id) VALUES (7,'fa fa-caret-right','Pricing Tables','#',6);
INSERT INTO config_sub_menu(id,icon,NAME,url, menu_id) VALUES (8,'fa fa-caret-right','Current Product','#',6);
INSERT INTO config_sub_menu(id,icon,NAME,url, menu_id) VALUES (9,'fa fa-caret-right','Upcoming Product','#',6);
INSERT INTO config_sub_menu(id,icon,NAME,url, menu_id) VALUES (10,'fa fa-caret-right','Privacy Policy','#',8);
INSERT INTO config_sub_menu(id,icon,NAME,url, menu_id) VALUES (11,'fa fa-caret-right','Terms and Conditions','#',8);
INSERT INTO config_sub_menu(id,icon,NAME,url, menu_id) VALUES (12,'fa fa-caret-right','Dashboard','/',1);
INSERT INTO config_sub_menu(id,icon,NAME,url, menu_id) VALUES (13,'fa fa-caret-right','Invoice','#',1);
INSERT INTO config_sub_menu(id,icon,NAME,url, menu_id) VALUES (14,'fa fa-caret-right','Payment','#',1);
INSERT INTO config_sub_menu(id,icon,NAME,url, menu_id) VALUES (15,'fa fa-caret-right','Notice','#',1);
INSERT INTO config_sub_menu(id,icon,NAME,url, menu_id) VALUES (16,'fa fa-caret-right','Menu','menu',10);
INSERT INTO config_sub_menu(id,icon,NAME,url, menu_id) VALUES (17,'fa fa-caret-right','Frequently Asked Questions','faqentry', 10);

INSERT INTO tbl_user(id, email, first_name, last_name, password, dob) VALUES (1 , 'panthaistiaque@gmail.com', 'Pantha', 'Istiaque', '$2a$10$8IJd6PdtcJk33XGgKPRk5uUQ08K1N/tsy/zCbQByH1POwRUkIWSD6', '1998-07-31');
INSERT INTO tbl_user(id, email, first_name, last_name, password, dob) VALUES (2 , 'mdistiaque56@yahoo.com', 'Hossain', 'Mohammad', '$2a$10$8IJd6PdtcJk33XGgKPRk5uUQ08K1N/tsy/zCbQByH1POwRUkIWSD6', '1991-11-19');

INSERT  INTO  MAP_USER_ROLE ( USER_ID, ROLE_ID  ) VALUES ( 1,1)
INSERT  INTO  MAP_USER_ROLE ( USER_ID, ROLE_ID  ) VALUES ( 1,2)
INSERT  INTO  MAP_USER_ROLE ( USER_ID, ROLE_ID  ) VALUES ( 2,2)


insert into MAP_ROLE_MENU(ROLE_ID,MENU_ID ) values(1,1);
insert into MAP_ROLE_MENU(ROLE_ID,MENU_ID ) values(1,10);
insert into MAP_ROLE_MENU(ROLE_ID,MENU_ID ) values(2,1);