package com.yashen.bestPos.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Navigation {
    public static AnchorPane paneContainer;

    public static void init(AnchorPane paneContainer){
        Navigation.paneContainer=paneContainer;
    }

    public static void navigate(Route route) throws IOException {
        paneContainer.getChildren().clear();
        Stage container = (Stage)paneContainer.getScene().getWindow();
        URL resource=null;
        switch (route){
            case MAIN_MENU:
                resource= Navigation.class.getResource("/views/MainForm.fxml");
                container.setTitle("Welcome to LMS v1.0.0");
                break;

            case CONTAINER:
                resource= Navigation.class.getResource("/views/Container.fxml");
                container.setTitle("Welcome to LMS v1.0.0");
                break;

            case CUSTOMER:
                resource= Navigation.class.getResource("/views/CustomerForm.fxml");
                container.setTitle("Welcome to LMS v1.0.0");
                break;
            case PLACE_ORDER:
                resource = Navigation.class.getResource("/views/PlaceOrderForm.fxml");
                break;
            case PRODUCT:
                resource = Navigation.class.getResource("/views/productForm.fxml");
                break;
            case ORDER_DETAILS:
                resource = Navigation.class.getResource("/views/OrderForm.fxml");
                break;
            case DELIVERY:
                resource = Navigation.class.getResource("/views/DeliveryOptionForm.fxml");
                break;
            case AGENCY:
                resource = Navigation.class.getResource("/views/DeliveryAgency.fxml");
                break;
        }


        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        AnchorPane load = fxmlLoader.load();
        paneContainer.getChildren().addAll(load);
    }
}
