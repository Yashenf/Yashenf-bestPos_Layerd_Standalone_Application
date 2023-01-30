package com.yashen.bestPos.bo.custom.impl;

import com.yashen.bestPos.bo.custom.OrderBO;
import com.yashen.bestPos.bo.util.Converter;
import com.yashen.bestPos.dao.custom.OrderDAO;
import com.yashen.bestPos.dao.util.DaoFactory;
import com.yashen.bestPos.dao.util.DaoTypes;
import com.yashen.bestPos.dto.OrderDTO;
import com.yashen.bestPos.entity.Order;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderBOImpl implements OrderBO {
    OrderDAO orderDAO = DaoFactory.getInstance().getDao(DaoTypes.ORDER);
    Converter converter = new Converter();
    @Override
    public ArrayList<OrderDTO> getAll() throws RuntimeException, SQLException, ClassNotFoundException {
        ArrayList<OrderDTO> orders = new ArrayList<>();
        for (Order o : orderDAO.getAll()){
            orders.add(new OrderDTO(
                    o.getOrdId(),
                    o.getCustomerId(),
                    o.getOrdDate()
            ));
        }
        return orders;
    }

    @Override
    public OrderDTO getByPrimaryKey(String primaryKey) throws RuntimeException, SQLException, ClassNotFoundException {
        return converter.fromOrder(orderDAO.getByPrimaryKey(primaryKey));
    }

    @Override
    public boolean isExists(String primaryKey) throws RuntimeException, SQLException, ClassNotFoundException {
        return orderDAO.isExists(primaryKey);
    }
}
