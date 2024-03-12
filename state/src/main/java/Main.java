public class Main {
    public static void main(String[] args) {
        Logging logging = new ConsoleLogging();
        Logger logger = new Logger(logging, 1);

        logger.log(new Log(Level.INFO,"first message"));
        logger.log(new Log(Level.WARNING,"second message"));
        logger.log(new Log(Level.ERROR,"third message"));
    }
}
