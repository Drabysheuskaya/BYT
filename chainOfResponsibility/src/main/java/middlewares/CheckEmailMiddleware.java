package middlewares;

import storage.FileStorage;

public class CheckEmailMiddleware extends Middleware {


    public CheckEmailMiddleware(FileStorage fileStorage) {
        super(fileStorage);
    }

    public CheckEmailMiddleware() {

    }

    @Override
    public boolean check(String email, String password) {
        if (storage.userExist(email)) {
            System.err.println(String.format("Email %s already exist", email));
            return false;
        }
        System.out.println("email checks have passed");
        return checkNext(email, password);
    }
}
