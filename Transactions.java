public class Transactions 
{
    private String itemName;
    private int quantity;
    private double totalPrice;
    private String date;

    public Transactions(String itemName, int quantity, double totalPrice, String date)
    {
        this.itemName = itemName;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
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

    public String getDate() {
        return date;
    }
}
