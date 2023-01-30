package com.yashen.bestPos.dao.custom.impl;

import com.yashen.bestPos.dao.custom.DeliveryStatusDAO;
import com.yashen.bestPos.dao.util.CrudUtil;
import com.yashen.bestPos.entity.DeliveryStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DeliveryStatusDAOImpl implements DeliveryStatusDAO {
    @Override
    public boolean save(DeliveryStatus entity) throws RuntimeException, SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO dilevery_status VALUES (?,?,?,?);",
                entity.getDeliveryTrackingNo(),
                entity.getDeliveryAgency(),
                entity.getOrderId(),
                entity.getDeliveryPrice()
        );
    }

    @Override
    public boolean update(DeliveryStatus entity) throws RuntimeException, SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE dilevery_status SET dilevery_agency =?, order_id=?, delevery_charge=? WHERE dilevery_tracking_no = ?;",
                entity.getDeliveryAgency(),
                entity.getOrderId(),
                entity.getDeliveryPrice(),
                entity.getDeliveryTrackingNo()
        );
    }

    @Override
    public boolean delete(String primaryKey) throws RuntimeException, SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM dilevery_status WHERE dilevery_tracking_no=?", primaryKey);
    }

    @Override
    public ArrayList<DeliveryStatus> getAll() throws RuntimeException, SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM dilevery_status");
        return getStatusList(rst);
    }

    @Override
    public DeliveryStatus getByPrimaryKey(String primaryKey) throws RuntimeException, SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM dilevery_status WHERE dilevery_tracking_no = ?", primaryKey);
        rst.next();
        return new DeliveryStatus(
                rst.getString(1),
                rst.getString(2),
                rst.getString(3),
                rst.getDouble(4)
        );
    }

    @Override
    public boolean isExists(String primaryKey) throws RuntimeException, SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM dilevery_status WHERE dilevery_tracking_no = ?", primaryKey);
        if (rst.next()) {
            return true;
        } else {
            return false;
        }
    }

    private ArrayList<DeliveryStatus> getStatusList(ResultSet rst) throws SQLException {
        ArrayList<DeliveryStatus> statuses = new ArrayList<>();
        while (rst.next()){
            statuses.add(new DeliveryStatus(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4)
            ));
        }
        return statuses;
    }
}
