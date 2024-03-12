import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BuilderPatternTest {

    private String email = "tobby2003@gmail.com";

    private String username = "tobs203";

    private String name = "tobby";

    private String password = "qwerty1234";

    @Test
    public void testBuilder(){
        User user = new User.UserBuilder().setEmail(email).setName(name).setUsername(username)
                .setPassword(password).build();

        Assertions.assertEquals(email, user.getEmail());
        Assertions.assertEquals(name, user.getName());
        Assertions.assertEquals(username, user.getUsername());
        Assertions.assertEquals(password, user.getPassword());
    }
}
