package server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import database.DatabaseOperationService;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;

public class StopServerHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if ("POST".equals(exchange.getRequestMethod())) {
            JSONObject obj = new JSONObject(new String(exchange.getRequestBody().readAllBytes()));
            if (obj.get("password").equals("qwerty123")){
                exchange.sendResponseHeaders(200, 0);
                OutputStream outputStream = exchange.getResponseBody();
                outputStream.write("Server stopped successfully".getBytes());
                outputStream.close();

                DatabaseOperationService.closeConnection();
                ServerManager.stopServer();
            }
            else {
                exchange.sendResponseHeaders(403, 0);
                OutputStream outputStream = exchange.getResponseBody();
                outputStream.write("You are not allowed to stop server".getBytes());
            }
        }
        else {
            exchange.sendResponseHeaders(405, 0);
            OutputStream outputStream = exchange.getResponseBody();
            outputStream.write("Method not allowed".getBytes());
        }
        exchange.close();
    }
}
