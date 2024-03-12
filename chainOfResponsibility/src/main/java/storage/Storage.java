package storage;

import models.User;

import java.util.Set;

public interface Storage {

    boolean userExist(String email);

    boolean userBlocked(String email);

}
