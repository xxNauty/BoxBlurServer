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

        try { //usunięcie danych z poprzednich uruchomień
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM " + TABLE_NAME);
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

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
        }
        return results;
    }

    public FileData selectByFilename(String filename) {
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE filepath = '" + filename + "'";
        FileData fileData = null;
        try {
            Statement statement = this.connection.createStatement();
            statement.execute(query);
            ResultSet resultSet = statement.getResultSet();
            fileData = new FileData(
                    UUID.fromString(resultSet.getString("id")),
                    resultSet.getString("filePath"),
                    resultSet.getString("fileType"),
//                    resultSet.getDate("receivedAt"),
//                    resultSet.getDate("returnedAt"),
                    resultSet.getInt("radius")
            );

        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
        }
        return fileData;
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
        try {
            Statement statement = this.connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
        }
    }

    @Override
    public void delete(UUID id) {
        String query = "DELETE FROM " + TABLE_NAME + " WHERE id = '" + id + "'";
        try {
            Statement statement = this.connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
        }
    }
}
