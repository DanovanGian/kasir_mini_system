package minimarket.repository;

import minimarket.model.User;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private List<User> users = new ArrayList<>();

    public UserRepository() {
        // Data dummy
        users.add(new User(1, "admin", "admin123", "Administrator", "admin"));
        users.add(new User(2, "kasir1", "kasir123", "Budi Santoso", "kasir"));
        users.add(new User(3, "kasir2", "kasir123", "Siti Rahayu", "kasir"));
    }

    public User findByUsername(String username) {
        return users.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    public User login(String username, String password) {
        User user = findByUsername(username);
        if (user != null && user.getPassword().equals(password))
            return user;
        return null;
    }

}