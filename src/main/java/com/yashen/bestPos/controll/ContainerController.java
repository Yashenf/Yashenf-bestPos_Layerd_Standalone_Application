package com.yashen.bestPos.controll;

import com.yashen.bestPos.util.Navigation;
import com.yashen.bestPos.util.Route;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

public class ContainerController implements Initializable {

    @FXML
    private AnchorPane paneContainer;

    @FXML
    private Label timeLbl;

    @FXML
    private Label userNameLbl;

    @FXML
    private Label dateLbl;

    Date currentDate = new Date();
    SimpleDateFormat dateFomat = new SimpleDateFormat("MM/dd/yyyy");
    SimpleDateFormat timeFomat = new SimpleDateFormat("h:mm a");

    @FXML
    void deliveryBtnOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Route.DELIVERY);
    }

    @FXML
    void internalOperationOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Route.USER);
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

    private void setTime() {
        Timeline time = new Timeline(
                new KeyFrame(Duration.ZERO, event -> {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss a");
                    timeLbl.setText(LocalDateTime.now().format(formatter));

                }), new KeyFrame(Duration.seconds(1)));
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }

    private void setDate() {
        dateLbl.setText(new SimpleDateFormat("yyyy:MM:dd").format(new Date()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTime();
        setDate();
    }
}
