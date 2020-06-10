http://localhost:8080/swagger-ui.html
http://localhost:8080/console/
jdbc:h2:mem:users-app;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE

{
  "pageParamDTO": null,
  "userDTO": {
    "firstName": "Luffy",
    "id": null,
    "lastName": null,
    "mail": null,
    "roleList": null,
    "version": null
  }
}
Configure template as context-root in tomcat
Add tomcat 9.0.35
Add jar h2 to lib tomcat

postgresql
----------
CREATE TABLE  templatedb.t_user (
  user_pk SERIAL PRIMARY KEY,
  user_first_name varchar(255) NOT NULL,
  user_last_name varchar(255) NOT NULL,
  user_mail varchar(255) NOT NULL,
  user_pk_creation integer NOT NULL,
  user_pk_modification integer NULL,
  user_creation_date timestamp NOT NULL,
  user_modification_date timestamp NULL,
  user_version integer NOT NULL);

  CREATE TABLE  templatedb.t_role (
    role_pk SERIAL PRIMARY KEY,
    role_name varchar(255) NOT NULL,
    user_pk_creation integer NOT NULL,
    user_pk_modification integer NULL,
    role_creation_date timestamp NOT NULL,
    role_modification_date timestamp NULL,
    role_version integer NOT NULL,
    CONSTRAINT c_role_01_fk FOREIGN KEY (user_pk_creation) REFERENCES templatedb.t_user (user_pk),
    CONSTRAINT c_role_02_fk FOREIGN KEY (user_pk_modification) REFERENCES templatedb.t_user (user_pk));


CREATE TABLE  templatedb.t_user_role (
  user_pk integer NOT NULL,
  role_pk integer NOT NULL,
  PRIMARY KEY (user_pk, role_pk),
  CONSTRAINT c_user_role_01_fk FOREIGN KEY (user_pk) REFERENCES templatedb.t_user (user_pk),
  CONSTRAINT c_user_role_02_fk FOREIGN KEY (role_pk) REFERENCES templatedb.t_role (role_pk));

  CREATE TABLE  templatedb.t_post (
    post_pk SERIAL PRIMARY KEY,
    post_content varchar(255) NOT NULL,
    user_pk integer NOT NULL,
    user_pk_creation integer NOT NULL,
    user_pk_modification integer NULL,
    post_creation_date timestamp NOT NULL,
    post_modification_date timestamp NULL,
    post_version integer NOT NULL,
    CONSTRAINT c_post_01_fk FOREIGN KEY (user_pk) REFERENCES templatedb.t_user (user_pk),
    CONSTRAINT c_post_02_fk FOREIGN KEY (user_pk_creation) REFERENCES templatedb.t_user (user_pk),
    CONSTRAINT c_post_03_fk FOREIGN KEY (user_pk_modification) REFERENCES templatedb.t_user (user_pk));


    INSERT INTO templatedb.T_USER(user_pk, user_first_name, user_last_name, user_mail, user_pk_creation, user_pk_modification, user_creation_date, user_modification_date, user_version)
    VALUES(1,'Luffy','MUGIWARA', 'luffy.mugiwara@onepiece.fr' , 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1);

    INSERT INTO templatedb.T_USER(user_pk, user_first_name, user_last_name, user_mail, user_pk_creation, user_pk_modification, user_creation_date, user_modification_date, user_version)
    VALUES(2,'Zoro','RORONOA', 'zoro.roronoa@onepiece.fr' , 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1);

    INSERT INTO templatedb.T_USER(user_pk, user_first_name, user_last_name, user_mail, user_pk_creation, user_pk_modification, user_creation_date, user_modification_date, user_version)
    VALUES(3,'Sanji','VINSMOKE', 'sanji.vinsmoke@onepiece.fr' , 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1);



    INSERT INTO templatedb.T_ROLE(role_pk, role_name, user_pk_creation, user_pk_modification, role_creation_date, role_modification_date, role_version)
    VALUES(1, 'CAPTAIN', 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1);

    INSERT INTO templatedb.T_ROLE(role_pk, role_name, user_pk_creation, user_pk_modification, role_creation_date, role_modification_date, role_version)
    VALUES(2, 'SABER', 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1);

    INSERT INTO templatedb.T_POST(post_pk, post_content, user_pk_creation, user_pk_modification, post_creation_date, post_modification_date, post_version, user_pk)
    VALUES(1, 'Become the king pirate', 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 1);

    INSERT INTO templatedb.T_POST(post_pk, post_content, user_pk_creation, user_pk_modification, post_creation_date, post_modification_date, post_version, user_pk)
    VALUES(2, 'Become the king pirate', 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 2);

    INSERT INTO templatedb.T_USER_ROLE(user_pk, role_pk)
    VALUES (1, 1);

    INSERT INTO templatedb.T_USER_ROLE(user_pk, role_pk)
    VALUES (2, 2);