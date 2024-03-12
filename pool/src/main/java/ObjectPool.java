import java.util.concurrent.*;

public abstract class ObjectPool<T> {

    private ConcurrentLinkedQueue<T> pool;
    private ScheduledExecutorService executorService;

    public ObjectPool(final int minObjects) {
        initialize(minObjects);
        initializeCounter();
    }

    public ObjectPool(final int minObjects, final int maxObjects, final long validationInterval) {
        initialize(minObjects);
        initializeCounter();

        executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleWithFixedDelay(() -> {
            int size = pool.size();

            if (size < minObjects) {
                int sizeToBeAdded = minObjects + size;
                for (int i = 0; i < sizeToBeAdded; i++) {
                    pool.add(createObject());
                }
            } else if (size > maxObjects) {
                int sizeToBeRemoved = size - maxObjects;
                for (int i = 0; i < sizeToBeRemoved; i++) {
                    pool.poll();
                }
            }
        }, validationInterval, validationInterval, TimeUnit.SECONDS);
    }

    public T borrowObject() {
        T object;
        if ((object = pool.poll()) == null) {
            object = createObject();
        }
        System.out.printf("Borrow object %s\n", object.toString());
        return object;
    }

    public void returnObject(T object) {
        if (object == null) {
            return;
        }
        System.out.printf("Return object %s\n", object.toString());
        this.pool.offer(object);
    }

    public void shutdown() {
        if (executorService != null) {
            executorService.shutdown();
            System.out.println("Object pool is shutdown");
        }
    }

    protected abstract T createObject();

    private void initialize(final int minObjects) {
        pool = new ConcurrentLinkedQueue<>();
        for (int i = 0; i < minObjects; i++) {
            pool.add(createObject());
        }
    }

    private void initializeCounter() {

    }

}
