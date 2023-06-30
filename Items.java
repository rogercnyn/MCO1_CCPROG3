/**
 * Items is a class that handles the details of each item that are inside the vending machine.
 * The following are the attributes of this class:
 *  <li> itemName - contains the name of the product
 *  <li> itemPrice - contains the price of the product
 *  <li> itemCalories - contains the calories of the product
 *  <li> itemQuantity - contains the quantity of the product
 */

public class Items 
{
    private String itemName;
    private int itemPrice, itemCalories;
    private int itemQuantity;

    /**
     * This is the constructor that do not accept any parameters and set everything to its default value.
     */
    public Items()
    {
        this.itemName = "";
        this.itemPrice = 0;
        this.itemCalories = 0;
        this.itemQuantity = 0;
    }

    /**
     * This is the constructor that accepts name, price, calories, 
     * and quantity as parameters and sets everything to the corresponding values given.
     * @param name - contains the product name of the product
     * @param price - contains the price of the product
     * @param calories - contains the calories of the product
     * @param quantity - contains the quantity of the product
     */

    public Items(String name, int price, int calories, int quantity)
    {
        this.itemName = name;
        this.itemPrice = price;
        this.itemCalories = calories;
        this.itemQuantity = quantity;
    }

    /**
     * getItemName returns the object's item name
     * @return item name
     */
    public String getItemName() 
    {
        return itemName;
    }

    /**
     * getItemPrice returns the object's item price
     * @return item price
     */
    public int getItemPrice() 
    {
        return itemPrice;
    }

    /**
     * getItemCalories returns the object's item calories
     * @return item calories
     */
    public int getItemCalories() 
    {
        return itemCalories;
    }

    /**
     * getItemQuantity returns the object's item quantity
     * @return item quantity
     */
    public int getItemQuantity() 
    {
        return itemQuantity;
    }

    /**
     * setItemName updates the item name
     * @param itemName - contains the new item name
     */

    public void setItemName(String itemName) 
    {
        this.itemName = itemName;
    }

    /**
     * setItemPrice updates the item price
     * @param itemPrice - contains the new item price
     */
    public void setItemPrice(int itemPrice) 
    {
        this.itemPrice = itemPrice;
    }

    /**
     * setItemCalories updates the item calories
     * @param itemCalories - contains the new item calories
     */
    public void setItemCalories(int itemCalories) 
    {
        this.itemCalories = itemCalories;
    }

    /**
     * setItemQuantity updates the item quantity
     * @param itemQuantity - contains the new item quantity
     */
    public void setItemQuantity(int itemQuantity) 
    {
        this.itemQuantity = this.itemQuantity + itemQuantity;
    }
}
