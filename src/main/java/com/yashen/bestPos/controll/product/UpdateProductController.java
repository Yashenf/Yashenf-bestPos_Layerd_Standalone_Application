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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javax.naming.InitialContext;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UpdateProductController implements Initializable {

    @FXML
    private JFXTextField idTxt;

    @FXML
    private JFXTextField priceTxt;

    @FXML
    private JFXTextArea productTxt;

    @FXML
    private JFXTextField qtyTxt;

    ProductDTO selectedItem;

    ProductForm productForm;

    ProductBO productBO;  // initialize from "init" method in bellow

    @FXML
    void updateProductBtnOnAction(ActionEvent event) {
        ProductDTO product = new ProductDTO(
                idTxt.getText(),
                productTxt.getText(),
                Double.parseDouble(priceTxt.getText()),
                Integer.parseInt(qtyTxt.getText())
        );

        try {
            boolean isUpdated = productBO.updateProduct(product);

            if (isUpdated){
                new Alert(Alert.AlertType.INFORMATION,"Product updated !").show();
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

    public void init(ProductDTO selectedItem, ProductForm productForm) {
        this.selectedItem = selectedItem;
        this.productForm = productForm;
        productBO = BOFactory.getInstance().getBO(BOTypes.PRODUCT);
        lordData();
        System.out.println(selectedItem.getPrice());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    private void lordData() {
        idTxt.setText(selectedItem.getId());
        productTxt.setText(selectedItem.getDesc());
        priceTxt.setText(String.valueOf(selectedItem.getPrice()));
        qtyTxt.setText(String.valueOf(selectedItem.getQty()));
    }
}
