import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    private static final int MIN_CONNECTIONS = 2;
    private static final int MAX_CONNECTIONS = 5;
    private static final long VALIDATION_INTERVAL = 5;

    private ConnectionPool connectionPool;

    public void setUp() {
        connectionPool = new ConnectionPool(MIN_CONNECTIONS, MAX_CONNECTIONS, VALIDATION_INTERVAL);
    }

    public void tearDown() {
        connectionPool.shutdown();
    }

    public void testConnectionPool() {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 3; i++) {
            executor.execute(new ConnectionTask(connectionPool, i));
        }
        executor.shutdown();
        try {
            executor.awaitTermination(30, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Main connectionPoolDemo = new Main();
        connectionPoolDemo.setUp();
        connectionPoolDemo.testConnectionPool();
        connectionPoolDemo.tearDown();
    }
}
