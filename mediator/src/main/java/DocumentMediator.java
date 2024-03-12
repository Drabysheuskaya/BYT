public interface DocumentMediator {
    void registerUser(User user);
    void notifyChanges(User sender, String changes);
    String getDocumentContent();
}
