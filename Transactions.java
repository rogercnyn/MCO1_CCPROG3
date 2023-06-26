import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    

public class Transactions 
{
    private String itemName;
    private double totalPrice;
    private double payment;
    private LocalDateTime date;

    public Transactions(String itemName, double totalPrice, double payment)
    {
        this.itemName = itemName;
        this.totalPrice = totalPrice;
        this.payment = payment;
        this.date = LocalDateTime.now();
    }

    public String getItemName() 
    {
        return itemName;
    }

    public double getTotalPrice() 
    {
        return totalPrice;
    }

    public double getPayment() 
    {
        return payment;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
