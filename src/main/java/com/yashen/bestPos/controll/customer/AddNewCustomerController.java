package com.yashen.bestPos.controll.customer;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.yashen.bestPos.bo.BOFactory;
import com.yashen.bestPos.bo.BOTypes;
import com.yashen.bestPos.bo.custom.CustomerBO;
import com.yashen.bestPos.controll.CustomerFormController;
import com.yashen.bestPos.dto.CustomerDTO;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

import java.sql.SQLException;

public class AddNewCustomerController {
    public JFXTextField idTxt;
    public JFXTextField nameTxt;
    public JFXTextArea addressTxt;
    public JFXTextField contactTxt;
    public JFXTextField emailTxt;

    private CustomerBO customerBO = BOFactory.getInstance().getBO(BOTypes.CUSTOMER);
    private CustomerFormController customerFormController;
    public void init (CustomerFormController customerFormController){
        this.customerFormController = customerFormController;
    }

    public void customersaveBtnOnAction(ActionEvent actionEvent) {
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
            boolean isSaved = customerBO.saveCustomer(customer);
            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION,"Succesfuly to Save the customer !").show();
                idTxt.clear();
                nameTxt.clear();
                addressTxt.clear();
                contactTxt.clear();
                emailTxt.clear();
                customerFormController.loardTable();
                return;
            }else {
                new Alert(Alert.AlertType.ERROR,"Failed to Save the customer !").show();
                idTxt.selectAll();
                idTxt.requestFocus();
                return;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
