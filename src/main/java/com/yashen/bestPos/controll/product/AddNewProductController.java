package com.yashen.bestPos.controll.product;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.yashen.bestPos.bo.BOFactory;
import com.yashen.bestPos.bo.BOTypes;
import com.yashen.bestPos.bo.custom.ProductBO;
import com.yashen.bestPos.controll.ProductForm;
import com.yashen.bestPos.dto.ProductDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

import java.sql.SQLException;

public class AddNewProductController {

    @FXML
    private JFXTextField idTxt;

    @FXML
    private JFXTextField priceTxt;

    @FXML
    private JFXTextArea productTxt;

    @FXML
    private JFXTextField qtyTxt;

    private ProductForm productForm;

    private ProductBO productBo; // initialize from "init" method in bellow

    @FXML
    void addProductBtnOnAction(ActionEvent event) {
        try {
            boolean isSaved = productBo.saveProduct(new ProductDTO(
                    idTxt.getText(),
                    productTxt.getText(),
                    Double.parseDouble(priceTxt.getText()),
                    Integer.parseInt(qtyTxt.getText())
            ));

            if (isSaved){
                new Alert(Alert.AlertType.INFORMATION,"Product Saved !").show();
                idTxt.clear();
                productTxt.clear();
                priceTxt.clear();
                qtyTxt.clear();
                productForm.lordTable();
            }else {
                new Alert(Alert.AlertType.ERROR,"Procces is faild . Try again !").show();
                idTxt.selectAll();
                idTxt.requestFocus();
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void init(ProductForm productForm){
        this.productForm = productForm;
        productBo = BOFactory.getInstance().getBO(BOTypes.PRODUCT);
    }

}
