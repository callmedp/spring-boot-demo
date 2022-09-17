package com.example.demo.service;

import com.example.demo.repository.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDAOService {

    private static List<User> users = new ArrayList();
    private static Integer counter = 0;

    static {
        users.add(new User(++counter, "Morgoth", LocalDate.now().minusYears((25))));
        users.add(new User(++counter, "Sauron", LocalDate.now().minusYears((25))));
        users.add(new User(++counter, "Galadriel", LocalDate.now().minusYears((25))));
        users.add(new User(++counter, "Durin", LocalDate.now().minusYears((25))));

    }

    public List<User> findAll() {
        return users;
    }

    public User findUser(Integer id) {
        return users.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
    }

    public void save(User user) {
        user.setId(++counter);
        users.add(user);
    }

    public void deleteById(Integer id) {
        users.removeIf(user -> user.getId().equals(id));
    }


}
