package com.yashen.bestPos.controll;

import com.yashen.bestPos.util.Navigation;
import com.yashen.bestPos.util.Route;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class MainFormController {

    @FXML
    private AnchorPane paneContainer;



    public void signInBtnOnAction(ActionEvent actionEvent) {
        try {
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

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }
}
