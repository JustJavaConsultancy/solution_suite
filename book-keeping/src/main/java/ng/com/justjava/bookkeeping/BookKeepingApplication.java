package ng.com.justjava.bookkeeping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication/*(scanBasePackages = {"ng.com.justjava.ruleEngine",
        "ng.com.justjava.domain","ng.com.justjava.db"})*/
@EnableFeignClients
public class BookKeepingApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookKeepingApplication.class, args);
    }

}
