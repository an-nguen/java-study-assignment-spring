package configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import persistence.dao.UserDAO;
import persistence.repository.UserRepository;

@Configuration
@ComponentScan(value = "consumer")
public class DIConfiguration {
    @Bean
    public UserRepository getUserRepo() {
        return new UserRepository(new UserDAO());
    }
}
