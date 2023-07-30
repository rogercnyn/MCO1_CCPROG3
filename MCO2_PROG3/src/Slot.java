import java.util.ArrayList;
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
    {
        return items.peek();
    }

    public int getNumberOfStock()
    {
        return items.size();
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
        Item itemone = new Item(okinawapearls, 118);
        Stack<Item> itemOne = new Stack<Item>();
        for(int i=0;i<10;i++)
        {
            itemOne.push(itemone);
        }
        Slot slot1 = new Slot(itemOne);
        regularItems.add(slot1);

        Milktea okinawawhitepearls = new Milktea("Okinawa with White Pearls", okinawa, whitepearls, 135);
        Item itemtwo = new Item(okinawawhitepearls, 120);
        Stack<Item> itemTwo = new Stack<Item>();
        for(int i=0;i<10;i++)
        {
            itemTwo.push(itemtwo);
        }
        Slot slot2 = new Slot(itemTwo);
        regularItems.add(slot2);

        Milktea okinawanata = new Milktea("Okinawa with Nata", okinawa, nata, 135);
        Item itemthree = new Item(okinawanata, 130);
        Stack<Item> itemThree = new Stack<Item>();
        for(int i=0;i<10;i++)
        {
            itemThree.push(itemthree);
        }
        Slot slot3 = new Slot(itemThree);
        regularItems.add(slot3);

        Milktea wintermelonpearls = new Milktea("Wintermelon with Pearls", wintermelon, pearls, 135);
        Item itemfour = new Item(wintermelonpearls, 118);
        Stack<Item> itemFour = new Stack<Item>();
        for(int i=0;i<10;i++)
        {
            itemFour.push(itemfour);
        }
        Slot slot4 = new Slot(itemFour);
        regularItems.add(slot4);

        Milktea wintermelonwhitepearls = new Milktea("Wintermelon with White Pearls", wintermelon, whitepearls, 135);
        Item itemfive = new Item(wintermelonwhitepearls, 120);
        Stack<Item> itemFive = new Stack<Item>();
        for(int i=0;i<10;i++)
        {
            itemFive.push(itemfive);
        }
        Slot slot5 = new Slot(itemFive);
        regularItems.add(slot5);

        Milktea wintermelonnata = new Milktea("Wintermelon with Nata", wintermelon, nata, 135);
         Item itemsix = new Item(wintermelonnata, 130);
        Stack<Item> itemSix = new Stack<Item>();
        for(int i=0;i<10;i++)
        {
            itemSix.push(itemsix);
        }
        Slot slot6 = new Slot(itemSix);
        regularItems.add(slot6);

        Milktea hokkaidopearls = new Milktea("Hokkaido with Pearls", hokkaido, pearls, 135);
         Item itemseven = new Item(hokkaidopearls, 120);
        Stack<Item> itemSeven = new Stack<Item>();
        for(int i=0;i<10;i++)
        {
            itemSeven.push(itemseven);
        }
        Slot slot7 = new Slot(itemSeven);
        regularItems.add(slot7);

        Milktea hokkaidowhitepearls = new Milktea("Hokkaido with White Pearls", hokkaido, whitepearls, 135);
        Item itemeight = new Item(hokkaidowhitepearls, 118);
        Stack<Item> itemEight = new Stack<Item>();
        for(int i=0;i<10;i++)
        {
            itemEight.push(itemeight);
        }
        Slot slot8 = new Slot(itemEight);
        regularItems.add(slot8);

        Milktea hokkaidonata = new Milktea("Hokkaido with Nata", hokkaido, nata, 135);
        Item itemnine = new Item(hokkaidonata, 130);
        Stack<Item> itemNinesStack = new Stack<Item>();
        for(int i=0;i<10;i++)
        {
            itemNinesStack.push(itemnine);
        }
        Slot slot9 = new Slot(itemNinesStack);
        regularItems.add(slot9);

        return regularItems;
    }
}
