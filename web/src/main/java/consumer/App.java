package consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import persistence.repository.UserRepository;

@Component
public class App {
    private UserRepository userRepository;

    @Autowired
    public void setRepo(UserRepository repo){
        this.userRepository = repo;
    }

    public UserRepository getRepo(){
        return userRepository;
    }
}
