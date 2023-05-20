package ra.model.service;

import java.sql.SQLException;
import java.util.List;

public interface IService <T>{
    List<T> findAll();
    boolean save(T t) throws SQLException;
    boolean update (T t);
    T findById(int e);
    boolean delete(int e);
}
