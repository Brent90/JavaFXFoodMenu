package Orders;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Database.DAO;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

/**
 *
 * @author slinger
 */
public class SubmitOrder {

    private File file = new File("src/Orders/orderQueue.txt");
    private Random random = new Random();
    private final DAO databaseOperations = new DAO();

//       public void submitOrder(Orders order) {
//
//        try (FileWriter writer = new FileWriter(file, true)) {
//            writer.write("Order: " + order.getOrders() + " " + now +  "\n");
//            writer.close();
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//
//    }
    public void submitOrder(Orders order) {
        String time = new SimpleDateFormat("yyyy/MM-dd/HH:mm").format(new Date());
        try {
             databaseOperations.insertOrder(time, order.getOrders(), orderNumberGenerator());
        } catch (Exception e) {
            //try again if you are lucky enough to generate two numbers that are equal
            submitOrder(order);
            System.out.println(e.getMessage());
        }
       

    }
    
    public int orderNumberGenerator() {
        //random number 5000 - 20000
        return random.nextInt((20000-5000) + 1) + 5000;
    }

}
