package com.yashen.bestPos.bo.custom;

import com.yashen.bestPos.bo.SuperBO;
import com.yashen.bestPos.dto.DeliveryAgencyDTO;
import com.yashen.bestPos.dto.DeliveryStatusDTO;
import com.yashen.bestPos.dto.OrderDTO;

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;

public interface DeliveryStatusBO extends SuperBO {
    ArrayList<OrderDTO> getPendingOrderList() throws RuntimeException,ClassNotFoundException, SQLException;
    boolean createDelivery(DeliveryStatusDTO deliveryStatusDTO) throws RuntimeException,ClassNotFoundException, SQLException;
    ArrayList<DeliveryAgencyDTO> getAllAgensies() throws RuntimeException,ClassNotFoundException, SQLException;
    String getLastTrackId() throws RuntimeException,ClassNotFoundException,SQLException;
    ArrayList<DeliveryStatusDTO> getAll() throws RuntimeException,ClassNotFoundException,SQLException;

}
