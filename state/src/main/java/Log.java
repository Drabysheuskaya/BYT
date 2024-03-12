import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Log {

    private Level level;

    private LocalDateTime time = LocalDateTime.now();

    private String message;

    public Log(Level level, String message) {
        this.level = level;
        this.time = LocalDateTime.now();
        this.message = message;
    }
}
