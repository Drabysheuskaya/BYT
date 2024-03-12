public class Editor extends User {

    public Editor(DocumentMediator mediator, String username) {
        super(mediator, username);
    }

    @Override
    public void receiveChanges(String changes) {
        System.out.printf("%s receive changes: '%s'%n", username, changes);
    }

    @Override
    public void makeChanges(String changes) {
        System.out.printf("%s is making changes: '%s'%n", username, changes);
        mediator.notifyChanges(this, changes);
        ((CollaborativeDocument) mediator).appendToDocument(changes);
    }
}
