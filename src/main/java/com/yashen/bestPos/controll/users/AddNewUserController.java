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
import com.yashen.bestPos.entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

import java.sql.SQLException;

public class AddNewUserController {
    UserBO userBO = BOFactory.getInstance().getBO(BOTypes.USER);
    InternalOperationsFormController internalOperationsFormController;
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
    private JFXButton saveBtn;

    @FXML
    void saveBtnOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        boolean b = userBO.saveUser(new UserDTO(
                nameTxt.getText(),
                addressTxt.getText(),
                usernameTxt.getText(),
                passwordTxt.getText(),
                accountTypeCmb.getValue()
        ));

        if (b){
            new Alert(Alert.AlertType.CONFIRMATION,"Saved").show();
            internalOperationsFormController.lordTable();
        }else {
            new Alert(Alert.AlertType.WARNING,"try again!").show();
        }
    }

    public void init(InternalOperationsFormController internalOperationsFormController) {
        this.internalOperationsFormController = internalOperationsFormController;
        accountTypeCmb.getItems().add("MANAGER");
        accountTypeCmb.getItems().add("CASHIER");
    }
}
