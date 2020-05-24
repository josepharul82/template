INSERT INTO T_USER(user_pk, user_first_name, user_last_name, user_mail, user_pk_creation, user_pk_modification, user_creation_date, user_modification_date, user_version)
VALUES(1,'Luffy','MUGIWARA', 'luffy.mugiwara@onepiece.fr' , 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1);

INSERT INTO T_USER(user_pk, user_first_name, user_last_name, user_mail, user_pk_creation, user_pk_modification, user_creation_date, user_modification_date, user_version)
VALUES(2,'Zoro','RORONOA', 'zoro.roronoa@onepiece.fr' , 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1);

INSERT INTO T_ROLE(role_pk, role_name, user_pk_creation, user_pk_modification, role_creation_date, role_modification_date, role_version)
VALUES(1, 'CAPTAIN', 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1);

INSERT INTO T_ROLE(role_pk, role_name, user_pk_creation, user_pk_modification, role_creation_date, role_modification_date, role_version)
VALUES(2, 'SABER', 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1);

INSERT INTO T_POST(post_pk, post_content, user_pk_creation, user_pk_modification, post_creation_date, post_modification_date, post_version, user_pk)
VALUES(1, 'Become the king pirate', 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 1);

INSERT INTO T_POST(post_pk, post_content, user_pk_creation, user_pk_modification, post_creation_date, post_modification_date, post_version, user_pk)
VALUES(2, 'Become the king pirate', 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 2);

INSERT INTO T_USER_ROLE(user_pk, role_pk)
VALUES (1, 1);

INSERT INTO T_USER_ROLE(user_pk, role_pk)
VALUES (2, 2);