package file.entity;

import database.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

public class FileDataRepository implements Repository {

    private final String TABLE_NAME = "file_data";
    private final Logger LOGGER = Logger.getLogger(this.getClass().getName());

    private final Connection connection;

    public FileDataRepository(Connection connection) {
        this.connection = connection;

        try { //tworzenie tabeli w bazie
            Statement statement = this.connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS "
                    + TABLE_NAME
                    + " (id UUID PRIMARY KEY, "
                    + "filePath VARCHAR(255), "
                    + "fileType VARCHAR(255), "
//                    + "receivedAt DATETIME, "
//                    + "returnedAt DATETIME, "
                    + "radius INT)");
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<FileData> select() {
        String query = "SELECT * FROM " + TABLE_NAME;
        List<FileData> results = new ArrayList<>();
        try {
            Statement statement = this.connection.createStatement();
            statement.execute(query);
            LOGGER.info("Select query executed successfully.");
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                FileData fileData = new FileData(
                        UUID.fromString(resultSet.getString("id")),
                        resultSet.getString("filePath"),
                        resultSet.getString("fileType"),
//                        resultSet.getDate("receivedAt"),
//                        resultSet.getDate("returnedAt"),
                        resultSet.getInt("radius")
                );
                results.add(fileData);
            }
            statement.close();
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
            throw new RuntimeException(e);
        }
        return results;
    }

    @Override
    public void insert(Object object) {
        FileData data = (FileData) object;
        String query = "INSERT INTO " + TABLE_NAME +
                "(id, filePath, fileType/*, receivedAt, returnedAt*/, radius) VALUES ("
                + "\"" + data.getId() + "\", "
                + "\"" + data.getFilePath() + "\","
                + "\"" + data.getFileType() + "\","
//                + "\"" +  data.getReceivedAt() + "\","
//                + "\"" + data.getReturnedAt() + "\", "
                + data.getRadius() + ")";
//        System.out.println(query);
        try {
            Statement statement = this.connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM " + TABLE_NAME + " WHERE id = " + id;
        try {
            Statement statement = this.connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
