package io.egen.repository;

import io.egen.NotFoundException;
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

    public User findById(String userId) {
        Optional<User> userOptional = userCache.stream()
                                               .filter(user -> user.getId()
                                                                   .equals(userId))
                                               .findFirst();

        if (userOptional.isPresent()) {
            return userOptional.get();
        }

        throw new NotFoundException("User not found");
    }

    public User create(User user) {
        userCache.add(user);
        return user;
    }

    public User update(String userId, User user) {
        User existing = findById(userId);
        userCache.remove(existing);
        userCache.add(user);
        return user;
    }

    public User remove(String userId) {
        User existing = findById(userId);
        userCache.remove(existing);
        return existing;
    }

    public List<User> findAll() {
        return new ArrayList<>(userCache);
    }
}
