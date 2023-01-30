package com.yashen.bestPos.controll;

import com.yashen.bestPos.bo.BOFactory;
import com.yashen.bestPos.bo.BOTypes;
import com.yashen.bestPos.bo.custom.CustomerBO;
import com.yashen.bestPos.bo.custom.OrderBO;
import com.yashen.bestPos.bo.custom.OrderDetailsBO;
import com.yashen.bestPos.bo.custom.ProductBO;
import com.yashen.bestPos.controll.view.tm.OrderDetailsTM;
import com.yashen.bestPos.dto.CustomerDTO;
import com.yashen.bestPos.dto.OrderDTO;
import com.yashen.bestPos.dto.OrderDetailsDTO;
import com.yashen.bestPos.dto.ProductDTO;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OrderFormController implements Initializable {

    public Label totalLbl;
    @FXML
    private TableView<OrderDTO> orderTbl;

    @FXML
    private TableView<OrderDetailsTM> cartTbl;

    @FXML
    private Label customerNameLbl;

    @FXML
    private Label orderIdLbl;


    OrderBO orderBO = BOFactory.getInstance().getBO(BOTypes.ORDER);
    OrderDetailsBO orderDetailsBO = BOFactory.getInstance().getBO(BOTypes.ORDER_DETAILS);
    ProductBO productBO = BOFactory.getInstance().getBO(BOTypes.PRODUCT);
    CustomerBO customerBO = BOFactory.getInstance().getBO(BOTypes.CUSTOMER);
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            lordOrders();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void lordOrders() throws SQLException, ClassNotFoundException {
        orderTbl.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("OrdId"));
        orderTbl.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("CustomerId"));
        orderTbl.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("OrdDate"));
        orderTbl.getSelectionModel().selectedItemProperty().addListener((observableValue, pre, curr) -> {
            try {
                lordOrderDetails(curr.getOrdId());
                setCustomerName(curr.getCustomerId());
                customerNameLbl.setText(customerBO.getByCustomerId(curr.getCustomerId()).getCusName());
                orderIdLbl.setText(String.valueOf(curr.getOrdId()));
                cartTbl.refresh();
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        ArrayList<OrderDTO> all = orderBO.getAll();
        orderTbl.setItems(FXCollections.observableArrayList(all));
    }

    private void setCustomerName(String id) {
        try {
            CustomerDTO byCustomerId = customerBO.getByCustomerId(id);
            customerNameLbl.setText(byCustomerId.getCusName());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void lordOrderDetails(String ordId) throws SQLException, ClassNotFoundException {
        /*tmList.clear();*/
        double tot = 0;
        ArrayList<OrderDetailsDTO> all = orderDetailsBO.getCart(ordId);
        ArrayList<OrderDetailsTM> tmList = new ArrayList<>();
        for (OrderDetailsDTO o:all){
            ProductDTO byProductKey = productBO.getByProductKey(o.getProductId());
            String desc = byProductKey.getDesc();
            double price = byProductKey.getPrice();
            tmList.add(new OrderDetailsTM(desc,o.getQty(),price));
            tot  += o.getQty()*price;
        }
        cartTbl.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("Product"));
        cartTbl.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("Qty"));
        cartTbl.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("Price"));
        cartTbl.setItems(FXCollections.observableArrayList(tmList));
        totalLbl.setText(String.valueOf(tot));
    }
}
