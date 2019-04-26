import configuration.DIConfiguration;
import consumer.App;
import domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class UserRepoTest {

    @Test
    @DisplayName("Test Spring DI")
    void testUserRepo() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DIConfiguration.class);
        App app = context.getBean(App.class);

        app.getRepo().add(new User("TestUser", "testpass", "test@mail.com"));

        context.close();
    }
}
