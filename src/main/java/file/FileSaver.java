package file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

public class FileSaver {

    private static final Logger logger = Logger.getLogger(String.valueOf(FileSaver.class));

    public static String saveFile(byte[] data){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String formattedDate = LocalDateTime.now().format(format);

        try {
            Path filePath = Path.of("src/main/java/images/" + formattedDate + ".jpg");
            Files.write(filePath, data);
            logger.info("File saved to " + filePath);
        } catch (IOException e) {
            logger.warning(e.getMessage());
        }

        return formattedDate;
    }
}
