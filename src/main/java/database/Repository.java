package database;

import java.util.List;
import java.util.UUID;

public interface Repository {

    List<? extends Entity> select();

    <T extends Entity> T selectByFilename(String filename);

    void insert(Object object);

    void delete(UUID id);
}
