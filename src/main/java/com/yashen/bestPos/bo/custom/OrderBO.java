package com.yashen.bestPos.bo.custom;

import com.yashen.bestPos.bo.SuperBO;
import com.yashen.bestPos.dto.OrderDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderBO extends SuperBO {
    ArrayList<OrderDTO> getAll() throws RuntimeException, SQLException, ClassNotFoundException;
    OrderDTO getByPrimaryKey(String primaryKey) throws RuntimeException, SQLException, ClassNotFoundException;
    boolean isExists(String primaryKey) throws RuntimeException, SQLException, ClassNotFoundException;
}
