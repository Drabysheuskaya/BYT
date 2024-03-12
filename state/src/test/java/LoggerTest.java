import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class LoggerTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    private static final String GREEN = "\u001B[38;5;34m";
    private static final String ORANGE = "\u001B[38;5;208m";
    private static final String RED = "\u001B[38;5;196m";
    private static final String DEFAULT_COLOR = "\u001B[0m";

    private static final File TEST_FILE = new File("src/test/resources/testFile.txt");

    private static final File DEFAULT_FILE = new File("src/main/resources/log.txt");

    private LocalDateTime time1 = LocalDateTime.parse("2023-11-13T14:37:21.510873700");
    private LocalDateTime time2 = LocalDateTime.parse("2023-11-13T14:37:21.524871400");
    private LocalDateTime time3 = LocalDateTime.parse("2023-11-13T14:37:21.525870200");


    private StringBuilder textConsoleLogging = new StringBuilder();

    private StringBuilder textConsoleLoggingIndex = new StringBuilder();

    private StringBuilder textFileLogging = new StringBuilder();


    {
        textConsoleLogging.append(GREEN + "Log(level=INFO, time=2023-11-13T14:37:21.510873700, message=first message)" + DEFAULT_COLOR + "\n");
        textConsoleLogging.append("**************************************************************************\n");
        textConsoleLogging.append(ORANGE + "Log(level=WARNING, time=2023-11-13T14:37:21.524871400, message=second message)" + DEFAULT_COLOR + "\n");
        textConsoleLogging.append("******************************************************************************\n");
        textConsoleLogging.append(RED + "Log(level=ERROR, time=2023-11-13T14:37:21.525870200, message=third message)" + DEFAULT_COLOR + "\n");
        textConsoleLogging.append("***************************************************************************\n");

        textConsoleLoggingIndex.append(RED + "Log(level=ERROR, time=2023-11-13T14:37:21.525870200, message=third message)" + DEFAULT_COLOR + "\n");
        textConsoleLoggingIndex.append("***************************************************************************\n");

        textFileLogging.append("Log(level=INFO, time=2023-11-13T14:37:21.510873700, message=first message)\n");
        textFileLogging.append("Log(level=WARNING, time=2023-11-13T14:37:21.524871400, message=second message)\n");
        textFileLogging.append("Log(level=ERROR, time=2023-11-13T14:37:21.525870200, message=third message)\n");
    }

    @Test
    public void testConsoleLogging() throws NoSuchFieldException, IllegalAccessException {
        Logging logging = new ConsoleLogging();
        Logger logger = new Logger(logging, 1);

        Log log1 = new Log(Level.INFO, "first message");
        Log log2 = new Log(Level.WARNING, "second message");
        Log log3 = new Log(Level.ERROR, "third message");

        Class<?> clazz = log1.getClass();
        Field timeField = clazz.getDeclaredField("time");
        timeField.setAccessible(true);
        timeField.set(log1, time1);
        timeField.set(log2, time2);
        timeField.set(log3, time3);

        logger.log(log1);
        logger.log(log2);
        logger.log(log3);

        String actual = outputStreamCaptor.toString().replace("\r\n", "\n").replace("\r", "\n");
        String expected = textConsoleLogging.toString().replace("\r\n", "\n").replace("\r", "\n");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testConsoleLoggingWithIndex() throws NoSuchFieldException, IllegalAccessException {
        Logging logging = new ConsoleLogging();
        Logger logger = new Logger(logging, 3);

        Log log1 = new Log(Level.INFO, "first message");
        Log log2 = new Log(Level.WARNING, "second message");
        Log log3 = new Log(Level.ERROR, "third message");

        Class<?> clazz = log1.getClass();
        Field timeField = clazz.getDeclaredField("time");
        timeField.setAccessible(true);
        timeField.set(log1, time1);
        timeField.set(log2, time2);
        timeField.set(log3, time3);

        logger.log(log1);
        logger.log(log2);
        logger.log(log3);

        String actual = outputStreamCaptor.toString().replace("\r\n", "\n").replace("\r", "\n");
        String expected = textConsoleLoggingIndex.toString().replace("\r\n", "\n").replace("\r", "\n");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testFileLoggingWithGivenFile() throws NoSuchFieldException, IllegalAccessException, IOException {
        Logging logging = new FileLogging(TEST_FILE);
        Logger logger = new Logger(logging, 1);
        Log log1 = new Log(Level.INFO, "first message");
        Log log2 = new Log(Level.WARNING, "second message");
        Log log3 = new Log(Level.ERROR, "third message");

        Class<?> clazz = log1.getClass();
        Field timeField = clazz.getDeclaredField("time");
        timeField.setAccessible(true);
        timeField.set(log1, time1);
        timeField.set(log2, time2);
        timeField.set(log3, time3);

        logger.log(log1);
        logger.log(log2);
        logger.log(log3);

        String actual = getContentOfFile(TEST_FILE).replace("\r\n", "\n").replace("\r", "\n");
        String expected = textFileLogging.toString().replace("\r\n", "\n").replace("\r", "\n");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testFileLoggingWithDefaultFile() throws NoSuchFieldException, IllegalAccessException, IOException {
        Logging logging = new FileLogging();
        Logger logger = new Logger(logging, 1);
        Log log1 = new Log(Level.INFO, "first message");
        Log log2 = new Log(Level.WARNING, "second message");
        Log log3 = new Log(Level.ERROR, "third message");

        Class<?> clazz = log1.getClass();
        Field timeField = clazz.getDeclaredField("time");
        timeField.setAccessible(true);
        timeField.set(log1, time1);
        timeField.set(log2, time2);
        timeField.set(log3, time3);

        logger.log(log1);
        logger.log(log2);
        logger.log(log3);

        String actual = getContentOfFile(DEFAULT_FILE).replace("\r\n", "\n").replace("\r", "\n");
        String expected = textFileLogging.toString().replace("\r\n", "\n").replace("\r", "\n");

        Assertions.assertEquals(expected, actual);
    }




    @Test
    public void testStatePattern() throws IllegalAccessException, NoSuchFieldException, IOException {
        Logging logging = new ConsoleLogging();
        Logger logger = new Logger(logging, 1);

        Log log1 = new Log(Level.INFO, "first message");
        Log log2 = new Log(Level.WARNING, "second message");
        Log log3 = new Log(Level.ERROR, "third message");

        Class<?> clazz = log1.getClass();
        Field timeField = clazz.getDeclaredField("time");
        timeField.setAccessible(true);
        timeField.set(log1, time1);
        timeField.set(log2, time2);
        timeField.set(log3, time3);

        logger.log(log1);
        logger.log(log2);
        logger.log(log3);

        Logging fileLogging = new FileLogging(TEST_FILE);
        logger.setLogging(fileLogging);

        logger.log(log1);
        logger.log(log2);
        logger.log(log3);

        String actualConsole = outputStreamCaptor.toString().replace("\r\n", "\n").replace("\r", "\n");
        String expectedConsole = textConsoleLogging.toString().replace("\r\n", "\n").replace("\r", "\n");


        String actualFile = getContentOfFile(TEST_FILE).replace("\r\n", "\n").replace("\r", "\n");
        String expectedFile = textFileLogging.toString().replace("\r\n", "\n").replace("\r", "\n");

        Assertions.assertEquals(expectedConsole, actualConsole);
        Assertions.assertEquals(actualFile, expectedFile);

    }

    public String getContentOfFile(File file) throws IOException {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(file))){
            String text = bufferedReader.lines().collect(Collectors.joining(System.lineSeparator())) + System.lineSeparator();
            cleanContent(file);
            return text;
        }
    }

    public void cleanContent(File file) throws IOException {
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write("");
        }
    }
}
