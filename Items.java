public class Items 
{
    private String itemName;
    private double itemPrice, itemCalories;
    private int itemQuantity;

    public Items()
    {
        this.itemName = "";
        this.itemPrice = 0;
        this.itemCalories = 0;
        this.itemQuantity = 0;
    }

    public Items(String name, double price, double calories, int quantity)
    {
        this.itemName = name;
        this.itemPrice = price;
        this.itemCalories = calories;
        this.itemQuantity = quantity;
    }

    public String getItemName() 
    {
        return itemName;
    }

    public double getItemPrice() 
    {
        return itemPrice;
    }

    public double getItemCalories() 
    {
        return itemCalories;
    }

    public int getItemQuantity() 
    {
        return itemQuantity;
    }

    public void setItemName(String itemName) 
    {
        this.itemName = itemName;
    }

    public void setItemPrice(double itemPrice) 
    {
        this.itemPrice = itemPrice;
    }

    public void setItemCalories(double itemCalories) 
    {
        this.itemCalories = itemCalories;
    }

    public void setItemQuantity(int itemQuantity) 
    {
        this.itemQuantity = itemQuantity;
    }
}
