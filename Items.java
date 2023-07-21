/**
 * Items is a class that handles the details of each item that are inside the vending machine.
 * The following are the attributes of this class:
 *  <li> itemName - contains the name of the product
 *  <li> itemPrice - contains the price of the product
 *  <li> itemCalories - contains the calories of the product
 *  <li> itemQuantity - contains the quantity of the product
 */

public abstract class Items 
{
    private String itemName;
    private int itemCalories;

    /**
     * This is the constructor that do not accept any parameters and set everything to its default value.
     */
    public Items()
    {
        this.itemName = "";
        this.itemCalories = 0;
    }

    /**
     * This is the constructor that accepts name, price, calories, 
     * and quantity as parameters and sets everything to the corresponding values given.
     * @param name - contains the product name of the product
     * @param calories - contains the calories of the product
     */

    public Items(String name, int calories)
    {
        this.itemName = name;
        this.itemCalories = calories;
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
     * getItemCalories returns the object's item calories
     * @return item calories
     */
    public int getItemCalories() 
    {
        return itemCalories;
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
     * setItemCalories updates the item calories
     * @param itemCalories - contains the new item calories
     */
    public void setItemCalories(int itemCalories) 
    {
        this.itemCalories = itemCalories;
    }
}
