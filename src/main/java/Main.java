import database.DatabaseOperationService;
import server.ServerManager;

public class Main {
    public static void main(String[] args) {
        DatabaseOperationService.openConnection();
        ServerManager.startServer();
    }

}
