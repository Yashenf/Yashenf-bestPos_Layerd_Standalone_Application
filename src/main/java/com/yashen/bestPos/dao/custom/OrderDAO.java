package com.yashen.bestPos.dao.custom;

import com.yashen.bestPos.dao.CrudDAO;
import com.yashen.bestPos.entity.Order;

import java.sql.SQLException;

public interface OrderDAO extends CrudDAO<Order,String> {
    String getLastOrderId () throws RuntimeException , SQLException , ClassNotFoundException;
}
