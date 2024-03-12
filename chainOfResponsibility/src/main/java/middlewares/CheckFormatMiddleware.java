package middlewares;

import storage.FileStorage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckFormatMiddleware extends Middleware {

    private static final Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");

    private static final Pattern passwordPattern = Pattern.compile("^[a-zA-Z0-9]{7,}$");

    public CheckFormatMiddleware(FileStorage fileStorage) {
        super(fileStorage);
    }

    public CheckFormatMiddleware() {

    }

    @Override
    public boolean check(String email, String password) {
        Matcher emailMatcher = emailPattern.matcher(email);
        if (!emailMatcher.matches()) {
            System.err.println("Format of email is incorrect");
            return false;
        }
        Matcher passwordMatcher = passwordPattern.matcher(password);
        if (!passwordMatcher.matches()) {
            System.err.println("Format of password is incorrect");
            return false;
        }
        System.out.println("Format checks have passed");
        return checkNext(email, password);
    }
}
