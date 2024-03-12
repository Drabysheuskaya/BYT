import java.util.Stack;

public class CsvFileSettingsHistory {

    private Stack<CsvFileSettingsMemento> history = new Stack<>();

    public void saveSettings(CsvFileSettingsMemento memento) {
        if (!history.contains(memento)) {
            history.push(memento);
        } else {
            System.err.printf("Such settings %s already exists\n", memento);
        }
    }

    public CsvFileSettingsMemento getMemento() {
        return history.isEmpty() ? null : history.pop();
    }
}
