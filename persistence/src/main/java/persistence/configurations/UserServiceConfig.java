package persistence.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import persistence.interfaces.UserService;

@Configuration
@ComponentScan(value = "persistence.services")
public class UserServiceConfig {
    @Bean
    public UserService getUserService() {
        return new persistence.services.UserServiceImpl();
    }
}
