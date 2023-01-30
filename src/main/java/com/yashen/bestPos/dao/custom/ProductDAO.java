package com.yashen.bestPos.dao.custom;

import com.yashen.bestPos.dao.CrudDAO;
import com.yashen.bestPos.entity.Customer;
import com.yashen.bestPos.entity.Product;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ProductDAO extends CrudDAO<Product,String> {
    ArrayList<Product> findByName(String searchQuary) throws SQLException, ClassNotFoundException;
}
