package database;

import other.Entity;

import java.util.List;

public interface Repository {
    List<? extends Entity> select();
    void insert(Object object);
    void delete(int id);
}
