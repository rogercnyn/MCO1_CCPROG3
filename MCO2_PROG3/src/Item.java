/**
 * Items is a class that handles the details of each item that are inside the vending machine.
 */

public class Item {
    private String itemName;
    private int itemCalories;

    /**
     * This is the constructor that accepts name as parameter.
     * @param name - contains the product name of the product
     */
    public Item(String itemName)
    {
        this.itemName = itemName;
    }

    /**
     * This is the constructor that accepts name and calories
     * as parameters and sets everything to the corresponding values given.
     * @param name - contains the product name of the product
     * @param calories - contains the calories of the product
     */
    public Item(String itemName, int itemCalories)
    {
        this.itemName = itemName;
        this.itemCalories = itemCalories;
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
     * getItemName returns the object's item name
     * @return item name
     */
    public String getItemName() {
        return itemName;
    }

     /**
     * getItemCalories returns the object's item calories
     * @return item calories
     */
    public int getItemCalories() {
        return itemCalories;
    }

}
