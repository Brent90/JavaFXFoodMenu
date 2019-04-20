/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxfoodmenu;

import Orders.Orders;
import Orders.SubmitOrder;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
    @FXML
    private Button addOrderBtn;

    private final SubmitOrder orderToSubmit = new SubmitOrder();
    private final Orders order = new Orders();

    //picutre variables
    @FXML
    private ImageView imageView;
    private int positionCount = 0;
    public String[] pictures = {"/Pictures/burger.jpg", "/Pictures/pizza.jpg", "/Pictures/beer.jpg"};

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
            buttonLogic();
            order.addOrder("Burger and Fries", burgerSpinner.getValue());
        });
        pizzaSpinner.valueProperty().addListener(c -> {
            buttonLogic();
            order.addOrder("Pizza", pizzaSpinner.getValue());
        });
        beerSpinner.valueProperty().addListener(c -> {
            buttonLogic();
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
            //submit orderToSubmit to text file 
            orderToSubmit.submitOrder(order);
            clearMenu();
        } else {
            //cancels orderToSubmit
        }
    }

    @FXML
    private void updateTotal(ActionEvent event) {
        String amount = String.format("%.2f", order.getOrderAmount());
        total.setText("Total $" + amount);

    }

    public void buttonLogic() {
        if (burgerSpinner.getValue() == 0 && pizzaSpinner.getValue() == 0 && beerSpinner.getValue() == 0) {
            addOrderBtn.setDisable(true);
        } else {
            addOrderBtn.setDisable(false);
        }
    }

    public void clearMenu() {
        total.setText("Total $");
        burgerSpinner.getValueFactory().setValue(0);
        pizzaSpinner.getValueFactory().setValue(0);
        beerSpinner.getValueFactory().setValue(0);

    }

    @FXML
    private void closeMenu(MouseEvent event) {
        System.exit(0);
    }

    //picture code
    @FXML
    public void nextPicture() {
        if (positionCount == pictures.length - 1) {
            positionCount = 0;
        } else {
            positionCount++;
        }

        Image im = new Image(pictures[positionCount]);
        imageView.setImage(im);

    }

    @FXML
    public void previousPicture() {
        positionCount--;

        if (positionCount < 0) {
            positionCount = pictures.length - 1;
        }

        Image im = new Image(pictures[positionCount]);
        imageView.setImage(im);

    }

}
