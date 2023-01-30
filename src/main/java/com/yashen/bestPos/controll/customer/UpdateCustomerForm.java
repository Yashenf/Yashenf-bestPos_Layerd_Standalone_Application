package com.yashen.bestPos.controll.customer;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.yashen.bestPos.bo.BOFactory;
import com.yashen.bestPos.bo.BOTypes;
import com.yashen.bestPos.bo.custom.CustomerBO;
import com.yashen.bestPos.controll.CustomerFormController;
import com.yashen.bestPos.dto.CustomerDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UpdateCustomerForm{

    @FXML
    private JFXTextField idTxt;

    @FXML
    private JFXTextField nameTxt;

    @FXML
    private JFXTextArea addressTxt;

    @FXML
    private JFXTextField contactTxt;

    @FXML
    private JFXTextField emailTxt;

    CustomerDTO customerDTO;
    CustomerFormController customerFormController;
    CustomerBO customerBO = BOFactory.getInstance().getBO(BOTypes.CUSTOMER);

    public void init(CustomerDTO selectedItem, CustomerFormController customerFormController){
        customerDTO = selectedItem;
        this.customerFormController = customerFormController;
        lordData();
    }

    @FXML
    void customerUpdateBtnOnAction(ActionEvent event) {
        //Data validation
        if (idTxt.getText().isBlank() || !idTxt.getText().matches("^[A-Za-z0-9 ]+$")) {
            new Alert(Alert.AlertType.ERROR, "customer id  invalid or empty").show();
            idTxt.selectAll();
            idTxt.requestFocus();
            return;
        } else if (nameTxt.getText().isBlank() || !nameTxt.getText().matches("^[A-Za-z0-9 ]+$")) {
            new Alert(Alert.AlertType.ERROR, "your name Cannot be empty or invalid").show();
            nameTxt.selectAll();
            nameTxt.requestFocus();
            return;
        } else if (addressTxt.getText().isBlank() || !addressTxt.getText().matches("^[A-Za-z0-9 ]+$")) {
            new Alert(Alert.AlertType.ERROR, "your address or city Cannot be empty or invalid").show();
            addressTxt.selectAll();
            addressTxt.requestFocus();
            return;
        } else if (contactTxt.getText().isBlank() || !contactTxt.getText().matches("^[0-9]+$")) {
            new Alert(Alert.AlertType.ERROR, "contact name invalid or null").show();
            contactTxt.selectAll();
            contactTxt.requestFocus();
            return;
        } /*else if (emailTxt.getText().isBlank() || !emailTxt.getText().matches("^(. +)@(\\S+) $")) {
            new Alert(Alert.AlertType.ERROR, "email invalid or null").show();
            emailTxt.selectAll();
            emailTxt.requestFocus();
            return;
        }*/

        CustomerDTO customer = new CustomerDTO(
                idTxt.getText(),
                nameTxt.getText(),
                addressTxt.getText(),
                contactTxt.getText(),
                emailTxt.getText()
        );

        try {
            boolean isUpdated = customerBO.updateCustomer(customer);
            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION,"Succesfuly to update the customer !").show();
                customerFormController.loardTable();
                return;
            }else {
                new Alert(Alert.AlertType.ERROR,"Failed to update the customer !").show();
                idTxt.selectAll();
                idTxt.requestFocus();
                return;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    private void lordData() {
        idTxt.setText(customerDTO.getCusId());
        nameTxt.setText(customerDTO.getCusName());
        addressTxt.setText(customerDTO.getCusAddress());
        contactTxt.setText(customerDTO.getCusContactNo());
        emailTxt.setText(customerDTO.getCusEmail());
    }
}
