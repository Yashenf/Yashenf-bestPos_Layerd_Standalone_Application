package com.yashen.bestPos.bo.custom.impl;

import com.yashen.bestPos.bo.custom.ProductBO;
import com.yashen.bestPos.bo.util.Converter;
import com.yashen.bestPos.dao.custom.OrderDetailsDAO;
import com.yashen.bestPos.dao.custom.ProductDAO;
import com.yashen.bestPos.dao.util.DaoFactory;
import com.yashen.bestPos.dao.util.DaoTypes;
import com.yashen.bestPos.db.DBConnection;
import com.yashen.bestPos.dto.CustomerDTO;
import com.yashen.bestPos.dto.ProductDTO;
import com.yashen.bestPos.entity.Product;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductBOImpl implements ProductBO {
    ProductDAO productDAO;
    Converter converter;
    Connection connection;
    OrderDetailsDAO orderDetailsDAO;
    public ProductBOImpl(){
        productDAO = DaoFactory.getInstance().getDao(DaoTypes.PRODUCT);
        converter = new Converter();
        connection = DBConnection.getInstance().getConnection();
        orderDetailsDAO = DaoFactory.getInstance().getDao(DaoTypes.ORDER_DETAILS);
    }
    @Override
    public boolean saveProduct(ProductDTO productDTO) throws RuntimeException, SQLException, ClassNotFoundException {
        boolean isSaved = productDAO.save(converter.toProduct(productDTO));
        return isSaved;
    }

    @Override
    public boolean updateProduct(ProductDTO productDTO) throws RuntimeException, SQLException, ClassNotFoundException {
        return productDAO.update(converter.toProduct(productDTO));
    }

    @Override
    public boolean deleteProduct(String id) throws RuntimeException, SQLException, ClassNotFoundException {
        return productDAO.delete(id);
    }

    @Override
    public ArrayList<ProductDTO> getAllProducts() throws RuntimeException, SQLException, ClassNotFoundException {
        ArrayList<Product> p = productDAO.getAll();
        ArrayList<ProductDTO> productDTOS = new ArrayList<>();
        for (Product product: p) {
            productDTOS.add(converter.fromProduct(product));
        }
        return productDTOS;
    }

    @Override
    public ProductDTO getByProductKey(String id) throws RuntimeException, SQLException, ClassNotFoundException {
        return converter.fromProduct(productDAO.getByPrimaryKey(id));
    }

    @Override
    public boolean isExistsThisProduct(String id) throws RuntimeException, SQLException, ClassNotFoundException {
        return productDAO.isExists(id);
    }

    @Override
    public ArrayList<ProductDTO> findByProductName(String searchQuary) throws SQLException, ClassNotFoundException {
        ArrayList<Product> searchResults = productDAO.findByName(searchQuary);
        ArrayList<ProductDTO> productDTOS = new ArrayList<>();
        for (Product product: searchResults) {
            productDTOS.add(converter.fromProduct(product));
        }
        return productDTOS;
    }
}
