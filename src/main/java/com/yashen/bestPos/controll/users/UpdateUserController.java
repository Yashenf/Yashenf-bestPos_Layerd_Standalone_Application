package com.yashen.bestPos.controll.users;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.yashen.bestPos.bo.BOFactory;
import com.yashen.bestPos.bo.BOTypes;
import com.yashen.bestPos.bo.custom.UserBO;
import com.yashen.bestPos.controll.InternalOperationsFormController;
import com.yashen.bestPos.dto.UserDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UpdateUserController{

    public Label accTypeLbl;
    UserDTO selectedItem;
    InternalOperationsFormController internalOperationsFormController;
    UserBO userBO = BOFactory.getInstance().getBO(BOTypes.USER);

    @FXML
    private JFXTextField nameTxt;

    @FXML
    private JFXTextField usernameTxt;

    @FXML
    private JFXTextField addressTxt;

    @FXML
    private JFXPasswordField passwordTxt;

    @FXML
    private JFXComboBox<String> accountTypeCmb;

    @FXML
    private JFXButton updateBtn;

    @FXML
    void updateBtnOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        boolean b = userBO.updateUser(new UserDTO(
                nameTxt.getText(),
                addressTxt.getText(),
                usernameTxt.getText(),
                passwordTxt.getText(),
                accountTypeCmb.getValue()
        ));

        if (b){
            new Alert(Alert.AlertType.CONFIRMATION,"Updated").show();
            internalOperationsFormController.lordTable();
        }else {
            new Alert(Alert.AlertType.WARNING,"try again!").show();
        }
    }

    public void init(UserDTO selectedItem, InternalOperationsFormController internalOperationsFormController) {
        this.selectedItem = selectedItem;
        this.internalOperationsFormController = internalOperationsFormController;

        nameTxt.setText(selectedItem.getName());
        addressTxt.setText(selectedItem.getAddress());
        usernameTxt.setText(selectedItem.getUsername());
        accTypeLbl.setText(selectedItem.getAccType());
        passwordTxt.setText(selectedItem.getPassword());
        System.out.println(selectedItem.getPassword());
        accountTypeCmb.getItems().add("MANAGER");
        accountTypeCmb.getItems().add("CASHIER");
    }

}
