import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class CsvFileSettingsMemento {

    private final String encoding;

    private final char quoteCharacter;

    private final char valuesDelimiter;

    private final boolean headerLine;

}
