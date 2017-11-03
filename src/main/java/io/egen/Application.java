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

        User rock = new User();
        rock.setFirstName("Dwayne");
        rock.setLastName("JohnSon");
        rock.setEmail("rock@WorldWideWrrestling.com");
        Map<String, Object> rockMap = new HashMap<>();
        rockMap.put("myadress", "WWF");
        rock.setAddress(rockMap);

        User obama = new User();
        obama.setFirstName("Barack");
        obama.setLastName("Hussein Obama");
        obama.setEmail("obama@YourPresident.com");
        Map<String, Object> obmamaMap = new HashMap<>();
        obmamaMap.put("myadress", "White House");
        obama.setAddress(obmamaMap);

        userCache.update(rock);
        userCache.update(obama);
    }
}
