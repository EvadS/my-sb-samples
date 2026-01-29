create TABLE users (
                       id   INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                       first_name   VARCHAR(100) NOT NULL ,
                       last_name    VARCHAR(100) NOT NULL ,
                       email        VARCHAR(100) NOT NULL ,
                       password     VARCHAR(100) NOT NULL,
                       cell_number  VARCHAR(100) NOT NULL,
                       enabled      BOOLEAN Default 0
);

create TABLE role (
                      id     INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
                      name         VARCHAR(200)
);

create TABLE user_role (
                           user INTEGER,
                           role INTEGER
);