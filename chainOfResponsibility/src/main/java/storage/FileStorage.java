package storage;

import models.Status;
import models.User;

import java.io.*;
import java.util.Set;
import java.util.stream.Collectors;

public class FileStorage implements Storage{

    private File activeUsersFile;

    private File blockedUsersFile;

    public FileStorage(File activeUsersFile, File blockedUsersFile) {
        this.activeUsersFile = activeUsersFile;
        this.blockedUsersFile = blockedUsersFile;
    }

    public Set<User> read(File file){
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            return reader.lines().map(data -> {
                User user = new User();
                String[] row = data.split(";");
                user.setName(row[0]);
                user.setSurname(row[1]);
                user.setEmail(row[2]);
                user.setPassword(row[3]);
                if(row[4].equals("true")){
                    user.setStatus(Status.ACTIVE);
                }else{
                    user.setStatus(Status.BLOCKED);
                }
                return user;
            }).collect(Collectors.toSet());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Set<User> getActiveUsersFile(){
        return read(activeUsersFile);
    }

    private Set<User> getBlockedUsersFile(){
        return read(blockedUsersFile);
    }

    public boolean userExist(String email){
        return getActiveUsersFile().stream().anyMatch(user -> user.getEmail().equals(email));
    }

    public boolean userBlocked(String email){
        return getBlockedUsersFile().stream().
                anyMatch(user -> user.getEmail().equals(email) && user.getStatus().equals(Status.BLOCKED));
    }

}
