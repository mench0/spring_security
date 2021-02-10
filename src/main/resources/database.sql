CREATE TABLE spring_security.users
(
    id       BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name     VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
)
    ENGINE = InnoDB;

CREATE TABLE spring_security.roles
(
    id   BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    role VARCHAR(255) NOT NULL
)
    ENGINE = InnoDB;

CREATE TABLE spring_security.users_roles
(
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES spring_security.users (id),
    FOREIGN KEY (role_id) REFERENCES spring_security.roles (id)
)
    ENGINE = InnoDB;

INSERT INTO users
VALUES (1, 'user', '$2y$12$L0bR18D2b2QQThbgppAO6ObXQAn.BQ2ANHusJ1aF0qGCuo.S5bS62'); #password=1234

INSERT INTO roles VALUES (1, 'ROLE_ADMIN');
INSERT INTO roles VALUES (2, 'ROLE_USER');

INSERT INTO users_roles VALUES (1, 2);