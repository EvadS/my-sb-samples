package com.se.sample.old;

import lombok.extern.slf4j.Slf4j;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
@Slf4j
class MysqlContainersManager {
	private static final String MYSQL_IMAGE_TAG = "mysql/mysql-server:8.0.31-1.2.10-server";
	private static final DockerImageName MYSQL_IMAGE = DockerImageName.parse(MYSQL_IMAGE_TAG)
			.asCompatibleSubstituteFor("mysql");

	@Container
	static final MySQLContainer<?> MYSQL_CONTAINER = new MySQLContainer<>(MYSQL_IMAGE);

	// start MYSQL_CONTAINER when this class is loaded
	static {
	MYSQL_CONTAINER.start();
	}
}
