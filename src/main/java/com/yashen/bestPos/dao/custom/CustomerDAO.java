package com.yashen.bestPos.dao.custom;

import com.yashen.bestPos.dao.CrudDAO;
import com.yashen.bestPos.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDAO extends CrudDAO<Customer,String> {
    ArrayList<Customer> findByName(String searchQuary) throws SQLException, ClassNotFoundException;
}
