package com.yashen.bestPos.bo.custom;

import com.yashen.bestPos.bo.SuperBO;
import com.yashen.bestPos.dto.CustomerDTO;
import com.yashen.bestPos.dto.DeliveryAgencyDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DeliveryAgencyBO extends SuperBO {
    boolean saveAgency (DeliveryAgencyDTO deliveryAgencyDTO) throws RuntimeException, SQLException, ClassNotFoundException;
    boolean updateAgency (DeliveryAgencyDTO deliveryAgencyDTO) throws RuntimeException, SQLException, ClassNotFoundException;
    boolean deleteAgency (String id) throws RuntimeException, SQLException, ClassNotFoundException;
    ArrayList<DeliveryAgencyDTO> getAllAgencies() throws RuntimeException, SQLException, ClassNotFoundException;
    DeliveryAgencyDTO getById(String id) throws RuntimeException, SQLException, ClassNotFoundException;
    boolean isExistsThisAgency(String id) throws RuntimeException, SQLException, ClassNotFoundException;
    ArrayList<DeliveryAgencyDTO> findByAgenciesName(String searchQuary) throws SQLException, ClassNotFoundException;
}
