package com.yashen.bestPos.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T,ID> extends SuperDAO {
    boolean save (T entity) throws RuntimeException, SQLException, ClassNotFoundException;
    boolean update (T entity) throws RuntimeException, SQLException, ClassNotFoundException;
    boolean delete (ID primaryKey) throws RuntimeException, SQLException, ClassNotFoundException;
    ArrayList<T> getAll() throws RuntimeException, SQLException, ClassNotFoundException;
    T getByPrimaryKey(ID primaryKey) throws RuntimeException, SQLException, ClassNotFoundException;
    boolean isExists(ID primaryKey) throws RuntimeException, SQLException, ClassNotFoundException;
}
