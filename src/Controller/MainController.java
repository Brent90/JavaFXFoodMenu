/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Orders.Orders;
import Orders.SubmitOrder;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author slinger
 */
public class MainController implements Initializable {

    @FXML
    private Spinner<Integer> beerSpinner;
    @FXML
    private Spinner<Integer> burgerSpinner;
    @FXML
    private Spinner<Integer> pizzaSpinner;
    @FXML
    private Label total;

    private final SubmitOrder orderToSubmit = new SubmitOrder();
    private final Orders order = new Orders();
    @FXML
    private AnchorPane body;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SpinnerValueFactory<Integer> spinner1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 0);
        SpinnerValueFactory<Integer> spinner2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 0);
        SpinnerValueFactory<Integer> spinner3 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 0);

        burgerSpinner.setValueFactory(spinner1);
        pizzaSpinner.setValueFactory(spinner2);
        beerSpinner.setValueFactory(spinner3);

        burgerSpinner.valueProperty().addListener(c -> {
            order.addOrder("Burger and Fries", burgerSpinner.getValue());
        });
        pizzaSpinner.valueProperty().addListener(c -> {
            order.addOrder("Pizza", pizzaSpinner.getValue());
        });
        beerSpinner.valueProperty().addListener(c -> {
            order.addOrder("Beer", beerSpinner.getValue());
        });
    }

    @FXML
    private void addOrder(ActionEvent event) {
        // dialog window for confirmation
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Order Conformation");
        alert.setContentText("Order amount: $" + order.getOrderAmount() + ". Submit order?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            System.out.println("submiting order...");
            //submit orderToSubmit to text file 
            orderToSubmit.submitOrder(order);
        } else {
            //cancels orderToSubmit
        }
    }

    @FXML
    private void updateTotal(ActionEvent event) {
        String amount = String.format("%.2f", order.getOrderAmount());
        total.setText("Total $" + amount);

    }

}
