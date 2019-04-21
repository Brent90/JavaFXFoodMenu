/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxfoodmenu;

import Database.DatabaseConnector;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author slinger
 */
public class JavaFXFoodMenu extends Application {
    
    DatabaseConnector connector = new DatabaseConnector();

    @Override
    public void start(Stage primaryStage) throws SQLException {
        
        
        try {
            // Read file fxml and draw interface.
            Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
            root.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            primaryStage.setScene(new Scene(root));
            
            //remove title bar
            primaryStage.initStyle(StageStyle.TRANSPARENT);
           
            
            primaryStage.show();

            //  dbConnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
