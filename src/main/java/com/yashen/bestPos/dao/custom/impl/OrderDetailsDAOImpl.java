package com.yashen.bestPos.dao.custom.impl;

import com.yashen.bestPos.dao.custom.OrderDetailsDAO;
import com.yashen.bestPos.dao.util.CrudUtil;
import com.yashen.bestPos.entity.OrderDetails;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {

    @Override
    public boolean save(OrderDetails entity) throws RuntimeException, SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO order_details VALUES (?,?,?);",
                entity.getOrdId(),
                entity.getProductId(),
                entity.getQty()
        );
    }

    @Override
    public boolean update(OrderDetails entity) throws RuntimeException, SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE order_details SET qty=? WHERE order_id=? AND product = ?;",
                entity.getQty(),
                entity.getOrdId(),
                entity.getProductId()
        );
    }

    @Override
    public boolean delete(String primaryKey) throws RuntimeException, SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String primaryKey1, String primaryKey2 ) throws RuntimeException, SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM order_details WHERE order_id=? AND product = ?", primaryKey1,primaryKey2);
    }

    @Override
    public ArrayList<OrderDetails> getAll() throws RuntimeException, SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM order_details");
        return getCustomersList(rst);
    }

    @Override
    public OrderDetails getByPrimaryKey(String primaryKey) throws RuntimeException, SQLException, ClassNotFoundException {
        return null;
    }
    @Override
    public OrderDetails getByPrimaryKey(String primaryKey1, String primaryKey2) throws RuntimeException, SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM order_details WHERE order_id=? AND product = ?", primaryKey1, primaryKey2);
        rst.next();
        return new OrderDetails(
                rst.getString(1),
                rst.getString(2),
                rst.getInt(3)
        );
    }

    @Override
    public boolean isExists(String primaryKey) throws RuntimeException, SQLException, ClassNotFoundException {
        return false;
    }
    @Override
    public boolean isExists(String primaryKey1, String primaryKey2) throws RuntimeException, SQLException, ClassNotFoundException {
        if (getByPrimaryKey(primaryKey1, primaryKey2) == null){
            return true;
        }else {
            return true;
        }
    }

    @Override
    public ArrayList<OrderDetails> getOrderCart(String id) throws RuntimeException, SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM order_details WHERE order_id=?", id);
        return getCustomersList(rst);
    }

    public ArrayList<OrderDetails> getCustomersList(ResultSet rst) throws SQLException {
        ArrayList<OrderDetails> cart = new ArrayList<>();
        while (rst.next()) {
            OrderDetails od = new OrderDetails(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3)
            );
            cart.add(od);
        }
        return cart;
    }


}
