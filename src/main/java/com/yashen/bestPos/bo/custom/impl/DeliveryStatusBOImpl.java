package com.yashen.bestPos.bo.custom.impl;

import com.yashen.bestPos.bo.custom.DeliveryStatusBO;
import com.yashen.bestPos.bo.util.Converter;
import com.yashen.bestPos.dao.custom.DeliveryCompanyDAO;
import com.yashen.bestPos.dao.custom.DeliveryStatusDAO;
import com.yashen.bestPos.dao.custom.QueryDAO;
import com.yashen.bestPos.dao.util.DaoFactory;
import com.yashen.bestPos.dao.util.DaoTypes;
import com.yashen.bestPos.dto.DeliveryAgencyDTO;
import com.yashen.bestPos.dto.DeliveryStatusDTO;
import com.yashen.bestPos.dto.OrderDTO;
import com.yashen.bestPos.entity.DeliveryCompany;
import com.yashen.bestPos.entity.DeliveryStatus;
import com.yashen.bestPos.entity.Order;

import java.sql.SQLException;
import java.util.ArrayList;

public class DeliveryStatusBOImpl implements DeliveryStatusBO {
    QueryDAO queryDAO = DaoFactory.getInstance().getDao(DaoTypes.QUERY);
    DeliveryStatusDAO deliveryStatusDAO = DaoFactory.getInstance().getDao(DaoTypes.DELIVERY_STATUS);
    DeliveryCompanyDAO deliveryCompanyDAO = DaoFactory.getInstance().getDao(DaoTypes.DELIVERY_COMPANY);
    Converter converter = new Converter();
    @Override
    public ArrayList<OrderDTO> getPendingOrderList() throws RuntimeException, ClassNotFoundException, SQLException {
        ArrayList<Order> allPendingOrdersList = queryDAO.getAllPendingOrdersList();
        ArrayList<OrderDTO> list = new ArrayList<>();
        for (Order order:allPendingOrdersList){
            list.add(converter.fromOrder(order));
        }
        return list;
    }

    @Override
    public boolean createDelivery(DeliveryStatusDTO deliveryStatusDTO) throws RuntimeException, ClassNotFoundException, SQLException {
        return deliveryStatusDAO.save(converter.toDeliveryStatus(deliveryStatusDTO));
    }

    @Override
    public ArrayList<DeliveryAgencyDTO> getAllAgensies() throws RuntimeException, ClassNotFoundException, SQLException {
        ArrayList<DeliveryCompany> all = deliveryCompanyDAO.getAll();
        ArrayList<DeliveryAgencyDTO> dtoList = new ArrayList<>();
        for (DeliveryCompany deliveryCompany:all){
            dtoList.add(converter.fromDeliveryAgency(deliveryCompany));
        }
        return dtoList;
    }

    @Override
    public String getLastTrackId() throws RuntimeException, ClassNotFoundException, SQLException {
        String lastTrackId = deliveryStatusDAO.getLstTrackId();
        if (lastTrackId == null){
            lastTrackId="TRC00000";
        }
        String[] orderIdArr = lastTrackId.split("[C]");
        int lastNumber = Integer.parseInt(orderIdArr[1])+1;
        String newId = String.format("TRC%05d",lastNumber);
        System.out.println("New Order id is : "+newId);
        return newId;
    }

    @Override
    public ArrayList<DeliveryStatusDTO> getAll() throws RuntimeException, ClassNotFoundException, SQLException {
        ArrayList<DeliveryStatus> all = deliveryStatusDAO.getAll();
        ArrayList<DeliveryStatusDTO> dtoList = new ArrayList<>();
        for (DeliveryStatus deliveryStatus: all){
            dtoList.add(converter.fromDeliveryStatus(deliveryStatus));
        }
        return dtoList;
    }
}
