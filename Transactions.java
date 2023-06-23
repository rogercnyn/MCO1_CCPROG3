public class Transactions 
{
    private String itemName;
    private int quantity;
    private double totalPrice;
    private double payment;
    private String date;

    public Transactions(String itemName, int quantity, double totalPrice, double payment, String date)
    {
        this.itemName = itemName;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.payment = payment;
        this.date = date;
    }

    public String getItemName() 
    {
        return itemName;
    }

    public int getQuantity() 
    {
        return quantity;
    }

    public double getTotalPrice() 
    {
        return totalPrice;
    }

    public double getPayment() 
    {
        return payment;
    }

    public String getDate() {
        return date;
    }
}
