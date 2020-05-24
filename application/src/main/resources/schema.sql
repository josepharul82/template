-- -----------------------------------------------------
-- Table t_user
-- -----------------------------------------------------
CREATE TABLE  t_user (
  user_pk INT NOT NULL AUTO_INCREMENT,
  user_first_name VARCHAR2(255) NOT NULL,
  user_last_name VARCHAR2(255) NOT NULL,
  user_mail VARCHAR2(255) NOT NULL,
  user_pk_creation INT NOT NULL,
  user_pk_modification INT NULL,
  user_creation_date DATETIME NOT NULL,
  user_modification_date DATETIME NULL,
  user_version INT NOT NULL,
  PRIMARY KEY (user_pk));

-- -----------------------------------------------------
-- Table t_role
-- -----------------------------------------------------
CREATE TABLE  t_role (
  role_pk INT NOT NULL AUTO_INCREMENT,
  role_name VARCHAR2(255) NOT NULL,
  user_pk_creation INT NOT NULL,
  user_pk_modification INT NULL,
  role_creation_date DATETIME NOT NULL,
  role_modification_date DATETIME NULL,
  role_version INT NOT NULL,
  PRIMARY KEY (role_pk),
  CONSTRAINT c_role_01_fk FOREIGN KEY (user_pk_creation) REFERENCES t_user (user_pk),
  CONSTRAINT c_role_02_fk FOREIGN KEY (user_pk_modification) REFERENCES t_user (user_pk));

-- -----------------------------------------------------
-- Table t_user_role
-- -----------------------------------------------------
CREATE TABLE  t_user_role (
  user_pk INT NOT NULL,
  role_pk INT NOT NULL,
  PRIMARY KEY (user_pk, role_pk),
  CONSTRAINT c_user_role_01_fk FOREIGN KEY (user_pk) REFERENCES t_user (user_pk),
  CONSTRAINT c_user_role_02_fk FOREIGN KEY (role_pk) REFERENCES t_role (role_pk));

-- -----------------------------------------------------
-- Table t_post
-- -----------------------------------------------------
CREATE TABLE  t_post (
  post_pk INT NOT NULL AUTO_INCREMENT,
  post_content VARCHAR2(255) NOT NULL,
  user_pk INT NOT NULL,
  user_pk_creation INT NOT NULL,
  user_pk_modification INT NULL,
  post_creation_date DATETIME NOT NULL,
  post_modification_date DATETIME NULL,
  post_version INT NOT NULL,
  PRIMARY KEY (post_pk),
  CONSTRAINT c_post_01_fk FOREIGN KEY (user_pk) REFERENCES t_user (user_pk),
  CONSTRAINT c_post_02_fk FOREIGN KEY (user_pk_creation) REFERENCES t_user (user_pk),
  CONSTRAINT c_post_03_fk FOREIGN KEY (user_pk_modification) REFERENCES t_user (user_pk));


