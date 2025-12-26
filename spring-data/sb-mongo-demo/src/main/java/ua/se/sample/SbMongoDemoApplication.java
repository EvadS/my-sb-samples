package ua.se.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "ua.se.sample.repository")
public class SbMongoDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SbMongoDemoApplication.class, args);
    }

}
