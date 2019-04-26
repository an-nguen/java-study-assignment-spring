package persistence.repository;

import domain.User;
import persistence.dao.UserDAO;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class UserRepository implements Repository<User> {
    private UserDAO userDAO;

    public UserRepository(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void add(User item) {
        if (item == null) return;

        userDAO.create(item);

    }

    @Override
    public void update(User item) {
        if (item == null) return;

        userDAO.update(item);
    }

    @Override
    public void delete(User item) {
        if (item == null) return;

        userDAO.delete(item);
    }

    @Override
    public ArrayList<User> selectAll() {
        return userDAO.getAll();
    }

    @Override
    public ArrayList<User> query(Specification specification) {
        return (ArrayList<User>) userDAO.getAll()
                .stream()
                .filter(specification::specified)
                .collect(Collectors.toList());
    }
}
