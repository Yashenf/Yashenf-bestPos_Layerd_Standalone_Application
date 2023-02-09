package com.yashen.bestPos.controll;

import com.jfoenix.controls.JFXButton;
import com.yashen.bestPos.bo.BOFactory;
import com.yashen.bestPos.bo.BOTypes;
import com.yashen.bestPos.bo.custom.UserBO;
import com.yashen.bestPos.controll.customer.AddNewCustomerController;
import com.yashen.bestPos.controll.customer.UpdateCustomerForm;
import com.yashen.bestPos.controll.users.AddNewUserController;
import com.yashen.bestPos.controll.users.UpdateUserController;
import com.yashen.bestPos.dto.UserDTO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class InternalOperationsFormController implements Initializable {
    public JFXButton updateBtn;
    public JFXButton deleteBtn;
    UserBO userBO = BOFactory.getInstance().getBO(BOTypes.USER);
    @FXML
    private TableView<UserDTO> userTbl;




    @FXML
    void createAccBtnOnAction(ActionEvent event) throws IOException {
        URL resource = this.getClass().getResource("/views/Users/AddNewUser.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        Parent load = fxmlLoader.load();
        AddNewUserController controller = fxmlLoader.getController();
        controller.init(this);
        Stage stage = new Stage();
        stage.setScene(new Scene(load));
        stage.centerOnScreen();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    @FXML
    void deleteBtnOnAction(ActionEvent event) {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete User");
            alert.setContentText("Are you sure ?");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK) {
                boolean isDeleted = userBO.deleteUser(userTbl.getSelectionModel().getSelectedItem().getUsername());
                if (isDeleted){
                    new Alert(Alert.AlertType.INFORMATION,"Successfully deleted user !").show();
                    lordTable();
                }else {
                    new Alert(Alert.AlertType.ERROR,"Faild process. try again!").show();
                }
            }else {
                return;
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void updateAccBtnOnAction(ActionEvent event) throws IOException {
        URL resource = this.getClass().getResource("/views/Users/UpdateUser.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        Parent load = fxmlLoader.load();
        UpdateUserController controller = fxmlLoader.getController();
        controller.init(userTbl.getSelectionModel().getSelectedItem(),this);
        Stage stage = new Stage();
        stage.setScene(new Scene(load));
        stage.centerOnScreen();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            lordTable();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void lordTable() throws SQLException, ClassNotFoundException {
        updateBtn.setDisable(true);
        deleteBtn.setDisable(true);
        ArrayList<UserDTO> allUsers = userBO.getAllUsers();
        userTbl.setItems(FXCollections.observableArrayList(allUsers));
        userTbl.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("Name"));
        userTbl.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("Address"));
        userTbl.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("Username"));
        userTbl.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("AccType"));

        userTbl.getSelectionModel().selectedItemProperty().addListener((observableValue, pre, curr) -> {
            if (curr!=pre || curr!=null){
                updateBtn.setDisable(false);
                deleteBtn.setDisable(false);
            }

        });
    }
}
