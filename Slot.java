import java.util.Stack;

public class Slot {
    private Stack<Items> items;
    private final int CAPACITY;

    public Slot(Items item){
        item = new Stack<Item>();
        addItem(item);
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
