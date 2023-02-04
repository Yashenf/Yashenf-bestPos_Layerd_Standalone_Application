package com.yashen.bestPos.dao.custom.impl;

import com.yashen.bestPos.dao.custom.DeliveryStatusDAO;
import com.yashen.bestPos.dao.custom.OrderDAO;
import com.yashen.bestPos.dao.custom.QueryDAO;
import com.yashen.bestPos.dao.util.CrudUtil;
import com.yashen.bestPos.dao.util.DaoFactory;
import com.yashen.bestPos.dao.util.DaoTypes;
import com.yashen.bestPos.dto.DeliveryStatusDTO;
import com.yashen.bestPos.entity.DeliveryStatus;
import com.yashen.bestPos.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QueryDAOImpl implements QueryDAO {
    OrderDAO orderDAO = DaoFactory.getInstance().getDao(DaoTypes.ORDER);
    @Override
    public ArrayList<Order> getAllPendingOrdersList() throws RuntimeException, ClassNotFoundException, ClassNotFoundException, SQLException {
        ResultSet rst = CrudUtil.execute("select * from `order` where `order`.ord_id NOT IN (select order_id from dilevery_status)");
        return orderDAO.getOrdersList(rst);
    }
}
