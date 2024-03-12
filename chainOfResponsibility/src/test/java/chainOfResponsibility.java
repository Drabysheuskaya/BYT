import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import storage.FileStorage;
import middlewares.CheckBlackListMiddleware;
import middlewares.CheckEmailMiddleware;
import middlewares.CheckFormatMiddleware;
import middlewares.Middleware;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.provider.Arguments;
import storage.Storage;

import java.io.File;
import java.util.stream.Stream;

public class chainOfResponsibility {

    private File activeUsers = new File("src/main/resources/users.csv");
    private File blockedUsers = new File("src/main/resources/blocked-users.csv");

    private Storage storage = new FileStorage(activeUsers, blockedUsers);

    public static Stream<Arguments> casesFormatMiddleware() {
        return Stream.of(
                Arguments.of(true, "danven2018@mail.ru", "qwertyi"),
                Arguments.of(false, "danven2018gmail.com", "fgbjhfgjn"),
                Arguments.of(false, "danven2018@gmail.com", "f"),
                Arguments.of(false, "danven2018mail.ru", "s")
        );
    }

    public static Stream<Arguments> casesEmailMiddleware() {
        return Stream.of(
                Arguments.of(false, "danven2018@mail.ru", "qwertyi"),
                Arguments.of(false, "bobby1976@gmail.com", "fgbjhfgjn"),
                Arguments.of(true, "petrovich1955@gmail.com", "dfjkhdfj"),
                Arguments.of(true, "s25205@pjwstk.edu.pl", "ssdghgn")
        );
    }

    public static Stream<Arguments> casesBlackListMiddleware() {
        return Stream.of(
                Arguments.of(true, "danven2018@mail.ru", "qwertyi"),
                Arguments.of(true, "bobby1976@gmail.com", "fgbjhfgjn"),
                Arguments.of(false, "reformy@internet.com", "dfjkhdfj"),
                Arguments.of(false, "makey@gmail.com", "ssdghgn")
        );
    }

    public static Stream<Arguments> casesAllMiddlewares() {
        return Stream.of(
                Arguments.of(false, "danven2018@mail.ru", "qwertyi"),
                Arguments.of(false, "bobby1976@gmail.com", "edsdfdf"),
                Arguments.of(false, "reformy@internet.com", "dfjkhdfj"),
                Arguments.of(false, "makey@gmail.com", "ssdghgn"),
                Arguments.of(false, "someNewEmail@gmail.com", "q"),
                Arguments.of(true, "someNewEmail@gmail.com", "sdfbjdslfjkb"),
                Arguments.of(true, "secondNewEmail@gmail.com", "er23876")
        );
    }

    @ParameterizedTest
    @MethodSource("casesFormatMiddleware")
    public void testFormatMiddleware(boolean expected, String email, String password){
        Middleware middleware = Middleware.link(storage, new CheckFormatMiddleware());
        Assertions.assertEquals(expected, middleware.check(email, password));
    }

    @ParameterizedTest
    @MethodSource("casesEmailMiddleware")
    public void testEmailMiddleware(boolean expected, String email, String password){
        Middleware middleware = Middleware.link(storage, new CheckEmailMiddleware());
        Assertions.assertEquals(expected, middleware.check(email, password));
    }

    @ParameterizedTest
    @MethodSource("casesBlackListMiddleware")
    public void testBlackListMiddleWare(boolean expected, String email, String password){
        Middleware middleware = Middleware.link(storage, new CheckBlackListMiddleware());
        Assertions.assertEquals(expected, middleware.check(email, password));
    }


    @ParameterizedTest
    @MethodSource("casesAllMiddlewares")
    public void testAllMiddlewares(boolean expected, String email, String password){
        Middleware middleware = Middleware.link(storage, new CheckFormatMiddleware(), new CheckEmailMiddleware(),  new CheckBlackListMiddleware());
        Assertions.assertEquals(expected, middleware.check(email, password));
    }





}
