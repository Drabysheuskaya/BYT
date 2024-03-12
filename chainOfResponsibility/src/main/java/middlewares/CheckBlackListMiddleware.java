package middlewares;

import storage.Storage;

public class CheckBlackListMiddleware extends Middleware {

    public CheckBlackListMiddleware(Storage storage) {
        super(storage);
    }

    public CheckBlackListMiddleware(){

    }

    @Override
    public boolean check(String email, String password) {
        if(storage.userBlocked(email)){
            System.err.println("User is blocked");
            return false;
        }
        System.out.println("User is not blocked");
        return checkNext(email, password);
    }
}
