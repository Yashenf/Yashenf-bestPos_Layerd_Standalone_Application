package com.yashen.bestPos.controll;

import com.jfoenix.controls.JFXButton;
import com.yashen.bestPos.bo.BOFactory;
import com.yashen.bestPos.bo.BOTypes;
import com.yashen.bestPos.bo.custom.DeliveryAgencyBO;
import com.yashen.bestPos.controll.agency.AddNewAgencyController;
import com.yashen.bestPos.controll.agency.UpdateAgencyController;
import com.yashen.bestPos.controll.customer.AddNewCustomerController;
import com.yashen.bestPos.dto.DeliveryAgencyDTO;
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
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class DeliveryAgencyController implements Initializable {
    public JFXButton updateBtn;
    public JFXButton deleteBtn;
    public JFXButton addBtn;
    @FXML
    public TableView<DeliveryAgencyDTO> agencyTbl;

    DeliveryAgencyBO deliveryAgencyBO = BOFactory.getInstance().getBO(BOTypes.AGENCY);

    @FXML
    void addBtnOnAction(ActionEvent event) throws IOException {
        URL resource = this.getClass().getResource("/views/deliveryAgency/AddNewAgency.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        Parent load = fxmlLoader.load();
//        AddNewAgencyController controller = fxmlLoader.getController();
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
            alert.setTitle("Delete Agency");
            alert.setContentText("Are you sure ?");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK) {
                boolean isDeleted = deliveryAgencyBO.deleteAgency(agencyTbl.getSelectionModel().getSelectedItem().getComId());
                if (isDeleted){
                    new Alert(Alert.AlertType.INFORMATION,"Successfully deleted agency !").show();
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
    void updateBtnOnAction(ActionEvent event) throws IOException {
        URL resource = this.getClass().getResource("/views/deliveryAgency/UpdateAgency.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        Parent load = fxmlLoader.load();
        UpdateAgencyController controller = fxmlLoader.getController();
        controller.init(agencyTbl.getSelectionModel().getSelectedItem() , this);
        Stage stage = new Stage();
        stage.setScene(new Scene(load));
        stage.centerOnScreen();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("--------------------------------Hi-----------------------------------------");
        try {
            lordTable();
            if (agencyTbl.getSelectionModel().getSelectedItem() == null){
                updateBtn.setDisable(true);
                deleteBtn.setDisable(true);
            }
            agencyTbl.getSelectionModel().selectedItemProperty().addListener((observableValue, pre, curr) -> {
                if (curr!=pre || curr!=null){
                    updateBtn.setDisable(false);
                    deleteBtn.setDisable(false);
                }

            });
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void lordTable() throws SQLException, ClassNotFoundException {
        ArrayList<DeliveryAgencyDTO> allAgencies = deliveryAgencyBO.getAllAgencies();
        agencyTbl.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("ComId"));
        agencyTbl.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("ComName"));
        agencyTbl.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("ComContact"));
        agencyTbl.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("ComEmail"));
        agencyTbl.setItems(FXCollections.observableArrayList(allAgencies));
        agencyTbl.refresh();
    }
}
