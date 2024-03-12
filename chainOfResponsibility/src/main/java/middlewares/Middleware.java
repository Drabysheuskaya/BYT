package middlewares;

import storage.Storage;

public abstract class Middleware {
    private Middleware next;

    protected Storage storage;

    public Middleware(Storage storage) {
        this.storage = storage;
    }

    public Middleware() {
    }

    public static Middleware link(Storage storage, Middleware start, Middleware... others) {
        Middleware first = start;
        first.setStorage(storage);
        for (Middleware middleware : others) {
            middleware.setStorage(storage);
            first.next = middleware;
            first = middleware;
        }
        return start;
    }

    public abstract boolean check(String email, String password);

    protected boolean checkNext(String email, String password) {
        if (next == null) {
            return true;
        }
        return next.check(email, password);
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }
}
