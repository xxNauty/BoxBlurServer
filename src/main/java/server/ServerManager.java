package server;

import com.sun.net.httpserver.HttpServer;
import file.FileDataHandler;
import file.FileUploadHandler;
import file.GetBlurredImageHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.logging.Logger;

final public class ServerManager {

    private ServerManager() {}

    private static final Logger logger = Logger.getLogger(ServerManager.class.getName());

    private static HttpServer server;

    public static void startServer() {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

            server.createContext("/upload", new FileUploadHandler()); //upload zdjęć
            logger.info("File upload at http://localhost:8080/upload");

            server.createContext("/blur", new FileDataHandler()); //przesłanie wartości "radius"
            logger.info("Additional file data upload at http://localhost:8080/data");

            server.createContext("/getFile", new GetBlurredImageHandler()); //pobieranie zablurowanego zdjęcia
            logger.info("Get blurred image at http://localhost:8080/getFile");

            server.createContext("/shutdown", new StopServerHandler()); //endpoint do wyłączania servera
            logger.info("Server can be turned off at http://localhost:8080/shutdown");

            server.setExecutor(null);
            server.start();
            ServerManager.server = server;
            logger.info("Server started on port 8080");
        } catch (IOException e) {
            logger.warning(e.getMessage());
        }
    }

    public static void stopServer() {
        ServerManager.server.stop(5);
        logger.info("Server stopped");
    }

}
