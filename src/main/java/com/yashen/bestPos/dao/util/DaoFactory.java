package com.yashen.bestPos.dao.util;

import com.yashen.bestPos.dao.SuperDAO;
import com.yashen.bestPos.dao.custom.impl.*;

public class DaoFactory {
    private static DaoFactory daoFactory;

    private DaoFactory() {
    }

    public static DaoFactory getInstance() {
        return daoFactory == null ? (daoFactory = new DaoFactory()) : daoFactory;
    }

    public<T extends SuperDAO> T getDao(DaoTypes daoType){
        switch (daoType){
            case CUSTOMER:
                return (T) new CustomerDAOImpl();
            case PRODUCT:
                return (T) new ProductDAOImpl();
            case ORDER:
                return (T) new OrderDAOImpl();
            case DELIVERY_COMPANY:
                return (T) new DeliveryCompanyDAOImpl();
            case DELIVERY_STATUS:
                return (T) new DeliveryStatusDAOImpl();
            case ORDER_DETAILS:
                return (T) new OrderDetailsDAOImpl();
            case QUERY:
                return (T) new QueryDAOImpl();
            default:
                return null;
        }
    }

}
