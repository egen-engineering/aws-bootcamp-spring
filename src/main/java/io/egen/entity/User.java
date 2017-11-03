package io.egen.entity;

import lombok.Data;

import java.util.Map;
import java.util.UUID;

@Data
public class User {

    private final String id;

    private String firstName;

    private String lastName;

    private String email;

    private Map<String, Object> address;

    public User() {
        id = UUID.randomUUID()
                 .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        if (!super.equals(o)) return false;

        User user = (User) o;

        return getId().equals(user.getId());
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getId().hashCode();
        return result;
    }
}
