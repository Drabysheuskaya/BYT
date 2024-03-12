import java.util.concurrent.atomic.AtomicInteger;

public class ConnectionPool extends ObjectPool<Connection> {
    private AtomicInteger connectionIdCounter = new AtomicInteger(1);

    public ConnectionPool(int minObjects) {
        super(minObjects);
    }

    public ConnectionPool(int minObjects, int maxObjects, long validationInterval) {
        super(minObjects, maxObjects, validationInterval);
    }

    @Override
    protected Connection createObject() {
        if (connectionIdCounter == null) {
            synchronized (this) {
                if (connectionIdCounter == null) {
                    connectionIdCounter = new AtomicInteger(1);
                }
            }
        }

        int connectionId = connectionIdCounter.getAndIncrement();
        return new Connection(connectionId);
    }

}
