package file;

import boxBlurAlghoritm.ImageService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import database.DatabaseOperationService;
import file.entity.FileData;
import file.entity.FileDataRepository;
import org.json.JSONObject;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

public class FileDataHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if ("POST".equals(exchange.getRequestMethod())) {
            JSONObject obj = new JSONObject(new String(exchange.getRequestBody().readAllBytes()));
            FileDataRepository repository = new FileDataRepository(DatabaseOperationService.getConnection());

            FileData data = repository.selectByFilename(obj.getString("filename"));
            data.setRadius(obj.getInt("radius"));
            repository.delete(data.getId());
            repository.insert(data);
            ImageService.processImage(ImageIO.read(new File(FileSaver.FILE_PATH_RAW + data.getFilePath() + FileSaver.FILE_ENDING_RAW)), data.getFilePath());
        }
        else {
            exchange.sendResponseHeaders(405, 0);
            OutputStream outputStream = exchange.getResponseBody();
            outputStream.write("Method not allowed".getBytes());
        }
            exchange.close();
        }
}
