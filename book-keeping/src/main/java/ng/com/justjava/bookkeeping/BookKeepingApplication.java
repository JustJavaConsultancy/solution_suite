package ng.com.justjava.bookkeeping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/*@EnableJpaRepositories(basePackages ={"ng.com.justjava.db"} )*/
@SpringBootApplication/*(scanBasePackages = {"ng.com.justjava.ruleEngine",
        "ng.com.justjava.domain","ng.com.justjava.db"})*/
public class BookKeepingApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookKeepingApplication.class, args);
    }

}
