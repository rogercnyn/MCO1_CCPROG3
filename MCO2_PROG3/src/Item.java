public class Item {
    private String itemName;
    private int itemCalories;

    public Item(String itemName)
    {
        this.itemName = itemName;
    }

    public Item(String itemName, int itemCalories)
    {
        this.itemName = itemName;
        this.itemCalories = itemCalories;
    }

    public String getItemName() {
        return itemName;
    }

    public int getItemCalories() {
        return itemCalories;
    }

}
