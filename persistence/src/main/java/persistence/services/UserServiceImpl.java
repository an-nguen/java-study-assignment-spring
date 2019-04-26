package persistence.services;

import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.interfaces.UserService;
import persistence.repositories.UserRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void add(User user) {
        userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    public List<User> select() {
        return (List<User>) userRepository.findAll();
    }

    public List<Map<String, String>> getJSON() {
        List<Map<String, String>> usersJSON = new ArrayList<>();
        for (var item : userRepository.findAll()) {
            usersJSON.add(new HashMap<>() {{
                put("username", item.getUsername());
                put("password", item.getPassword());
                put("email", item.getEmail());
            }});
        }
        return usersJSON;
    }
}
