package file;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUploadHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        OutputStream outputStream = exchange.getResponseBody();
        if ("POST".equals(exchange.getRequestMethod())) {
            InputStream inputStream = exchange.getRequestBody();

            String filename = FileSaver.saveFile(inputStream.readAllBytes());

            exchange.sendResponseHeaders(200, 0);
            outputStream.write(("Image received, image name: " + filename + ".jpg").getBytes());
            outputStream.close();
        }
        else {
            exchange.sendResponseHeaders(405, 0);
        }
        exchange.close();
    }
}