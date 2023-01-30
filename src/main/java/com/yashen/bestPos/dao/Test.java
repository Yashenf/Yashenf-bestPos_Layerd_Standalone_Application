package com.yashen.bestPos.dao;

import com.yashen.bestPos.dao.custom.impl.CustomerDAOImpl;
import com.yashen.bestPos.entity.Customer;

import java.sql.SQLException;

public class Test {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        /*boolean isSaved = new CustomerDAOImpl().save(new Customer("C004", "yashen savindu","Matugama", "034336598", "efg@hotmail.com"));
        boolean isSaved2 = new CustomerDAOImpl().save(new Customer("C005", "Amal Perera","Matugama", "011985672", "efg@hotmail.com"));
        boolean isSaved3 = new CustomerDAOImpl().save(new Customer("C006", "Saman Kumara","Aluthgama", "0779892325", "str@hotmail.com"));
        boolean isSaved4 = new CustomerDAOImpl().save(new Customer("C007", "Ashmoka Dilshan","Matara", "078336598", "xyz@yahoo.com"));

        if (isSaved){
            System.out.println("customer saved");
        }else {
            System.out.println("Customer not saved");
        }*/

        /*boolean isUpdated = new CustomerDAOImpl().update(new Customer("C002", "Yashen Savindu", "Aluthgama", "071975976", "abc@gmail.com"));*/
        /*boolean isDeleted = new CustomerDAOImpl().delete("C002");*/
        /*if (true){
            System.out.println("Quarry Ok...");
        }else {
            System.out.println("Operation Failed!");
        }*/

        /*ArrayList<Customer> all = new CustomerDAOImpl().findByName("Al");
        for (Customer c: all) {
            System.out.println(c.getCusName());
        }*/

        /*boolean b = new CustomerDAOImpl().isExists("C002");
        System.out.println(b);*/

        /*boolean flag = new ProductDAOImpl().save(new Product("P001", "Msi gtx 3090", 1999, 30));
        System.out.println(flag);*/

        /*boolean isSaved = new OrderDAOImpl().save(new Order("Ord001", "C003"));
        System.out.println(isSaved);*/

        /*Order order = new OrderDAOImpl().getByPrimaryKey("ord001");
        System.out.println(order.getCustomerId() +" , "+order.getOrdDate());*/

        /*boolean isUpdate = new OrderDAOImpl().update(new Order("ord001", "C001"));
        System.out.println(isUpdate);*/

        /*boolean isUpdate = new OrderDAOImpl().delete("ord001");
        System.out.println(isUpdate);*/

        /*ArrayList<Order> all = new OrderDAOImpl().getAll();
        for (Order o: all) {
            System.out.println(o.getOrdDate());
        }*/

        /*boolean isSave = new OrderDetailsDAOImpl().save(new OrderDetails("ord001", "p001", 10));
        System.out.println(isSave);*/

        /*boolean isSave = new OrderDetailsDAOImpl().update(new OrderDetails("ord001", "p001", 20));
        System.out.println(isSave);*/

        /*boolean isDelete = new OrderDetailsDAOImpl().delete("ord001", "p001");
        System.out.println(isDelete);*/

        /*OrderDetails byPrimaryKey = new OrderDetailsDAOImpl().getByPrimaryKey("ord001", "p001");
        System.out.println(byPrimaryKey.getOrdId());*/

        /*boolean exists = new OrderDetailsDAOImpl().isExists("ord001", "p001");
        System.out.println(exists);*/

        /*ArrayList<OrderDetails> all = new OrderDetailsDAOImpl().getAll();
        for (OrderDetails o:all) {
            System.out.println(o.getOrdId());
        }*/
        /*OrderDetailsDAO o = new OrderDetailsDAOImpl();*/

        /*boolean isSaved = new DeliveryCompanyDAOImpl().save(new DeliveryCompany(
                "Com001",
                "ABC Express",
                "+94113696585",
                "Example.gmail.com"
        ));
        System.out.println(isSaved);*/

        /*new DeliveryCompanyDAOImpl().update(new DeliveryCompany(
                "Com001",
                "XYZ Express",
                "+94113696585",
                "Example.gmail.com"
        ));*/

        /*ArrayList<DeliveryCompany> all = new DeliveryCompanyDAOImpl().getAll();
        for (DeliveryCompany o:all) {
            System.out.println(o.getComContact());
        }*/

        /*boolean isDeleted = new DeliveryCompanyDAOImpl().delete("Com001");
        System.out.println(isDeleted);*/

       /* boolean save = new DeliveryStatusDAOImpl().save(new DeliveryStatus("Trac001", "Com001", "ord001", 450));
        System.out.println(save);*/

        /*boolean trac001 = new DeliveryStatusDAOImpl().isExists("Trac002");
        System.out.println(trac001);*/

         /*boolean save = new DeliveryStatusDAOImpl().update(new DeliveryStatus("Trac001", "Com001", "ord001", 250));
        System.out.println(save);*/

        /*boolean trac001 = new DeliveryStatusDAOImpl().delete("Trac001");
        System.out.println(trac001);*/
    }
}
