import java.util.ArrayList;
import java.util.List;

class CollaborativeDocument implements DocumentMediator {
    private final List<User> users;
    private final StringBuilder documentContent;

    public CollaborativeDocument() {
        this.users = new ArrayList<>();
        this.documentContent = new StringBuilder();
    }

    @Override
    public void registerUser(User user) {
        users.add(user);
    }

    @Override
    public void notifyChanges(User sender, String changes) {
        for (User user : users) {
            if (user != sender) {
                user.receiveChanges(changes);
            }
        }
    }

    public String getDocumentContent() {
        return documentContent.toString();
    }

    public synchronized void appendToDocument(String changes) {
        documentContent.append(changes);
    }
}