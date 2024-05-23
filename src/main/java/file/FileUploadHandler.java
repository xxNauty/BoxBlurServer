package file;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import database.DatabaseOperationService;
import file.entity.FileData;
import file.entity.FileDataRepository;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUploadHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if ("POST".equals(exchange.getRequestMethod())) {
            InputStream inputStream = exchange.getRequestBody();
            String filename = FileSaver.saveFile(inputStream.readAllBytes(), false);
            FileData data = new FileData(
                    filename,
                    "JPG image",
                    0
            );
            FileDataRepository repository = new FileDataRepository(DatabaseOperationService.getConnection());
            repository.insert(data);

            exchange.sendResponseHeaders(200, 0);
            OutputStream outputStream = exchange.getResponseBody();
            outputStream.write(("Image uploaded, image name: " + filename + ".jpg").getBytes());
            outputStream.close();
        }
        else {
            exchange.sendResponseHeaders(405, 0);
            OutputStream outputStream = exchange.getResponseBody();
            outputStream.write("Method not allowed".getBytes());
        }
        exchange.close();
    }
}