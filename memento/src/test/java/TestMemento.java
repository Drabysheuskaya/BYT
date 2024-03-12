import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestMemento {


    public CsvFileSettings getDefaultSettings() {
        return new CsvFileSettings("UTF-8", '"', ',', true);
    }

    public CsvFileSettings copySettings(CsvFileSettings settings) {
        return new CsvFileSettings(
                settings.getEncoding(),
                settings.getQuoteCharacter(),
                settings.getValuesDelimiter(),
                settings.isHeaderLine()
        );
    }


    @Test
    public void testRestore() {
        CsvFileSettings csvFileSettings = getDefaultSettings();
        CsvFileSettingsHistory settingsHistory = new CsvFileSettingsHistory();

        settingsHistory.saveSettings(csvFileSettings.save());

        csvFileSettings.setEncoding("ISO-8859-1");
        csvFileSettings.setHeaderLine(false);
        settingsHistory.saveSettings(csvFileSettings.save());
        CsvFileSettings commit = copySettings(csvFileSettings);
        csvFileSettings.setEncoding("some wrong encoding");

        Assertions.assertNotEquals(commit, csvFileSettings);

        csvFileSettings.restore(settingsHistory.getMemento());
        CsvFileSettings backToSecond = copySettings(csvFileSettings);

        Assertions.assertEquals(commit, backToSecond);
    }

    @Test
    public void testNullCase() {
        CsvFileSettings csvFileSettings = getDefaultSettings();
        CsvFileSettingsHistory settingsHistory = new CsvFileSettingsHistory();

        csvFileSettings.restore(settingsHistory.getMemento());

        CsvFileSettings afterRestore = copySettings(csvFileSettings);

        Assertions.assertEquals(csvFileSettings, afterRestore);

    }
}
