package Orders;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author slinger
 */
public class Orders {

    private Map<String, Integer> orders = new HashMap<>();

  
    public void addOrder(String order, int quantity) {
        orders.put(order, quantity);
    }
    
    public double getOrderAmount() {
 
        double totalAmount = 0;

        for (String s : orders.keySet()) {
            if (s.equals("Burger and Fries")) {
                totalAmount += 6.99 * orders.get(s);
            } else if (s.equals("Pizza")) {
                totalAmount += 12.99 * orders.get(s);
            } else if (s.equals("Beer")) {
                totalAmount += 3.99 * orders.get(s);
            }
        }
        

        return totalAmount;
    }
    
    
    public String getOrders() {
        return orders.toString();
    }

}
