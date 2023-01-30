package com.yashen.bestPos.bo;

import com.yashen.bestPos.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getInstance(){
        return boFactory == null ? (boFactory = new BOFactory()):boFactory;
    }

    public <T extends SuperBO> T getBO(BOTypes boType){
        switch (boType){
            case CUSTOMER:
                return (T) new CustomerBOImpl();
            case PRODUCT:
                return (T) new ProductBOImpl();
            case PLACE_ORDER:
                return (T) new PlaceOrderBOImpl();
            case ORDER:
                return (T) new OrderBOImpl();
            case ORDER_DETAILS:
                return (T) new OrderDetailsBOImpl();
            default:
                return null;
        }
    }
}
