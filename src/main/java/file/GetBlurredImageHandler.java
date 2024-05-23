package file;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

public class GetBlurredImageHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String filename = exchange.getRequestURI().getQuery().split("&")[0].split("=")[1];
        if ("GET".equals(exchange.getRequestMethod())) {
            BufferedImage image = ImageIO.read(new File(FileSaver.FILE_PATH_BLURRED + filename + FileSaver.FILE_ENDING_BLURRED));

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try {
                ImageIO.write(image, "jpg", baos);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            exchange.sendResponseHeaders(200, 0);
            exchange.getResponseBody().write(baos.toByteArray());
        }
        else {
            exchange.sendResponseHeaders(405, 0);
            OutputStream outputStream = exchange.getResponseBody();
            outputStream.write("Method not allowed".getBytes());
        }
        exchange.close();
    }
}
