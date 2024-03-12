import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FileLogging implements Logging{

    private static final String DEFAULT_FILE = "src/main/resources/log.txt";

    private File file;

    @Override
    public void logMessage(Log log) {
        Path path = Paths.get(DEFAULT_FILE);
        if(file == null){
            if (!Files.exists(path)) {
                try{
                    Files.createFile(path);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            file = new File(DEFAULT_FILE);
        }
        try(BufferedWriter bf = new BufferedWriter(new FileWriter(file, true))){
            bf.write(log.toString());
            bf.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}