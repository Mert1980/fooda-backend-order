package be.fooda.backend.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class FoodaOrderApp {
    public static void main(String[] args) {
        SpringApplication.run(FoodaOrderApp.class, args);
    }
}
