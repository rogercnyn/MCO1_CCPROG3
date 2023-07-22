import java.util.Stack;

public class Slot {
    private Stack<Items> items;
    private final int CAPACITY;

    public Slot(Meal meal){
        this.items = new Stack<Items>();

        addItem((Items)meal);
        CAPACITY = 10;
    }

    public Slot(Side side){
        this.items = new Stack<Items>();
        addItem(side);
        CAPACITY = 10;
    }
    
    public Slot(Drink drink){
        this.items = new Stack<Items>();
        addItem(drink);
        CAPACITY = 10;
    }
    public Slot(Extra extra){
        this.items = new Stack<Items>();
        addItem(extra);
        CAPACITY = 10;
    }

    public void addItem(Items item){
        items.push(item);
    }

    public Items dispenseItem(){
        return items.pop();
    }

    public Stack<Items> getItems() {
        return items;
    }

    public int getCAPACITY() {
        return CAPACITY;
    }

    public Items checkItem(){
        return items.peek();
    }
}
