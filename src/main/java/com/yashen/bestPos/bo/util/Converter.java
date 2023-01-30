package com.yashen.bestPos.bo.util;

import com.yashen.bestPos.dto.CustomerDTO;
import com.yashen.bestPos.dto.OrderDTO;
import com.yashen.bestPos.dto.OrderDetailsDTO;
import com.yashen.bestPos.dto.ProductDTO;
import com.yashen.bestPos.entity.Customer;
import com.yashen.bestPos.entity.Order;
import com.yashen.bestPos.entity.OrderDetails;
import com.yashen.bestPos.entity.Product;

public class Converter {

    /*
    * from :- DAO (Entity) To BO
    * to :- BO (DTO) to DAO
    */

    public CustomerDTO formCustomer(Customer customer){
        return new CustomerDTO(
                customer.getCusId(),
                customer.getCusName(),
                customer.getCusAddress(),
                customer.getCusContactNo(),
                customer.getCusEmail()
        );
    }

    public Customer toCustomer(CustomerDTO customerDTO){
        return new Customer(
                customerDTO.getCusId(),
                customerDTO.getCusName(),
                customerDTO.getCusAddress(),
                customerDTO.getCusContactNo(),
                customerDTO.getCusEmail()
        );
    }

    public ProductDTO fromProduct(Product product){
        return new ProductDTO(
                product.getpId(),
                product.getpDesc(),
                product.getpPrice(),
                product.getpQty()
        );
    }

    public Product toProduct(ProductDTO productDTO){
        return new Product(
                productDTO.getId(),
                productDTO.getDesc(),
                productDTO.getPrice(),
                productDTO.getQty()
        );
    }

    public OrderDTO fromOrder(Order order){
        return new OrderDTO(
                order.getOrdId(),
                order.getCustomerId(),
                order.getOrdDate()
        );
    }

    public Order toOrder(OrderDTO orderDTO){
        return new Order(
                orderDTO.getOrdId(),
                orderDTO.getCustomerId(),
                orderDTO.getOrdDate()
        );
    }

    public OrderDetailsDTO fromOrderDetails(OrderDetails orderDetails){
        return new OrderDetailsDTO(
                orderDetails.getOrdId(),
                orderDetails.getProductId(),
                orderDetails.getQty()
        );
    }

    public OrderDetails toOrderDetails(OrderDetailsDTO orderDetailsDTO){
        return new OrderDetails(
                orderDetailsDTO.getOrdId(),
                orderDetailsDTO.getProductId(),
                orderDetailsDTO.getQty()
        );
    }
}
