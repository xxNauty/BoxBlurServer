package boxBlurAlghoritm;

import database.DatabaseOperationService;
import file.FileSaver;
import file.entity.FileData;
import file.entity.FileDataRepository;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

final public class ImageService {

    private ImageService() {}

    public static void processImage(BufferedImage image, String fileName) {
        FileDataRepository repository = new FileDataRepository(DatabaseOperationService.getConnection());
        FileData data = repository.selectByFilename(fileName);

        BufferedImage imageAfterBlur = ImageModifier.addBlurEffect(image, data.getRadius());

        //konwersja zdjęcia do tablicy bitów
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(imageAfterBlur, "jpg", baos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        FileSaver.saveFile(baos.toByteArray(), true);
    }
}
