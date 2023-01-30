package com.yashen.bestPos.bo.custom;

import com.yashen.bestPos.bo.SuperBO;
import com.yashen.bestPos.dto.CustomerDTO;
import com.yashen.bestPos.dto.OrderDTO;
import com.yashen.bestPos.dto.OrderDetailsDTO;
import com.yashen.bestPos.dto.ProductDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PlaceOrderBO extends SuperBO {
    ArrayList<CustomerDTO> getCustomerList () throws RuntimeException, SQLException, ClassNotFoundException;
    ArrayList<ProductDTO> getProductList () throws RuntimeException, SQLException , ClassNotFoundException;
    boolean saveOrder (OrderDTO orderDTO) throws RuntimeException, SQLException , ClassNotFoundException;
    boolean saveOrderDetails (ArrayList<OrderDetailsDTO> orderDetailsDTOList) throws RuntimeException, SQLException , ClassNotFoundException;
    String getLastOrderId () throws RuntimeException, SQLException, ClassNotFoundException;
    ProductDTO getProductById(String id) throws RuntimeException, SQLException, ClassNotFoundException;
}
