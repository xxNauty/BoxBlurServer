package file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

final public class FileSaver {

    private FileSaver() {}

    public static final String FILE_PATH_RAW = "src/main/java/images/raw/";
    public static final String FILE_PATH_BLURRED = "src/main/java/images/blurred/";
    public static final String FILE_ENDING_RAW = "_raw.jpg";
    public static final String FILE_ENDING_BLURRED = "_blurred.jpg";

    private static final Logger logger = Logger.getLogger(String.valueOf(FileSaver.class));

    public static String saveFile(byte[] data, boolean isAfterBlur){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String formattedDate = LocalDateTime.now().format(format);

        try {
            Path filePath = null;
            if(isAfterBlur){
                filePath = Path.of(FILE_PATH_BLURRED + formattedDate + FILE_ENDING_BLURRED);
            }
            else{
                filePath = Path.of(FILE_PATH_RAW + formattedDate + FILE_ENDING_RAW);
            }
            Files.write(filePath, data);
            logger.info("File saved to " + filePath);
        } catch (IOException e) {
            logger.warning(e.getMessage());
        }

        return formattedDate;
    }
}
