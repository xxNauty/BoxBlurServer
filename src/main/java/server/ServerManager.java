package server;

import com.sun.net.httpserver.HttpServer;
import file.FileUploadHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.logging.Logger;

public class ServerManager {

    private final Logger logger = Logger.getLogger(ServerManager.class.getName());

    private HttpServer server;

    public void startServer() {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

            server.createContext("/upload", new FileUploadHandler()); //upload zdjęć
            logger.info("File upload at http://localhost:8080/upload");

            server.createContext("/data", new FileDataHandler());
            logger.info("Additional file data upload at http://localhost:8080/data");

            server.setExecutor(null);
            server.start();
            this.server = server;
            logger.info("Server started on port 8080");
        } catch (IOException e) {
            logger.warning(e.getMessage());
        }
    }

    public void stopServer() {
        this.server.stop(0);
    }

}
