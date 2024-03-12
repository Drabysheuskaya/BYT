import lombok.ToString;

@ToString
public class ConnectionTask implements Runnable {
    private ConnectionPool connectionPool;
    private int threadNo;

    public ConnectionTask(ConnectionPool connectionPool, int threadNo) {
        this.connectionPool = connectionPool;
        this.threadNo = threadNo;
    }

    public void run() {
        Connection connection = connectionPool.borrowObject();
        connection.setThreadNo(threadNo);
        connection.executeQuery("SELECT * FROM TABLE");
        connectionPool.returnObject(connection);
        connection.close();
    }
}
