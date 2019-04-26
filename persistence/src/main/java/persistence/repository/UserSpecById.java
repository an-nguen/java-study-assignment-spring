package persistence.repository;

import domain.User;

public class UserSpecById implements Specification<User> {
    private User user;

    public UserSpecById(User user) {
        this.user = user;
    }

    @Override
    public boolean specified(User item) {
        return user.getId() == item.getId();
    }
}
