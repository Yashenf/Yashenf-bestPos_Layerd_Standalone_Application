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

public class AddNewAgencyController {
    DeliveryAgencyBO deliveryAgencyBO = BOFactory.getInstance().getBO(BOTypes.AGENCY);
    @FXML
    private JFXTextField idTxt;

    @FXML
    private JFXTextField nameTxt;

    @FXML
    private JFXTextField contactTxt;

    @FXML
    private JFXTextField emailTxt;
    public  DeliveryAgencyController deliveryAgencyController;
    public void init(DeliveryAgencyController deliveryAgencyController){
        this.deliveryAgencyController = deliveryAgencyController;
    }

    @FXML
    void saveBtnOnAction(ActionEvent event) {
        try {
            boolean b = deliveryAgencyBO.saveAgency(new DeliveryAgencyDTO(
                    idTxt.getText(),
                    nameTxt.getText(),
                    contactTxt.getText(),
                    emailTxt.getText()
            ));
            if (b){
                new Alert(Alert.AlertType.CONFIRMATION,"Saved").show();
                deliveryAgencyController.lordTable();
            }else{
                new Alert(Alert.AlertType.WARNING,"Not saved").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
