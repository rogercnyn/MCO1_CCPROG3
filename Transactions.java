import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.LocalDateTime;    

public class Transactions 
{
    private String itemName;
    private int totalPrice;
    private int payment;
    private LocalDate date;

    public Transactions(String itemName, int totalPrice, int payment)
    {
        this.itemName = itemName;
        this.totalPrice = totalPrice;
        this.payment = payment;
        this.date = LocalDate.now();
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

    public LocalDate getDate() {
        return date;
    }
}
