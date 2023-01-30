package com.yashen.bestPos.bo.custom.impl;

import com.yashen.bestPos.bo.custom.CustomerBO;
import com.yashen.bestPos.bo.util.Converter;
import com.yashen.bestPos.dao.custom.CustomerDAO;
import com.yashen.bestPos.dao.util.DaoFactory;
import com.yashen.bestPos.dao.util.DaoTypes;
import com.yashen.bestPos.dto.CustomerDTO;
import com.yashen.bestPos.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAO customerDAO;
    Converter converter;

    public CustomerBOImpl(){
        // constructor injection (DI)
        customerDAO = DaoFactory.getInstance().getDao(DaoTypes.CUSTOMER);
        converter = new Converter();
    }
    @Override
    public boolean saveCustomer(CustomerDTO customerDTO) throws RuntimeException, SQLException, ClassNotFoundException {
        return customerDAO.save(converter.toCustomer(customerDTO));
    }

    @Override
    public boolean updateCustomer(CustomerDTO customerDTO) throws RuntimeException, SQLException, ClassNotFoundException {
        return customerDAO.update(converter.toCustomer(customerDTO));
    }

    @Override
    public boolean deleteCustomer(String id) throws RuntimeException, SQLException, ClassNotFoundException {
        return customerDAO.delete(id);
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws RuntimeException, SQLException, ClassNotFoundException {
        ArrayList<Customer> all = customerDAO.getAll();
        ArrayList<CustomerDTO> customerDTOS= new ArrayList<>();
        for(Customer customer : all){
            customerDTOS.add(converter.formCustomer(customer));
        }
        return customerDTOS;
    }

    @Override
    public CustomerDTO getByCustomerId(String id) throws RuntimeException, SQLException, ClassNotFoundException {
        return converter.formCustomer(customerDAO.getByPrimaryKey(id));
    }

    @Override
    public boolean isExistsThisCustomer(String id) throws RuntimeException, SQLException, ClassNotFoundException {
        return customerDAO.isExists(id);
    }

    @Override
    public ArrayList<CustomerDTO> findByCustomersName(String searchQuary) throws SQLException, ClassNotFoundException {
        ArrayList<Customer> all = customerDAO.findByName(searchQuary);
        ArrayList<CustomerDTO> customerDTOS= new ArrayList<>();
        for(Customer customer : all){
            customerDTOS.add(converter.formCustomer(customer));
        }
        return customerDTOS;
    }
}
