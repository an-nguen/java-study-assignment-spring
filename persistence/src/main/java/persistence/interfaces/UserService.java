package persistence.interfaces;

import domain.User;

import java.util.Collection;

public interface UserService {
    void add(User user);
    void delete(User user);

    Collection<User> select();
}
