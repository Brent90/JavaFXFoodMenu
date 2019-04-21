
package javafxfoodmenu;

import Database.DAO;
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
    private Spinner<Integer> hotwingSpinner;
    @FXML
    private Spinner<Integer> nachoSpinner;
    @FXML
    private Label total;
    @FXML
    private Button addOrderBtn;

    private final SubmitOrder orderToSubmit = new SubmitOrder();
    private final Orders order = new Orders();
    private final DAO databaseOperations = new DAO();

    //picutre variables
    @FXML
    private ImageView imageView;
    private int positionCount = 0;
    public String[] pictures = {"/Pictures/burger.jpg", "/Pictures/pizza.jpg", "/Pictures/nachos.jpg", "/Pictures/hotWings.jpg", "/Pictures/beer.jpg"};
    @FXML
    private AnchorPane body;
    @FXML
    private FontAwesomeIconView closeIcon;
    @FXML
    private Label menu;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        SpinnerValueFactory<Integer> spinnerForBurger = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 0);
        SpinnerValueFactory<Integer> spinnerForPizza = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 0);
        SpinnerValueFactory<Integer> spinnerForWings = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 0);
        SpinnerValueFactory<Integer> spinnerForNachos = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 0);
        SpinnerValueFactory<Integer> spinnerForBeer = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 0);

        burgerSpinner.setValueFactory(spinnerForBurger);
        pizzaSpinner.setValueFactory(spinnerForPizza);
        hotwingSpinner.setValueFactory(spinnerForWings);
        nachoSpinner.setValueFactory(spinnerForNachos);
        beerSpinner.setValueFactory(spinnerForBeer);

        burgerSpinner.valueProperty().addListener(c -> {
            buttonLogic();
            order.addOrder("Burger and Fries", burgerSpinner.getValue());
        });
        pizzaSpinner.valueProperty().addListener(c -> {
            buttonLogic();
            order.addOrder("Pizza", pizzaSpinner.getValue());
        });
        hotwingSpinner.valueProperty().addListener(c -> {
            buttonLogic();
            order.addOrder("Hot Wings", hotwingSpinner.getValue());
        });
        nachoSpinner.valueProperty().addListener(c -> {
            buttonLogic();
            order.addOrder("Nachos", nachoSpinner.getValue());
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
        String amount = String.format("%.2f", order.getOrderAmount());
        alert.setContentText("Order amount: $" + amount + ". Submit order?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            orderToSubmit.submitOrder(order);
            clearMenu();
        } else {
            //cancels orderToSubmit
        }
    }

    @FXML
    private void updateTotal(ActionEvent event) {
        String amount = String.format("%.2f", order.getOrderAmount());
        total.setText("Total: $" + amount);

    }

    public void buttonLogic() {
        //if everthing is zero, disable the 'add order' button
        if (burgerSpinner.getValue() == 0 && pizzaSpinner.getValue() == 0 && beerSpinner.getValue() == 0 && hotwingSpinner.getValue() == 0 && nachoSpinner.getValue() == 0) {
            addOrderBtn.setDisable(true);
        } else {
            addOrderBtn.setDisable(false);
        }
    }

    public void clearMenu() {
        total.setText("Total $");
        burgerSpinner.getValueFactory().setValue(0);
        pizzaSpinner.getValueFactory().setValue(0);
        hotwingSpinner.getValueFactory().setValue(0);
        nachoSpinner.getValueFactory().setValue(0);
        beerSpinner.getValueFactory().setValue(0);
        

    }

    @FXML
    private void closeMenu(MouseEvent event) {
        System.exit(0);
    }

    //start of picture code
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
    //end of picture code

}
