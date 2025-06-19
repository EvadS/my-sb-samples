SELECT *
FROM flyway_demo.bookmarks;
CREATE TABLE `bookmarks`
(
    `id`         bigint   NOT NULL AUTO_INCREMENT,
    `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `title`      varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
    `url`        varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
    `created_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
)
