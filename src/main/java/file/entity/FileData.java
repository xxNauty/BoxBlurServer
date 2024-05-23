package file.entity;

import database.Entity;

import java.util.UUID;

//TODO: Uzupełnić encję o datę dodania pliku oraz czas przetwarzania
public class FileData implements Entity {

    private UUID id;
    private String filePath;
    private String fileType;
//    private Date receivedAt;
//    private Date returnedAt;
    private int radius;

    public FileData(String filePath, String fileType/*, Date receivedAt, Date returnedAt*/, int radius) {
        this.id = UUID.randomUUID();
        this.filePath = filePath;
        this.fileType = fileType;
//        this.receivedAt = receivedAt;
//        this.returnedAt = returnedAt;
        this.radius = radius;
    }

    public FileData(UUID id, String filePath, String fileType/*, Date receivedAt, Date returnedAt*/, int radius) {
        this.id = id;
        this.filePath = filePath;
        this.fileType = fileType;
//        this.receivedAt = receivedAt;
//        this.returnedAt = returnedAt;
        this.radius = radius;
    }

    public FileData() {}

    @Override
    public String toString() {
        return "FileData{" +
                "id=" + id +
                ", filePath='" + filePath + '\'' +
                ", fileType='" + fileType + '\'' +
//                ", receivedAt=" + receivedAt +
//                ", returnedAt=" + returnedAt +
                ", radius=" + radius +
                '}';
    }

    public UUID getId() {
        return id;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getFileType() {
        return fileType;
    }

//    public Date getReceivedAt() {
//        return receivedAt;
//    }
//
//    public Date getReturnedAt() {
//        return returnedAt;
//    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
