package com.yashen.bestPos.dao.custom;

import com.yashen.bestPos.dao.CrudDAO;
import com.yashen.bestPos.entity.OrderDetails;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDetailsDAO extends CrudDAO<OrderDetails,String> {
    boolean delete (String primaryKey1 , String primaryKey2) throws RuntimeException, SQLException, ClassNotFoundException;
    OrderDetails getByPrimaryKey(String primaryKey1 , String primaryKey2) throws RuntimeException, SQLException, ClassNotFoundException;
    boolean isExists(String primaryKey1 , String primaryKey2) throws RuntimeException, SQLException, ClassNotFoundException;
    ArrayList<OrderDetails> getOrderCart(String id) throws RuntimeException,SQLException,ClassNotFoundException;
}


