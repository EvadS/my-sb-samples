CREATE TABLE `books` (
                         `id` bigint NOT NULL AUTO_INCREMENT,
                         `isbn` varchar(255) COLLATE utf8mb3_bin NOT NULL,
                         `name` varchar(255) COLLATE utf8mb3_bin NOT NULL,
                         PRIMARY KEY (`id`),
                         UNIQUE KEY `UK_kibbepcitr0a3cpk3rfr7nihn` (`isbn`)
);

CREATE TABLE `history` (
                           `document_date` datetime(6) DEFAULT NULL,
                           `document_view_date` datetime(6) DEFAULT NULL,
                           `document_id` varchar(255) COLLATE utf8mb3_bin NOT NULL,
                           `document_name` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
                           `document_number` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
                           `document_type` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
                           `publisher` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
                           `status` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
                           `status_color` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
                           `user_email` varchar(255) COLLATE utf8mb3_bin NOT NULL,
                           PRIMARY KEY (`document_id`,`user_email`)
);