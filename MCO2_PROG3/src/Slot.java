import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Stack;

public class Slot {
    private Stack<Item> items;
    private int CAPACITY;

    public Slot()
    {
        this.items = new Stack<Item>();
        this.CAPACITY = 10;
    }

    public Slot(Stack<Item> items)
    {
        this.items = items;
        this.CAPACITY = 10;
    }

    public Item dispenseItem()
    {
        return items.pop();
    }

    public Item checkItem()
    { // error
        //Item tempItem = items.peek(); 
        try {
            return items.peek();
        } catch (EmptyStackException e) {
            return null;
        }
    }

    public int getNumberOfStock()
    {
        return items.size();
    }

    public Stack<Item> addItem(Item item)
    {
        Stack<Item> stackOfItems = new Stack<Item>();
        for (int i = 0; i < CAPACITY; i++)
        {
            stackOfItems.push(item);
        }

        return stackOfItems;
    }

    public void addItem(Item item, int totalNumberOfRestock)
    {
        for (int i = 0; i < totalNumberOfRestock; i++)
        {
            items.push(item);
        }
    }

    public ArrayList<Slot> regularItems()
    {
        ArrayList<Slot> regularItems = new ArrayList<Slot>();

        Flavor okinawa = new Flavor("Okinawa", 30);
        Flavor wintermelon = new Flavor("Wintermelon", 30);
        Flavor hokkaido = new Flavor("Hokkaido", 30);

        Sinker pearls = new Sinker("Pearls", 88);
        Sinker whitepearls = new Sinker("White Pearls", 90);
        Sinker nata = new Sinker("Nata de Coco", 100);

        Milktea okinawapearls = new Milktea("Okinawa with Pearls", okinawa, pearls, 135);
        Stack<Item> itemOne = addItem(okinawapearls);
        Slot slot1 = new Slot(itemOne);
        regularItems.add(slot1);

        Milktea okinawawhitepearls = new Milktea("Okinawa with White Pearls", okinawa, whitepearls, 135);
        Stack<Item> itemTwo = addItem(okinawawhitepearls);
        Slot slot2 = new Slot(itemTwo);
        regularItems.add(slot2);

        Milktea okinawanata = new Milktea("Okinawa with Nata", okinawa, nata, 135);
        Stack<Item> itemThree = addItem(okinawanata);
        Slot slot3 = new Slot(itemThree);
        regularItems.add(slot3);

        Milktea wintermelonpearls = new Milktea("Wintermelon with Pearls", wintermelon, pearls, 135);
        Stack<Item> itemFour = addItem(wintermelonpearls);
        Slot slot4 = new Slot(itemFour);
        regularItems.add(slot4);

        Milktea wintermelonwhitepearls = new Milktea("Wintermelon with White Pearls", wintermelon, whitepearls, 135);
        Stack<Item> itemFive = addItem(wintermelonwhitepearls);
        Slot slot5 = new Slot(itemFive);
        regularItems.add(slot5);

        Milktea wintermelonnata = new Milktea("Wintermelon with Nata", wintermelon, nata, 135);
        Stack<Item> itemSix = addItem(wintermelonnata);
        Slot slot6 = new Slot(itemSix);
        regularItems.add(slot6);

        Milktea hokkaidopearls = new Milktea("Hokkaido with Pearls", hokkaido, pearls, 135);
        Stack<Item> itemSeven = addItem(hokkaidopearls);
        Slot slot7 = new Slot(itemSeven);
        regularItems.add(slot7);

        Milktea hokkaidowhitepearls = new Milktea("Hokkaido with White Pearls", hokkaido, whitepearls, 135);
        Stack<Item> itemEight = addItem(hokkaidowhitepearls);
        Slot slot8 = new Slot(itemEight);
        regularItems.add(slot8);

        Milktea hokkaidonata = new Milktea("Hokkaido with Nata", hokkaido, nata, 135);
        Stack<Item> itemNine = addItem(hokkaidonata);
        Slot slot9 = new Slot(itemNine);
        regularItems.add(slot9);

        return regularItems;
    }
}
