package com.yashen.bestPos.controll;

import com.yashen.bestPos.util.Navigation;
import com.yashen.bestPos.util.Route;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ContainerController {

    @FXML
    private AnchorPane paneContainer;

    @FXML
    private Label timeLbl;

    @FXML
    private Label userNameLbl;

    @FXML
    private Label dateLbl;


    @FXML
    void deliveryBtnOnAction(ActionEvent event) {

    }

    @FXML
    void internalOperationOnAction(ActionEvent event) {

    }

    @FXML
    void manageCustomersBtnOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Route.CUSTOMER);
    }

    @FXML
    void manageProductsBtnOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Route.PRODUCT);
    }

    @FXML
    void placeOrderBtnOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Route.PLACE_ORDER);
    }

    @FXML
    public void OrdersBtnOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.ORDER_DETAILS);
    }
}
