package com.yashen.bestPos.dao.custom;

import com.yashen.bestPos.dao.SuperDAO;
import com.yashen.bestPos.dto.DeliveryStatusDTO;
import com.yashen.bestPos.entity.DeliveryStatus;
import com.yashen.bestPos.entity.Order;

import java.sql.SQLException;
import java.util.ArrayList;

public interface QueryDAO extends SuperDAO {
    ArrayList<Order> getAllPendingOrdersList() throws RuntimeException, ClassNotFoundException, ClassNotFoundException, SQLException;
}
