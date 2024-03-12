public class Main {
    public static void main(String[] args) {
        CollaborativeDocument document = new CollaborativeDocument();
        User user1 = new Editor(document, "User 1");
        User user2 = new Editor(document, "User 2");
        User user3 = new Editor(document, "User 3");
        document.registerUser(user1);
        document.registerUser(user2);
        document.registerUser(user3);

        user1.makeChanges("initializing the project");
        user2.makeChanges("add dependencies");
        user3.makeChanges("creating first class");

        System.out.println(document.getDocumentContent());

    }
}