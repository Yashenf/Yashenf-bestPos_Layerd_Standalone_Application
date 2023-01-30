package com.yashen.bestPos.dao.custom.impl;

import com.yashen.bestPos.dao.custom.CustomerDAO;
import com.yashen.bestPos.dao.util.CrudUtil;
import com.yashen.bestPos.db.DBConnection;
import com.yashen.bestPos.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public boolean save(Customer entity) throws RuntimeException, SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO customer VALUES (?,?,?,?,?);",
                entity.getCusId(),
                entity.getCusName(),
                entity.getCusAddress(),
                entity.getCusContactNo(),
                entity.getCusEmail()
        );

    }

    @Override
    public boolean update(Customer entity) throws RuntimeException, SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE customer SET cus_name =?, cus_address=?, cus_contact_no=?, cus_email=? WHERE cus_id = ?;",
                entity.getCusName(),
                entity.getCusAddress(),
                entity.getCusContactNo(),
                entity.getCusEmail(),
                entity.getCusId()
        );
    }

    @Override
    public boolean delete(String primaryKey) throws RuntimeException, SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM customer WHERE cus_id=?", primaryKey);
    }

    @Override
    public ArrayList<Customer> getAll() throws RuntimeException, SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM customer");
        return getCustomersList(rst);
    }

    @Override
    public Customer getByPrimaryKey(String primaryKey) throws RuntimeException, SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM customer WHERE cus_id = ?", primaryKey);
        rst.next();
        return new Customer(rst.getString("cus_id"),
                rst.getString("cus_name"),
                rst.getString("cus_address"),
                rst.getString("cus_contact_no"),
                rst.getString("cus_email")
        );
    }

    @Override
    public boolean isExists(String primaryKey) throws RuntimeException, SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM customer WHERE cus_id = ?", primaryKey);
        if (rst.next()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ArrayList<Customer> findByName(String searchQuary) throws SQLException, ClassNotFoundException {
        searchQuary = "%" + searchQuary + "%";
        ResultSet rst = CrudUtil.execute("SELECT * FROM customer WHERE cus_id LIKE ? OR cus_name LIKE ? OR cus_address LIKE ? " +
                "OR cus_email LIKE ?", searchQuary, searchQuary, searchQuary, searchQuary);
        return getCustomersList(rst);
    }

    public ArrayList<Customer> getCustomersList(ResultSet rst) throws SQLException {
        ArrayList<Customer> customers = new ArrayList<>();
        while (rst.next()) {
            Customer tempCustomer = new Customer(rst.getString("cus_id"),
                    rst.getString("cus_name"),
                    rst.getString("cus_address"),
                    rst.getString("cus_contact_no"),
                    rst.getString("cus_email"));
            customers.add(tempCustomer);
        }
        return customers;
    }
}
