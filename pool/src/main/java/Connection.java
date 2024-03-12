import lombok.ToString;

@ToString
public class Connection {

    private int connectionId;
    private int threadNo;

    public Connection(int connectionId){
        this.connectionId = connectionId;
    }

    public int getConnectionId() {
        return connectionId;
    }

    public int getThreadNo() {
        return threadNo;
    }

    public void setThreadNo(int threadNo) {
        this.threadNo = threadNo;
    }

    public void executeQuery(String query){
        System.out.printf("Executing query with connection %s from thread %d\n", connectionId, threadNo);
    }

    public void close(){
        System.out.printf("Closing the connection %s from thread %d\n", connectionId, threadNo);
    }
}

