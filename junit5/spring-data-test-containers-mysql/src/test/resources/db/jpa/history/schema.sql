CREATE TABLE `history` (
  `document_id` varchar(255) NOT NULL,
  `user_email` varchar(255) NOT NULL,
  `document_date` datetime(6) DEFAULT NULL,
  `document_name` varchar(255) DEFAULT NULL,
  `document_number` varchar(255) DEFAULT NULL,
  `document_type` varchar(255) DEFAULT NULL,
  `document_view_date` datetime(6) DEFAULT NULL,
  `publisher` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `status_color` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`document_id`,`user_email`)
);