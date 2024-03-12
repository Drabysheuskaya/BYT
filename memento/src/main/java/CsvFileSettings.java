import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class CsvFileSettings {

    private String encoding;

    private char quoteCharacter;

    private char valuesDelimiter;

    private boolean headerLine;

    public CsvFileSettingsMemento save() {
        return new CsvFileSettingsMemento(encoding, quoteCharacter, valuesDelimiter, headerLine);
    }

    public void restore(CsvFileSettingsMemento csvFileSettingsMemento) {
        if (csvFileSettingsMemento == null) {
            System.err.println("There are no saved settings");
        } else {
            this.encoding = csvFileSettingsMemento.getEncoding();
            this.headerLine = csvFileSettingsMemento.isHeaderLine();
            this.quoteCharacter = csvFileSettingsMemento.getQuoteCharacter();
            this.headerLine = csvFileSettingsMemento.isHeaderLine();
        }
    }

}