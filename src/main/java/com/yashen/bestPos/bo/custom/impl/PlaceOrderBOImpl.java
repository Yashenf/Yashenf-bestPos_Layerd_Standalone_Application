package com.yashen.bestPos.bo.custom.impl;

import com.yashen.bestPos.bo.BOFactory;
import com.yashen.bestPos.bo.BOTypes;
import com.yashen.bestPos.bo.custom.PlaceOrderBO;
import com.yashen.bestPos.bo.util.Converter;
import com.yashen.bestPos.dao.custom.CustomerDAO;
import com.yashen.bestPos.dao.custom.OrderDAO;
import com.yashen.bestPos.dao.custom.OrderDetailsDAO;
import com.yashen.bestPos.dao.custom.ProductDAO;
import com.yashen.bestPos.dao.util.DaoFactory;
import com.yashen.bestPos.dao.util.DaoTypes;
import com.yashen.bestPos.db.DBConnection;
import com.yashen.bestPos.dto.CustomerDTO;
import com.yashen.bestPos.dto.OrderDTO;
import com.yashen.bestPos.dto.OrderDetailsDTO;
import com.yashen.bestPos.dto.ProductDTO;
import com.yashen.bestPos.entity.Customer;
import com.yashen.bestPos.entity.Product;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlaceOrderBOImpl implements PlaceOrderBO {
    OrderDAO orderDAO = DaoFactory.getInstance().getDao(DaoTypes.ORDER);
    ProductDAO productDAO = DaoFactory.getInstance().getDao(DaoTypes.PRODUCT);
    CustomerDAO customerDAO = DaoFactory.getInstance().getDao(DaoTypes.CUSTOMER);
    OrderDetailsDAO orderDetailsDAO = DaoFactory.getInstance().getDao(DaoTypes.ORDER_DETAILS);
    Converter converter = new Converter();
    Connection connection = DBConnection.getInstance().getConnection();

    @Override
    public ArrayList<CustomerDTO> getCustomerList() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> all = customerDAO.getAll();
        ArrayList<CustomerDTO> customers = new ArrayList<>();
        for (Customer c : all){
            customers.add(converter.formCustomer(c));
        }
        return customers;
    }

    @Override
    public ArrayList<ProductDTO> getProductList() throws SQLException, ClassNotFoundException {
        ArrayList<Product> all = productDAO.getAll();
        ArrayList<ProductDTO> products = new ArrayList<>();
        for (Product p : all){
            products.add(converter.fromProduct(p));
        }
        return products;
    }

    @Override
    public boolean saveOrder(OrderDTO orderDTO) throws SQLException, ClassNotFoundException {
        return orderDAO.save(converter.toOrder(orderDTO));
    }

    @Override
    public boolean saveOrderDetails(ArrayList<OrderDetailsDTO> cart) throws SQLException, ClassNotFoundException {
        connection.setAutoCommit(false);
        try{
            for (OrderDetailsDTO o:cart){
                boolean isSave = orderDetailsDAO.save(converter.toOrderDetails(o));
                if (isSave){
                    Product product = productDAO.getByPrimaryKey(o.getProductId());
                    product.setpQty(product.getpQty()-o.getQty());
                    boolean isUpdated = productDAO.update(product);
                    if (!isUpdated){
                        return false;
                    }
                }else {
                    return false;
                }
            }
            return true;
        }finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public String getLastOrderId() throws RuntimeException, SQLException, ClassNotFoundException {
        String lastOrderId = orderDAO.getLastOrderId();
        if (lastOrderId == null){
            lastOrderId="Ord00000";
        }
        String[] orderIdArr = lastOrderId.split("[d]");
        int lastNumber = Integer.parseInt(orderIdArr[1])+1;
        String newId = String.format("Ord%05d",lastNumber);
        System.out.println("New Order id is : "+newId);
        return newId;
    }

    @Override
    public ProductDTO getProductById(String id) throws RuntimeException, SQLException, ClassNotFoundException {
        return converter.fromProduct(productDAO.getByPrimaryKey(id));
    }


}
