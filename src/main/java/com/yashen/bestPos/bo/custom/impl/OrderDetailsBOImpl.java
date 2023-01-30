package com.yashen.bestPos.bo.custom.impl;

import com.yashen.bestPos.bo.custom.OrderDetailsBO;
import com.yashen.bestPos.bo.util.Converter;
import com.yashen.bestPos.dao.custom.OrderDetailsDAO;
import com.yashen.bestPos.dao.util.DaoFactory;
import com.yashen.bestPos.dao.util.DaoTypes;
import com.yashen.bestPos.dto.OrderDTO;
import com.yashen.bestPos.dto.OrderDetailsDTO;
import com.yashen.bestPos.entity.OrderDetails;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailsBOImpl implements OrderDetailsBO {
    Converter converter = new Converter();
    OrderDetailsDAO orderDetailsDAO = DaoFactory.getInstance().getDao(DaoTypes.ORDER_DETAILS);
    @Override
    public ArrayList<OrderDetailsDTO> getAll() throws RuntimeException, SQLException, ClassNotFoundException {
        ArrayList<OrderDetailsDTO> cart = new ArrayList<>();
        for (OrderDetails o : orderDetailsDAO.getAll()){
            cart.add(new OrderDetailsDTO(
                    o.getOrdId(),
                    o.getProductId(),
                    o.getQty()
            ));
        }
        return cart;
    }

    @Override
    public OrderDetailsDTO getByPrimaryKey(String primaryKey) throws RuntimeException, SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean isExists(String primaryKey) throws RuntimeException, SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<OrderDetailsDTO> getCart(String id) throws RuntimeException, SQLException, ClassNotFoundException {
        ArrayList<OrderDetails> orderCart = orderDetailsDAO.getOrderCart(id);
        ArrayList<OrderDetailsDTO> dtoList = new ArrayList<>();
        for (OrderDetails o:orderCart){
            dtoList.add(converter.fromOrderDetails(o));
        }
        return dtoList;
    }
}
