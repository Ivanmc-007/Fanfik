DROP SEQUENCE IF EXISTS user_id_sec;
CREATE SEQUENCE user_id_sec INCREMENT BY 1 START WITH 10;
-- insert user
INSERT INTO usr (id, name, email, password, active) VALUES (nextval('user_id_sec'), 'userNameTest', 'user-email-test@mail.com', '1', 't');
-- insert roles for user
INSERT INTO user_role (user_id, roles) VALUES (currval('user_id_sec'), 'USER');