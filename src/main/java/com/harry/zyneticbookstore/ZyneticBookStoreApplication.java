package com.harry.zyneticbookstore;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.servers.Servers;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Book Store OPEN API",
                version = "1.0.0",
                description = "Book Store OPEN API Documentation"
        ),
        servers = @Server(
                url = "http://localhost:8080",
                description = "Book Store OPEN API url"
        )
)
public class ZyneticBookStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZyneticBookStoreApplication.class, args);
    }

}
