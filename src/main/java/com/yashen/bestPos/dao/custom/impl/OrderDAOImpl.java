package com.yashen.bestPos.dao.custom.impl;

import com.yashen.bestPos.dao.custom.OrderDAO;
import com.yashen.bestPos.dao.util.CrudUtil;
import com.yashen.bestPos.entity.Customer;
import com.yashen.bestPos.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public boolean save(Order entity) throws RuntimeException, SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO `order` (ord_id , customer) VALUES (?,?);",
                entity.getOrdId(),
                entity.getCustomerId()
        );
    }

    @Override
    public boolean update(Order entity) throws RuntimeException, SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE `order` SET customer = ? WHERE ord_id = ?;",
                entity.getCustomerId(),
                entity.getOrdId()
        );
    }

    @Override
    public boolean delete(String primaryKey) throws RuntimeException, SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM `order` WHERE ord_id=?", primaryKey);
    }

    @Override
    public ArrayList<Order> getAll() throws RuntimeException, SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM `order`");
        return getCustomersList(rst);
    }


    @Override
    public Order getByPrimaryKey(String primaryKey) throws RuntimeException, SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM `order` WHERE ord_id = ?", primaryKey);
         rst.next();
        return new Order(rst.getString(1), rst.getString(2),rst.getDate(3));
    }

    @Override
    public boolean isExists(String primaryKey) throws RuntimeException, SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM `order` WHERE ord_id = ?", primaryKey);
        if (rst.next()) {
            return true;
        } else {
            return false;
        }
    }

    private ArrayList<Order> getCustomersList(ResultSet rst) throws SQLException {
        ArrayList<Order> orders = new ArrayList<>();
        while (rst.next()) {
            Order order = new Order(rst.getString(1), rst.getString(2), rst.getDate(3));
            orders.add(order);
        }
        return orders;
    }

    @Override
    public String getLastOrderId() throws RuntimeException, SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT ord_id FROM `order` ORDER BY (ord_id) DESC LIMIT 1");
        if (rst.next()){
            System.out.println(rst.getString(1));
            return rst.getString(1);
        }
        return null;
    }
}
