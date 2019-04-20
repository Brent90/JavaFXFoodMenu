package Orders;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.FileWriter;
import java.time.LocalTime;

/**
 *
 * @author slinger
 */
public class SubmitOrder {

    private File file = new File("src/Orders/orderQueue.txt");
    private LocalTime now = LocalTime.now();

       
       public void submitOrder(Orders order) {

        try (FileWriter writer = new FileWriter(file, true)) {
            writer.write("Order: " + order.getOrders() + " " + now +  "\n");
            writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
