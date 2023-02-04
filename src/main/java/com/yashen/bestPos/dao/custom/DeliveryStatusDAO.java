package com.yashen.bestPos.dao.custom;

import com.yashen.bestPos.dao.CrudDAO;
import com.yashen.bestPos.entity.DeliveryStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface DeliveryStatusDAO extends CrudDAO<DeliveryStatus,String> {
    ArrayList<DeliveryStatus> getStatusList(ResultSet rst) throws RuntimeException,ClassNotFoundException, SQLException;
    String getLstTrackId() throws RuntimeException,ClassNotFoundException,SQLException;
}
