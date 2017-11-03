package io.egen;


import io.egen.entity.User;
import io.egen.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Configuration
@SpringBootApplication
@EnableAutoConfiguration
@EnableCaching
@ComponentScan
public class Application {

    @Autowired
    private UserRepository userCache;

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.run(args);
    }

    @PostConstruct
    public void loadUsers() {
        User user1 = new User();
        user1.setFirstName("Bruce");
        user1.setLastName("Wayne");
        user1.setEmail("batman@wayneenterprises.com");
        Map<String, Object> user1Map = new HashMap<>();
        user1Map.put("state", "NY");
        user1.setAddress(user1Map);

        User user2 = new User();
        user2.setFirstName("Clark");
        user2.setLastName("Kent");
        user2.setEmail("superman@kryptonian.com");
        Map<String, Object> user2Map = new HashMap<>();
        user2Map.put("state", "IL");
        user2.setAddress(user2Map);

        userCache.create(user1);
        userCache.create(user2);
    }
}
