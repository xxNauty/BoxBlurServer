package database;

import java.sql.*;
import java.util.Objects;
import java.util.logging.Logger;

public class DatabaseOperationService {

    private final Logger logger = Logger.getLogger(DatabaseOperationService.class.getName());

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void openConnection() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:src/main/java/database/data/index.db");
            if (connection != null) {
                DatabaseMetaData meta = connection.getMetaData();
                logger.info("A new database has been created.");
            }
            this.connection = connection;
        } catch (SQLException e) {
            logger.warning(e.getMessage());
        }
    }

//    public void executeUpdateQuery(String query) {
//        try {
//            Statement statement = this.connection.createStatement();
//            statement.executeUpdate(query);
//            logger.info("Update query executed successfully.");
//            statement.close();
//        } catch (SQLException e) {
//            logger.warning(e.getMessage());
//        }
//    }
//
//    public ResultSet executeSelectQuery(String query, Object datatype) {
//        System.out.println(datatype.getClass().getName());
//
//        return null;
//    }

    public void closeConnection() {
        try {
            this.connection.close();
            logger.info("Connection closed.");
        } catch (SQLException e) {
            logger.warning(e.getMessage());
        }
    }
}
