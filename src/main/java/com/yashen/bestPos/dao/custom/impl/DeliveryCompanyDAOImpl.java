package com.yashen.bestPos.dao.custom.impl;

import com.yashen.bestPos.dao.custom.DeliveryCompanyDAO;
import com.yashen.bestPos.dao.util.CrudUtil;
import com.yashen.bestPos.entity.DeliveryCompany;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DeliveryCompanyDAOImpl implements DeliveryCompanyDAO {
    @Override
    public boolean save(DeliveryCompany entity) throws RuntimeException, SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO dilevery_agency VALUES (?,?,?,?);",
                entity.getComId(),
                entity.getComName(),
                entity.getComContact(),
                entity.getComEmail()
        );
    }

    @Override
    public boolean update(DeliveryCompany entity) throws RuntimeException, SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE dilevery_agency SET com_name =?, com_contact=?, com_email=? WHERE com_id = ?;",
                entity.getComName(),
                entity.getComContact(),
                entity.getComEmail(),
                entity.getComId()
        );
    }

    @Override
    public boolean delete(String primaryKey) throws RuntimeException, SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM dilevery_agency WHERE com_id=?", primaryKey);
    }

    @Override
    public ArrayList<DeliveryCompany> getAll() throws RuntimeException, SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM dilevery_agency");
        return getAgencies(rst);
    }

    @Override
    public DeliveryCompany getByPrimaryKey(String primaryKey) throws RuntimeException, SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM dilevery_agency WHERE com_id = ?", primaryKey);
        rst.next();
        return new DeliveryCompany(
                rst.getString(1),
                rst.getString(2),
                rst.getString(3),
                rst.getString(4)
        );
    }

    @Override
    public boolean isExists(String primaryKey) throws RuntimeException, SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM dilevery_agency WHERE com_id = ?", primaryKey);
        if (rst.next()) {
            return true;
        } else {
            return false;
        }
    }

    private ArrayList<DeliveryCompany> getAgencies(ResultSet rst) throws SQLException {
        ArrayList<DeliveryCompany> agencies = new ArrayList<>();
        while(rst.next()){
            agencies.add(new DeliveryCompany(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            ));
        }
        return agencies;
    }
}
