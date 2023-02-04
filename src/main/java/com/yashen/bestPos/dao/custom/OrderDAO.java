package com.yashen.bestPos.dao.custom;

import com.yashen.bestPos.dao.CrudDAO;
import com.yashen.bestPos.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDAO extends CrudDAO<Order,String> {
    String getLastOrderId () throws RuntimeException , SQLException , ClassNotFoundException;
    ArrayList<Order> getOrdersList(ResultSet rst) throws RuntimeException,ClassNotFoundException,SQLException;
}
