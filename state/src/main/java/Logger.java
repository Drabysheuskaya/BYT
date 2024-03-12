import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Logger {

    private Logging logging;

    private int minimumLevelOfLogging = 1;

    public Logger(Logging logging, int minimumLevelOfLogging) {
        this.logging = logging;
        if(minimumLevelOfLogging >= 1 && minimumLevelOfLogging <= 3) {
            this.minimumLevelOfLogging = minimumLevelOfLogging;
        }
    }

    public Logger(Logging logging) {
        this.logging = logging;
    }


    public void log(Log log){
        if(log.getLevel().getIndex() >= minimumLevelOfLogging) {
            logging.logMessage(log);
        }
    }

}
