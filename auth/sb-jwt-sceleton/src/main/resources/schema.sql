CREATE TABLE  role_table
(
    id   INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(45) NULL,
    PRIMARY KEY (id),
    UNIQUE INDEX name_UNIQUE (name ASC)
)

CREATE TABLE  user_table
(
    id       INT NOT NULL AUTO_INCREMENT,
    login    VARCHAR(45) NULL,
    password VARCHAR(255) NULL,
    role_id  INT NULL,
    PRIMARY KEY (id),
    UNIQUE INDEX login_UNIQUE (login ASC),
    UNIQUE INDEX password_UNIQUE (password ASC),
    INDEX       (role_id ASC)
    CONSTRAINT fk_user_table_1
        FOREIGN KEY (role_id)
            REFERENCES secureDb.role_table (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)


