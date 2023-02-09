package com.yashen.bestPos.dao.custom.impl;

import com.yashen.bestPos.dao.custom.UserDAO;
import com.yashen.bestPos.dao.util.CrudUtil;
import com.yashen.bestPos.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAOImpl implements UserDAO {
    @Override
    public boolean save(User entity) throws RuntimeException, SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO internal_operations VALUES (?,?,?,?,?);",

                entity.getName(),
                entity.getAddress(),
                entity.getUsername(),
                entity.getPassword(),
                entity.getAccType()

                );
    }

    @Override
    public boolean update(User entity) throws RuntimeException, SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE internal_operations SET name =?, address=?, acc_type=?, password=? WHERE user_name = ?",
                entity.getName(),
                entity.getAddress(),
                entity.getAccType(),
                entity.getPassword(),
                entity.getUsername()


        );
    }

    @Override
    public boolean delete(String primaryKey) throws RuntimeException, SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM internal_operations WHERE user_name=?",primaryKey);
    }

    @Override
    public ArrayList<User> getAll() throws RuntimeException, SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM internal_operations");
        return getUsersList(rst);
    }

    @Override
    public User getByPrimaryKey(String primaryKey) throws RuntimeException, SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM internal_operations where user_name = ?", primaryKey);
        if (rst.next()){
            return new User(
                    rst.getString("name"),
                    rst.getString("address"),
                    rst.getString("user_name"),
                    rst.getString("password"),
                    rst.getString("acc_type")
            );
        }
        return null;
    }

    @Override
    public boolean isExists(String primaryKey) throws RuntimeException, SQLException, ClassNotFoundException {
        return false;
    }

    private ArrayList<User> getUsersList(ResultSet rst) throws SQLException {
        ArrayList<User> usersList =  new ArrayList<>();
        while (rst.next()){
            usersList.add(new User(
                    rst.getString("name"),
                    rst.getString("address"),
                    rst.getString("user_name"),
                    rst.getString("password"),
                    rst.getString("acc_type")
            ));
        }
        return usersList;
    }
}
