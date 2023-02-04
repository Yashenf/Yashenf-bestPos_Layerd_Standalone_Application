package com.yashen.bestPos.controll;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.yashen.bestPos.bo.BOFactory;
import com.yashen.bestPos.bo.BOTypes;
import com.yashen.bestPos.bo.custom.DeliveryStatusBO;
import com.yashen.bestPos.dto.DeliveryAgencyDTO;
import com.yashen.bestPos.dto.DeliveryStatusDTO;
import com.yashen.bestPos.dto.OrderDTO;
import com.yashen.bestPos.util.Navigation;
import com.yashen.bestPos.util.Route;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DeliveryOptionFormController implements Initializable {
    DeliveryStatusBO deliveryStatusBO = BOFactory.getInstance().getBO(BOTypes.DELIVERY_STATUS);

    @FXML
    private TableView<DeliveryStatusDTO> deliveryTbl;

    @FXML
    private JFXComboBox<String> orderIdCmb;

    @FXML
    private JFXComboBox<String> agencyCmb;

    @FXML
    private JFXTextField whightTxt;

    @FXML
    void addBtnOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        double price = Double.parseDouble(whightTxt.getText())*10+150;
        deliveryStatusBO.createDelivery(new DeliveryStatusDTO(deliveryStatusBO.getLastTrackId(),agencyCmb.getValue(),
                orderIdCmb.getValue(),price));
        deliveryTbl.refresh();
    }

    @FXML
    void newAgenciesBtnOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Route.AGENCY);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            lordAgencies();
            lordOrders();
            lordTbl();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void lordTbl() throws SQLException, ClassNotFoundException {
        ArrayList<DeliveryStatusDTO> all = deliveryStatusBO.getAll();
        deliveryTbl.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("DeliveryTrackingNo"));
        deliveryTbl.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("DeliveryAgency"));
        deliveryTbl.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("OrderId"));
        deliveryTbl.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("DeliveryCharge"));
        deliveryTbl.setItems(FXCollections.observableArrayList(all));
        deliveryTbl.refresh();
    }

    private void lordOrders() throws SQLException, ClassNotFoundException {
        ArrayList<OrderDTO> pendingOrderList = deliveryStatusBO.getPendingOrderList();
        for (OrderDTO orderDTO:pendingOrderList){
            orderIdCmb.getItems().add(orderDTO.getOrdId());
        }
    }

    private void lordAgencies() throws SQLException, ClassNotFoundException {
        ArrayList<DeliveryAgencyDTO> allAgensies = deliveryStatusBO.getAllAgensies();
        for (DeliveryAgencyDTO deliveryAgencyDTO:allAgensies){
            agencyCmb.getItems().add(deliveryAgencyDTO.getComId());
        }
    }
}
