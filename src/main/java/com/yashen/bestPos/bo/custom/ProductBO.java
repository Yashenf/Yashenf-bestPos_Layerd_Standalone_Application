package com.yashen.bestPos.bo.custom;

import com.yashen.bestPos.bo.SuperBO;
import com.yashen.bestPos.dto.CustomerDTO;
import com.yashen.bestPos.dto.ProductDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ProductBO extends SuperBO {
    boolean saveProduct (ProductDTO productDTO) throws RuntimeException, SQLException, ClassNotFoundException;
    boolean updateProduct (ProductDTO productDTO) throws RuntimeException, SQLException, ClassNotFoundException;
    boolean deleteProduct (String id) throws RuntimeException, SQLException, ClassNotFoundException;
    ArrayList<ProductDTO> getAllProducts() throws RuntimeException, SQLException, ClassNotFoundException;
    ProductDTO getByProductKey(String id) throws RuntimeException, SQLException, ClassNotFoundException;
    boolean isExistsThisProduct(String id) throws RuntimeException, SQLException, ClassNotFoundException;
    ArrayList<ProductDTO> findByProductName(String searchQuary) throws SQLException, ClassNotFoundException;
}
