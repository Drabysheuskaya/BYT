import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class MediatorTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    StringBuilder textWithThreeUsers = new StringBuilder();

    StringBuilder textWithThreeUsersMakingChanges = new StringBuilder();

    StringBuilder finalDocument = new StringBuilder();

    {
        textWithThreeUsers.append("User 1 is making changes: 'initializing the project'\n");
        textWithThreeUsers.append("User 2 receive changes: 'initializing the project'\n");
        textWithThreeUsers.append("User 3 receive changes: 'initializing the project'\n");

        textWithThreeUsersMakingChanges.append(textWithThreeUsers);
        textWithThreeUsersMakingChanges.append("User 2 is making changes: 'add dependencies'\n");
        textWithThreeUsersMakingChanges.append("User 1 receive changes: 'add dependencies'\n");
        textWithThreeUsersMakingChanges.append("User 3 receive changes: 'add dependencies'\n");
        textWithThreeUsersMakingChanges.append("User 3 is making changes: 'creating first class'\n");
        textWithThreeUsersMakingChanges.append("User 1 receive changes: 'creating first class'\n");
        textWithThreeUsersMakingChanges.append("User 2 receive changes: 'creating first class'\n");

        finalDocument.append("initializing the projectadd dependenciescreating first class");

    }

    @Test
    public void testMediatorWithThreeUsers() {
        CollaborativeDocument document = new CollaborativeDocument();
        User user1 = new Editor(document, "User 1");
        User user2 = new Editor(document, "User 2");
        User user3 = new Editor(document, "User 3");
        document.registerUser(user1);
        document.registerUser(user2);
        document.registerUser(user3);
        user1.makeChanges("initializing the project");

        String expected = outputStreamCaptor.toString().replace("\r\n", "\n").replace("\r", "\n");
        String actual = textWithThreeUsers.toString().replace("\r\n", "\n").replace("\r", "\n");
        Assertions.assertEquals(expected, actual);
    }


    @Test
    public void testMediatorWithThreeUsersMakingChanges(){
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

        String expected = outputStreamCaptor.toString().replace("\r\n", "\n").replace("\r", "\n");
        String actual = textWithThreeUsersMakingChanges.toString().replace("\r\n", "\n").replace("\r", "\n");
        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void testFinalDocument(){
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

        String expected = document.getDocumentContent();
        String actual = finalDocument.toString().replace("\r\n", "\n").replace("\r", "\n");
        Assertions.assertEquals(actual, expected);
    }
    
    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}
