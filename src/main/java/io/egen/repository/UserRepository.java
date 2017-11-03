package io.egen.repository;

import io.egen.entity.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public class UserRepository {

    private Set<User> userCache = new HashSet<>();

    public boolean hasUser(User user) {
        return userCache.contains(user);
    }

    public User findById(String userId) {
        Optional<User> userOptional = userCache.stream()
                                               .filter(user -> user.getId()
                                                                   .equals(userId))
                                               .findFirst();

        if (userOptional.isPresent()) {
            return userOptional.get();
        }

        throw new RuntimeException("User not found");
    }

    public boolean update(User user) {
        return userCache.add(user);
    }

    public boolean remove(String userId) {

        Optional<User> userOptional = userCache.stream()
                                               .filter(user -> user.getId()
                                                                   .equals(userId))
                                               .findFirst();

        return userOptional.filter(user -> userCache.remove(user))
                           .isPresent();
    }

    public List<User> findAll() {
        return new ArrayList<>(userCache);
    }
}
