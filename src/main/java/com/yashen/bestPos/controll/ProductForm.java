package com.yashen.bestPos.controll;

import com.jfoenix.controls.JFXButton;
import com.yashen.bestPos.bo.BOFactory;
import com.yashen.bestPos.bo.BOTypes;
import com.yashen.bestPos.bo.custom.ProductBO;
import com.yashen.bestPos.controll.customer.AddNewCustomerController;
import com.yashen.bestPos.controll.customer.UpdateCustomerForm;
import com.yashen.bestPos.controll.product.AddNewProductController;
import com.yashen.bestPos.controll.product.UpdateProductController;
import com.yashen.bestPos.dto.ProductDTO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class ProductForm implements Initializable {

    @FXML
    public JFXButton updateProductBtn;
    @FXML
    public JFXButton deleteProductBtn;
    public TableColumn<ProductDTO,String> pIdCol;
    @FXML
    private TableView<ProductDTO> productTbl;

    @FXML
    private TableColumn<ProductDTO,String> productCol;

    @FXML
    private TableColumn<ProductDTO,Double> priceCol;

    @FXML
    private TableColumn<ProductDTO,Integer> qtyCol;

    @FXML
    private TextField searchTxt;

    ProductBO productBO = BOFactory.getInstance().getBO(BOTypes.PRODUCT);

    @FXML
    void addProductBtnOnAction(ActionEvent event) throws IOException {
        URL resource = this.getClass().getResource("/views/product/AddNewProduct.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        Parent load = fxmlLoader.load();
        AddNewProductController controller = fxmlLoader.getController();
        controller.init(this);
        Stage stage = new Stage();
        stage.setScene(new Scene(load));
        stage.centerOnScreen();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    @FXML
    void deleteDeleteBtnOnAction(ActionEvent event) {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Product");
            alert.setContentText("Are you sure ?");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK) {
                boolean isDeleted = productBO.deleteProduct(productTbl.getSelectionModel().getSelectedItem().getId());
                if (isDeleted){
                    new Alert(Alert.AlertType.INFORMATION,"Successfully deleted Product !").show();
                    lordTable();
                }else {
                    new Alert(Alert.AlertType.ERROR,"Faild process. try again!").show();
                }
            }else {
                return;
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void updateProductBtnOnAction(ActionEvent event) throws IOException {
        URL resource = this.getClass().getResource("/views/product/UpdateProductForm.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        Parent load = fxmlLoader.load();
        UpdateProductController controller = fxmlLoader.getController();
        controller.init(productTbl.getSelectionModel().getSelectedItem(),this);
        Stage stage = new Stage();
        stage.setScene(new Scene(load));
        stage.centerOnScreen();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateProductBtn.setDisable(true);
        deleteProductBtn.setDisable(true);
        lordTable();
        productTbl.getSelectionModel().selectedItemProperty().addListener((observableValue, pre, curr) -> {
            if (curr!=pre || curr!=null){
                updateProductBtn.setDisable(false);
                deleteProductBtn.setDisable(false);
            }

        });
        productTbl.refresh();
    }

    public void lordTable() {

        productTbl.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("Id"));
        productTbl.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("Desc"));
        productTbl.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("Price"));
        productTbl.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("Qty"));

        try {
            ProductBO productBO = BOFactory.getInstance().getBO(BOTypes.PRODUCT);
            ArrayList<ProductDTO> allProducts = productBO.getAllProducts();
            productTbl.setItems(FXCollections.observableArrayList(allProducts));
            for (ProductDTO productDTO:FXCollections.observableArrayList(allProducts)){
                System.out.println(productDTO.getId());
            }

            searchTxt.textProperty().addListener((observableValue, pre, curr) -> {
                if (!Objects.equals(pre, curr)) {
                    productTbl.getItems().clear();
                    try {
                        productTbl.setItems(FXCollections.observableArrayList(productBO.findByProductName(curr)));
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }

            });
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
