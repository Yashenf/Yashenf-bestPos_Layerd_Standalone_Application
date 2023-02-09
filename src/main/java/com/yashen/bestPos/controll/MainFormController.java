package com.yashen.bestPos.controll;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.yashen.bestPos.bo.BOFactory;
import com.yashen.bestPos.bo.BOTypes;
import com.yashen.bestPos.bo.custom.UserBO;
import com.yashen.bestPos.dto.UserDTO;
import com.yashen.bestPos.util.Navigation;
import com.yashen.bestPos.util.Route;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

public class MainFormController implements Initializable {

    UserBO userBO = BOFactory.getInstance().getBO(BOTypes.USER);

    public JFXTextField userNameTxt;
    public JFXPasswordField passwordTxt;
    public JFXComboBox<String> accountTypeCmb;
    @FXML
    private AnchorPane paneContainer;

    public void signInBtnOnAction(ActionEvent actionEvent) {
        try {
            if (isCorrectAcc()){
                System.out.println("hi");
                URL resource = this.getClass().getResource("/views/Container.fxml");
                Stage stage = new Stage();
                AnchorPane container = FXMLLoader.load(resource);
                AnchorPane pneContainer = (AnchorPane)container.lookup("#paneContainer");
                Navigation.init(pneContainer);
                stage.setScene(new Scene(container));
                stage.centerOnScreen();
                Stage window =(Stage) paneContainer.getScene().getWindow();
                window.hide();
                Navigation.navigate(Route.CUSTOMER);
                stage.show();
            }else{
                new Alert(Alert.AlertType.WARNING,"try again with correct information !").show();
            }

            } catch (IOException ignored) {
            } catch (SQLException | ClassNotFoundException throwable) {
            throwable.printStackTrace();
            //default username and password :- Yashen123,yash2002
        }
    }

    public boolean isCorrectAcc() throws SQLException, ClassNotFoundException {
        if (!userNameTxt.getText().trim().isEmpty()){
            System.out.println(" username is not null : "+userNameTxt.getText());
            UserDTO userFromId = userBO.getUserFromId(userNameTxt.getText());
            if (userFromId == null){
                new Alert(Alert.AlertType.WARNING,"wrong information !").show();
                return false;
            }
            if (passwordTxt.getText() != null && accountTypeCmb.getValue() != null){
                if (passwordTxt.getText().equals(userFromId.getPassword()) && accountTypeCmb.getValue().equals(userFromId.getAccType())){
                    return true;
                }else{
                    new Alert(Alert.AlertType.WARNING,"Wrong password or accType").show();
                    return false;
                }
            }else {
                new Alert(Alert.AlertType.WARNING,"empty password or acc type!").show();
                return false;
            }
        }else {
            new Alert(Alert.AlertType.WARNING,"enter username !").show();
            return false;
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        accountTypeCmb.getItems().add("MANAGER");
        accountTypeCmb.getItems().add("CASHIER");
    }

}
