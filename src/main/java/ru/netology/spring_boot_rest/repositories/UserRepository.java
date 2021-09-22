package ru.netology.spring_boot_rest.repositories;

import org.springframework.stereotype.Repository;
import ru.netology.spring_boot_rest.model.Authorities;
import ru.netology.spring_boot_rest.model.User;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class UserRepository {
    private final Map<Integer, User> users;
    private final AtomicInteger id = new AtomicInteger();

    public UserRepository() {
        users = new ConcurrentHashMap<>();
        users.put(id.incrementAndGet(),
                new User(id.intValue(), "Anton", "password", List.of(Authorities.WRITE)));
        users.put(id.incrementAndGet(),
                new User(id.intValue(), "Peter", "Peter", List.of(Authorities.WRITE,
                        Authorities.READ)));
        users.put(id.incrementAndGet(),
                new User(id.intValue(), "Jhon", "Jhon", List.of(Authorities.DELETE,
                        Authorities.READ)));
        users.put(id.incrementAndGet(),
                new User(id.intValue(), "admin", "admin", List.of(Authorities.DELETE,
                        Authorities.READ, Authorities.WRITE)));
    }

    public List<Authorities> getUserAuthorities(String name, String password) {
        return users.values().stream()
                .filter(user -> user.getName().equals(name) && user.getPassword().equals(password))
                .findAny().orElse(new User()).getAuthorities();
    }
}
