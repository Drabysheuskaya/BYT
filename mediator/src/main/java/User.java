public abstract class User{
    protected DocumentMediator mediator;
    protected String username;

    public User(DocumentMediator mediator, String username) {
        this.mediator = mediator;
        this.username = username;
    }

    public abstract void receiveChanges(String changes);

    public abstract void makeChanges(String changes);

}
