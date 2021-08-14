package com.in28minutes.rest.webservices.restfulwebservices.user;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserService {
    private static List<User> users = new ArrayList<>();
    private static int userIdCount = 3;

    static {
        users.add(new User(1, "Ivo", new Date()));
        users.add(new User(2, "John", new Date()));
        users.add(new User(3, "Andrea", new Date()));
    }

    List<User> findAll() {
        return users;
    }

    User save(User user) {
        if (user.getId() == null) {
            user.setId(++userIdCount);
        }
        users.add(user);
        return user;
    }

    User findOne(int id) {
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
