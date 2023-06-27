public class Items 
{
    private String itemName;
    private int itemPrice, itemCalories;
    private int itemQuantity;

    public Items()
    {
        this.itemName = "";
        this.itemPrice = 0;
        this.itemCalories = 0;
        this.itemQuantity = 0;
    }

    public Items(String name, int price, int calories, int quantity)
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

    public int getItemPrice() 
    {
        return itemPrice;
    }

    public int getItemCalories() 
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

    public void setItemPrice(int itemPrice) 
    {
        this.itemPrice = itemPrice;
    }

    public void setItemCalories(int itemCalories) 
    {
        this.itemCalories = itemCalories;
    }

    public void setItemQuantity(int itemQuantity) 
    {
        this.itemQuantity = itemQuantity;
    }
}
