package com.yashen.bestPos.bo.custom;

import com.yashen.bestPos.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DeliveryAgencyBO {
    boolean saveAgency (CustomerDTO customerDTO) throws RuntimeException, SQLException, ClassNotFoundException;
    boolean updateCustomer (CustomerDTO customerDTO) throws RuntimeException, SQLException, ClassNotFoundException;
    boolean deleteCustomer (String id) throws RuntimeException, SQLException, ClassNotFoundException;
    ArrayList<CustomerDTO> getAllCustomers() throws RuntimeException, SQLException, ClassNotFoundException;
    CustomerDTO getByCustomerId(String id) throws RuntimeException, SQLException, ClassNotFoundException;
    boolean isExistsThisCustomer(String id) throws RuntimeException, SQLException, ClassNotFoundException;
    ArrayList<CustomerDTO> findByCustomersName(String searchQuary) throws SQLException, ClassNotFoundException;
}
