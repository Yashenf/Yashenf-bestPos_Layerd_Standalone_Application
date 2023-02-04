package com.yashen.bestPos.bo.custom.impl;

import com.yashen.bestPos.bo.custom.DeliveryAgencyBO;
import com.yashen.bestPos.bo.util.Converter;
import com.yashen.bestPos.dao.custom.DeliveryCompanyDAO;
import com.yashen.bestPos.dao.custom.DeliveryStatusDAO;
import com.yashen.bestPos.dao.util.DaoFactory;
import com.yashen.bestPos.dao.util.DaoTypes;
import com.yashen.bestPos.dto.DeliveryAgencyDTO;
import com.yashen.bestPos.entity.DeliveryCompany;

import java.sql.SQLException;
import java.util.ArrayList;

public class DeliveryAgencyBOImpl implements DeliveryAgencyBO {
    DeliveryCompanyDAO deliveryCompanyDAO = DaoFactory.getInstance().getDao(DaoTypes.DELIVERY_COMPANY);
    Converter converter = new Converter();
    @Override
    public boolean saveAgency(DeliveryAgencyDTO deliveryAgencyDTO) throws RuntimeException, SQLException, ClassNotFoundException {
         return deliveryCompanyDAO.save(converter.toDeliveryAgency(deliveryAgencyDTO));
    }

    @Override
    public boolean updateAgency(DeliveryAgencyDTO deliveryAgencyDTO) throws RuntimeException, SQLException, ClassNotFoundException {
        return deliveryCompanyDAO.update(converter.toDeliveryAgency(deliveryAgencyDTO));
    }

    @Override
    public boolean deleteAgency(String id) throws RuntimeException, SQLException, ClassNotFoundException {
        return deliveryCompanyDAO.delete(id);
    }

    @Override
    public ArrayList<DeliveryAgencyDTO> getAllAgencies() throws RuntimeException, SQLException, ClassNotFoundException {
        ArrayList<DeliveryCompany> all = deliveryCompanyDAO.getAll();
        ArrayList<DeliveryAgencyDTO> agencies = new ArrayList<>();
        for (DeliveryCompany dc: all){
            agencies.add(converter.fromDeliveryAgency(dc));
            System.out.println(dc.getComName());
        }
        return agencies;
    }

    @Override
    public DeliveryAgencyDTO getById(String id) throws RuntimeException, SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean isExistsThisAgency(String id) throws RuntimeException, SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<DeliveryAgencyDTO> findByAgenciesName(String searchQuary) throws SQLException, ClassNotFoundException {
        return null;
    }
}
