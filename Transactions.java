import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    

public class Transactions 
{
    private String itemName;
    private int totalPrice;
    private int payment;
    private LocalDateTime date;

    public Transactions(String itemName, int totalPrice, int payment)
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

    public int getTotalPrice() 
    {
        return totalPrice;
    }

    public int getPayment() 
    {
        return payment;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
