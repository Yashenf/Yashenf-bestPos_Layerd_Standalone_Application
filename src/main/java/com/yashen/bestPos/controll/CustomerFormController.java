package com.yashen.bestPos.controll;

import com.jfoenix.controls.JFXButton;
import com.yashen.bestPos.bo.BOFactory;
import com.yashen.bestPos.bo.BOTypes;
import com.yashen.bestPos.bo.custom.CustomerBO;
import com.yashen.bestPos.controll.customer.AddNewCustomerController;
import com.yashen.bestPos.controll.customer.UpdateCustomerForm;
import com.yashen.bestPos.dto.CustomerDTO;
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
import java.util.*;
import java.util.stream.Collectors;

import static javafx.scene.control.ButtonType.*;

public class CustomerFormController implements Initializable {


    public TableColumn contactCol1;
    public TextField searchTxt;
    public JFXButton updateBtn;
    public JFXButton deleteBtn;
    @FXML
    private TableView<CustomerDTO> customerTbl;

    @FXML
    private TableColumn idCol;

    @FXML
    private TableColumn nameCol;

    @FXML
    private TableColumn cityCol;

    @FXML
    private TableColumn contactCol;

    CustomerBO customerBO = BOFactory.getInstance().getBO(BOTypes.CUSTOMER);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateBtn.setDisable(true);
        deleteBtn.setDisable(true);
        loardTable();
        customerTbl.getSelectionModel().selectedItemProperty().addListener((observableValue, pre, curr) -> {
            if (curr!=pre || curr!=null){
                updateBtn.setDisable(false);
                deleteBtn.setDisable(false);
            }

        });
        customerTbl.refresh();
    }

    public void loardTable() {
        customerTbl.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("cusId"));
        customerTbl.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("cusName"));
        customerTbl.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("cusAddress"));
        customerTbl.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("cusContactNo"));
        customerTbl.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("cusEmail"));

        try {
            ArrayList<CustomerDTO> allCustomers = customerBO.getAllCustomers();
            customerTbl.setItems(FXCollections.observableArrayList(allCustomers));

            searchTxt.textProperty().addListener((observableValue, pre, curr) -> {
                if (!Objects.equals(pre, curr)) {
                    customerTbl.getItems().clear();
                    try {
                        customerTbl.setItems(FXCollections.observableArrayList(customerBO.findByCustomersName(curr)));
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }

            });
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void addCustomerBtnOnAction(ActionEvent event) throws IOException {
        URL resource = this.getClass().getResource("/views/customer/AddNewCustomer.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        Parent load = fxmlLoader.load();
        AddNewCustomerController addBookFormController = fxmlLoader.getController();
        addBookFormController.init(this);
        Stage stage = new Stage();
        stage.setScene(new Scene(load));
        stage.centerOnScreen();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    @FXML
    void deleteCustomerBtnOnAction(ActionEvent event) {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Customer");
            alert.setContentText("Are you sure ?");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK) {
                boolean isDeleted = customerBO.deleteCustomer(customerTbl.getSelectionModel().getSelectedItem().getCusId());
                if (isDeleted){
                    new Alert(Alert.AlertType.INFORMATION,"Successfully deleted customer !").show();
                    loardTable();
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
    void searchTxtOnAction(ActionEvent event) {

    }

    @FXML
    void updateCustomerBtnOnAction(ActionEvent event) throws IOException {
        URL resource = this.getClass().getResource("/views/customer/UpdateCustomerForm.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        Parent load = fxmlLoader.load();
        UpdateCustomerForm controller = fxmlLoader.getController();
        controller.init(customerTbl.getSelectionModel().getSelectedItem(),this);
        Stage stage = new Stage();
        stage.setScene(new Scene(load));
        stage.centerOnScreen();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }


}
