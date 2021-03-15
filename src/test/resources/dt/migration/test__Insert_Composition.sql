DROP SEQUENCE IF EXISTS composition_id_sec_test;
CREATE SEQUENCE composition_id_sec_test INCREMENT BY 1 START WITH 10;

DROP SEQUENCE IF EXISTS usr_id_sec_test;
CREATE SEQUENCE usr_id_sec_test INCREMENT BY 1 START WITH 20;

--insert user
INSERT INTO usr (id, name, email, password, active)
VALUES (nextval('usr_id_sec_test'), 'userNameTest', 'user-email-test@mail.com', '1', 't');

--insert compositions
INSERT INTO composition (id,date_update,name,short_description,user_id)
VALUES (nextval('composition_id_sec_test'), '2008-01-01 00:00:01', 'Composition name first', 'short', currval('usr_id_sec_test'));
INSERT INTO composition (id,date_update,name,short_description,user_id)
VALUES (nextval('composition_id_sec_test'),'2008-01-01 00:00:02','Composition name','short',currval('usr_id_sec_test'));
INSERT INTO composition (id,date_update,name,short_description,user_id)
VALUES (nextval('composition_id_sec_test'),'2008-01-01 00:00:03','Composition name','short',currval('usr_id_sec_test'));
INSERT INTO composition (id,date_update,name,short_description,user_id)
VALUES (nextval('composition_id_sec_test'),'2008-01-01 00:00:04','Composition name','short',currval('usr_id_sec_test'));
INSERT INTO composition (id,date_update,name,short_description,user_id)
VALUES (nextval('composition_id_sec_test'),'2008-01-01 00:00:05','Composition name','short',currval('usr_id_sec_test'));
INSERT INTO composition (id,date_update,name,short_description,user_id)
VALUES (nextval('composition_id_sec_test'),'2008-01-01 00:00:06','Composition name','short',currval('usr_id_sec_test'));
INSERT INTO composition (id,date_update,name,short_description,user_id)
VALUES (nextval('composition_id_sec_test'),'2008-01-01 00:00:07','Composition name','short',currval('usr_id_sec_test'));
INSERT INTO composition (id,date_update,name,short_description,user_id)
VALUES (nextval('composition_id_sec_test'),'2008-01-01 00:00:08','Composition name','short',currval('usr_id_sec_test'));
INSERT INTO composition (id,date_update,name,short_description,user_id)
VALUES (nextval('composition_id_sec_test'),'2008-01-01 00:00:09','Composition name','short',currval('usr_id_sec_test'));
INSERT INTO composition (id,date_update,name,short_description,user_id)
VALUES (nextval('composition_id_sec_test'),'2008-01-01 00:00:10','Composition name','short',currval('usr_id_sec_test'));
INSERT INTO composition (id,date_update,name,short_description,user_id)
VALUES (nextval('composition_id_sec_test'),'2008-01-01 00:00:11','Composition name','short',currval('usr_id_sec_test'));
INSERT INTO composition (id,date_update,name,short_description,user_id)
VALUES (nextval('composition_id_sec_test'),'2008-01-01 00:00:12','Composition name','short',currval('usr_id_sec_test'));
INSERT INTO composition (id,date_update,name,short_description,user_id)
VALUES (nextval('composition_id_sec_test'),'2008-01-01 00:00:13','Composition name','short',currval('usr_id_sec_test'));
INSERT INTO composition (id,date_update,name,short_description,user_id)
VALUES (nextval('composition_id_sec_test'),'2008-01-01 00:00:14','Composition name','short',currval('usr_id_sec_test'));
INSERT INTO composition (id,date_update,name,short_description,user_id)
VALUES (nextval('composition_id_sec_test'),'2008-01-01 00:00:15','Composition name','short',currval('usr_id_sec_test'));
INSERT INTO composition (id,date_update,name,short_description,user_id)
VALUES (nextval('composition_id_sec_test'),'2008-01-01 00:00:16','Composition name','short',currval('usr_id_sec_test'));
INSERT INTO composition (id,date_update,name,short_description,user_id)
VALUES (nextval('composition_id_sec_test'),'2008-01-01 00:00:17','Composition name','short',currval('usr_id_sec_test'));