package com.yashen.bestPos.dao.custom.impl;

import com.yashen.bestPos.dao.custom.ProductDAO;
import com.yashen.bestPos.dao.util.CrudUtil;
import com.yashen.bestPos.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDAOImpl implements ProductDAO {
    @Override
    public boolean save(Product entity) throws RuntimeException, SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO product VALUES (?,?,?,?);",
                entity.getpId(),
                entity.getpDesc(),
                entity.getpPrice(),
                entity.getpQty()
        );
    }

    @Override
    public boolean update(Product entity) throws RuntimeException, SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE product SET p_desc = ? , p_price = ? , p_qty = ? WHERE p_id = ? ",
                entity.getpDesc(),
                entity.getpPrice(),
                entity.getpQty(),
                entity.getpId()
        );
    }

    @Override
    public boolean delete(String primaryKey) throws RuntimeException, SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM product WHERE p_id=?", primaryKey);
    }

    @Override
    public ArrayList<Product> getAll() throws RuntimeException, SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM product");
        return getProductsList(rst);
    }

    @Override
    public Product getByPrimaryKey(String primaryKey) throws RuntimeException, SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM product WHERE p_id = ?", primaryKey);
        rst.next();
        return new Product(rst.getString(1), rst.getString(2), rst.getDouble(3),
                rst.getInt(4) );
    }

    @Override
    public boolean isExists(String primaryKey) throws RuntimeException, SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM product WHERE p_id = ?", primaryKey);
        if (rst.next()) {
            return true;
        } else {
            return false;
        }
    }

    private ArrayList<Product> getProductsList(ResultSet rst) throws SQLException {
        ArrayList<Product> products = new ArrayList<>();
        while (rst.next()) {
            Product tempProduct = new Product (rst.getString(1),
                    rst.getString(2),
                    rst.getDouble(3),
                    rst.getInt(4)
            );
            products.add(tempProduct);
        }
        return products;
    }

    @Override
    public ArrayList<Product> findByName(String searchQuary) throws SQLException, ClassNotFoundException {
        searchQuary = "%" + searchQuary + "%";
        ResultSet rst = CrudUtil.execute("SELECT * FROM product WHERE p_id LIKE ? OR p_desc LIKE ? OR p_price LIKE ? ", searchQuary, searchQuary, searchQuary);
        return getProductsList(rst);
    }
}
