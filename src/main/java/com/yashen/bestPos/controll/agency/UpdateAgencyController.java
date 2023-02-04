package com.yashen.bestPos.controll.agency;

import com.jfoenix.controls.JFXTextField;
import com.yashen.bestPos.bo.BOFactory;
import com.yashen.bestPos.bo.BOTypes;
import com.yashen.bestPos.bo.custom.DeliveryAgencyBO;
import com.yashen.bestPos.controll.DeliveryAgencyController;
import com.yashen.bestPos.dto.DeliveryAgencyDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

import java.sql.SQLException;

public class UpdateAgencyController {

    static DeliveryAgencyDTO agency;
    DeliveryAgencyController deliveryAgencyController;
    DeliveryAgencyBO deliveryAgencyBO = BOFactory.getInstance().getBO(BOTypes.AGENCY);
    public void init(DeliveryAgencyDTO deliveryAgencyDTO , DeliveryAgencyController deliveryAgencyController){
        agency = deliveryAgencyDTO;
        this.deliveryAgencyController = deliveryAgencyController;
        lordData();
    }

    private void lordData() {
        idTxt.setText(agency.getComId());
        nameTxt.setText(agency.getComName());
        contactTxt.setText(agency.getComContact());
        emailTxt.setText(agency.getComEmail());
    }

    @FXML
    private JFXTextField idTxt;

    @FXML
    private JFXTextField nameTxt;

    @FXML
    private JFXTextField contactTxt;

    @FXML
    private JFXTextField emailTxt;

    @FXML
    void updateBtnOnAction(ActionEvent event) {
        try {
            boolean b = deliveryAgencyBO.updateAgency(new DeliveryAgencyDTO(
                    idTxt.getText(),
                    nameTxt.getText(),
                    contactTxt.getText(),
                    emailTxt.getText()
            ));
            if (b){
                new Alert(Alert.AlertType.CONFIRMATION,"Updated").show();
                deliveryAgencyController.lordTable();
            }else{
                new Alert(Alert.AlertType.WARNING,"Not Updated").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.WARNING,e.getMessage()).show();
        }
    }

}
