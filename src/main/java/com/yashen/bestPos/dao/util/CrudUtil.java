package com.yashen.bestPos.dao.util;

import com.yashen.bestPos.db.DBConnection;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CrudUtil {
    public static <T> T execute(String query, Object... args) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(query);

        // assign arguments for sql query
        for (int i = 1; i < args.length + 1; i++) {
            pstm.setObject(i,args[i-1]);
        }

        if (query.startsWith("SELECT") || query.startsWith("select")){
            return (T) pstm.executeQuery();
        }else{
            return (T)((Boolean)(pstm.executeUpdate() > 0));
        }
    }
}