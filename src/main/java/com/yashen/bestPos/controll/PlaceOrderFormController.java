package com.yashen.bestPos.controll;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.yashen.bestPos.bo.BOFactory;
import com.yashen.bestPos.bo.BOTypes;
import com.yashen.bestPos.bo.custom.CustomerBO;
import com.yashen.bestPos.bo.custom.PlaceOrderBO;
import com.yashen.bestPos.controll.view.tm.PlaceOrderTM;
import com.yashen.bestPos.dto.CustomerDTO;
import com.yashen.bestPos.dto.OrderDTO;
import com.yashen.bestPos.dto.OrderDetailsDTO;
import com.yashen.bestPos.dto.ProductDTO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PlaceOrderFormController implements Initializable {

    public TableColumn optionCol;
    public Label totLbl;
    PlaceOrderBO placeOrderBO = BOFactory.getInstance().getBO(BOTypes.PLACE_ORDER);

    public Label productLbl;
    public Label orderIdTxt;
    @FXML
    private TableView<PlaceOrderTM> placeOrderTbl;

    @FXML
    private TableColumn noCol;

    @FXML
    private TableColumn productCol;

    @FXML
    private TableColumn priceCol;

    @FXML
    private TableColumn qtyCol;

    @FXML
    private TableColumn totalCol;

    @FXML
    private JFXComboBox<String> productCmb;

    @FXML
    private JFXTextField qtyTxt;


    @FXML
    private JFXTextField discountTxt;

    @FXML
    private JFXComboBox<String> customerCmb;

    @FXML
    private Label subTotLbl;

    ArrayList<PlaceOrderTM> tmList = new ArrayList<>();

    @FXML
    void addItemBtnOnAction(ActionEvent event) {
        lordTable();
    }


    @FXML
    void placeOrderBtnOnAction(ActionEvent event) {

        System.out.println("-----------------------------------------------------");
        for (PlaceOrderTM tm :tmList){
            System.out.println(tm.getNo()+" , "+tm.getProduct()+" , "+tm.getQty());
        }
        System.out.println("-----------------------------------------------------");

        ArrayList<OrderDetailsDTO> cart =new ArrayList<>();
        for (PlaceOrderTM pot:tmList){
            cart.add(new OrderDetailsDTO(
                    orderIdTxt.getText(),
                    pot.getProduct(),
                    pot.getQty()
            ));
        }

        try {
            if(placeOrderBO.saveOrder(new OrderDTO(orderIdTxt.getText(),customerCmb.getValue(),new Date(2023,01,29)))){
                placeOrderBO.saveOrderDetails(cart);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        placeOrderTbl.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("No"));
        placeOrderTbl.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("Product"));
        placeOrderTbl.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("Price"));
        placeOrderTbl.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("Qty"));
        placeOrderTbl.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("Total"));
        placeOrderTbl.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("ClearBtn"));
        lordOrderId();
        lordCustomerID();
        lordProductList();
        if (tmList.size() != 0) {
            placeOrderTbl.setItems(FXCollections.observableArrayList(tmList));
            lordTable();
        }
    }

    private void lordTable() {

        try {
            setDataToTable();
            placeOrderTbl.setItems(FXCollections.observableArrayList(tmList));
            setTotal();
            for (PlaceOrderTM p : tmList){
                System.out.println(p.getNo()+" , "+p.getProduct()+" , "+p.getQty());
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        /*
        * String no;
        String product;
        double price;
        int qty;
        double total;
        Button clearBtn;

        * */
    }

    private void setDataToTable() throws SQLException, ClassNotFoundException {
        if (productCmb.getValue() != null && customerCmb.getValue() != null && Integer.parseInt(qtyTxt.getText())>0){
            ProductDTO product = placeOrderBO.getProductById(productCmb.getValue());
            if (!isDuplicateProduct()){
                PlaceOrderTM placeOrderTM = new PlaceOrderTM(
                        tmList.size()+1+"",
                        productCmb.getValue(),
                        product.getPrice(),
                        Integer.parseInt(qtyTxt.getText()),
                        product.getPrice() * product.getQty(),
                        new Button("clear")
                );
                tmList.add(placeOrderTM);
            }
        }else{
            new Alert(Alert.AlertType.WARNING,"some fields are empty. check and try again !").show();
        }
    }

    private boolean isDuplicateProduct() {
        for (PlaceOrderTM pot : tmList){
            if (productCmb.getValue().equals(pot.getProduct())){
                int newQty = pot.getQty()+Integer.parseInt(qtyTxt.getText());
                pot.setQty(newQty);
                setTotal();
                return true;
            }
        }
        return false;
    }


    private void setTotal() {
        double tot = 0;
        for (PlaceOrderTM tm : tmList) {
            tot += tm.getTotal();
        }
        totLbl.setText(String.valueOf(tot));
        subTotLbl.setText(String.valueOf(String.valueOf(tot)));
    }

    private void lordProductList() {
        try {
            ArrayList<ProductDTO> productList = placeOrderBO.getProductList();
            for (ProductDTO p : productList) {
                productCmb.getItems().add(p.getId());
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void lordCustomerID() {
        try {
            ArrayList<CustomerDTO> allCustomers = placeOrderBO.getCustomerList();
            for (CustomerDTO c : allCustomers) {
                customerCmb.getItems().add(c.getCusId());
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void lordOrderId() {
        try {
            orderIdTxt.setText(placeOrderBO.getLastOrderId());
            System.out.println(placeOrderBO.getLastOrderId());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void productCmbAction(ActionEvent actionEvent) {
        String value = productCmb.getValue();
        try {
            ProductDTO product = placeOrderBO.getProductById(value);
            productLbl.setText(product.getDesc());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void discountCalcAction(ActionEvent actionEvent) {

        try {
            double pricePerDiscount = Double.parseDouble(totLbl.getText());
            if (Double.parseDouble(discountTxt.getText()) <= 0) {
                subTotLbl.setText(String.valueOf(pricePerDiscount));
            }
            double newPrice = (pricePerDiscount -= (pricePerDiscount / 100) * Double.parseDouble(discountTxt.getText()));
            subTotLbl.setText(String.valueOf(newPrice));
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


}

