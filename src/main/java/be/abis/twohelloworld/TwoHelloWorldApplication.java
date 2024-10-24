package be.abis.twohelloworld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class TwoHelloWorldApplication {

    public static void main(String[] args) {

        SpringApplication.run(TwoHelloWorldApplication.class, args);
    }
}
