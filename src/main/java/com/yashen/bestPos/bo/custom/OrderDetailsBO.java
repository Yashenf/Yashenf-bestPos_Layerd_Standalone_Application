package com.yashen.bestPos.bo.custom;

import com.yashen.bestPos.bo.SuperBO;
import com.yashen.bestPos.dto.OrderDTO;
import com.yashen.bestPos.dto.OrderDetailsDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDetailsBO extends SuperBO {
    ArrayList<OrderDetailsDTO> getAll() throws RuntimeException, SQLException, ClassNotFoundException;
    OrderDetailsDTO getByPrimaryKey(String primaryKey) throws RuntimeException, SQLException, ClassNotFoundException;
    boolean isExists(String primaryKey) throws RuntimeException, SQLException, ClassNotFoundException;
    ArrayList<OrderDetailsDTO> getCart(String id) throws RuntimeException, SQLException, ClassNotFoundException;
}
