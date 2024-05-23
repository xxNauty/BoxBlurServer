package database;

import file.FileSaver;

import java.sql.*;
import java.util.Objects;
import java.util.logging.Logger;

final public class DatabaseOperationService {

    private DatabaseOperationService() {}

    private static final Logger logger = Logger.getLogger(DatabaseOperationService.class.getName());

    private static Connection connection;

    public static Connection getConnection() {
        return connection;
    }

    public static void openConnection() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:src/main/java/database/data/index.db");
            if (connection != null) {
                logger.info("A new database has been created.");
            }
            DatabaseOperationService.connection = connection;
        } catch (SQLException e) {
            logger.warning(e.getMessage());
        }
    }

    public static void closeConnection() {
        try {
            DatabaseOperationService.connection.close();
            logger.info("Connection closed.");
        } catch (SQLException e) {
            logger.warning(e.getMessage());
        }
    }
}
