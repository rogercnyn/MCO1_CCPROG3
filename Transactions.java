/**
 * Transactions handles the details of all of the successful transactions made by the machine.
 *  The following are the attributes of this class:
 *  <li> itemName - contains the name of the product
 *  <li> totalPrice - contains the price of the product
 *  <li> payment - contains the total amount that the user paid
 *  <li> date - contains the date when the transaction took place
 */

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.LocalDateTime;    

public class Transactions 
{
    private String itemName;
    private int totalPrice;
    private int payment;
    private LocalDate date;

    /**
     * This is the constructor that accepts item name, total price, total amount of payment, 
     * parameters and sets everything to the corresponding values given.
     * The date atttribute will record the obtained date from the system.
     * @param itemName
     * @param totalPrice
     * @param payment
     */
    public Transactions(String itemName, int totalPrice, int payment)
    {
        this.itemName = itemName;
        this.totalPrice = totalPrice;
        this.payment = payment;
        this.date = LocalDate.now();
    }

    /**
     * getItemName returns the transaction's item name
     * @return item name of the transaction
     */
    public String getItemName() 
    {
        return itemName;
    }

    /**
     * getTotalPrice returns the transaction's total price
     * @return total price of the transaction
     */
    public int getTotalPrice() 
    {
        return totalPrice;
    }

    /**
     * getPayment returns the transaction's total payment received
     * @return payment to the transaction
     */
    public int getPayment() 
    {
        return payment;
    }

    /**
     * getDate returns the transaction's date
     * @return date of the transaction
     */
    public LocalDate getDate() {
        return date;
    }
}
