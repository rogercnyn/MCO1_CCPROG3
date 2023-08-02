import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Stack;

public class Slot {
    private Stack<Item> items;
    private int CAPACITY;

    public Slot()
    {
        this.items = new Stack<Item>();
        this.CAPACITY = 0;
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

    public ArrayList<Slot> specialItems()
    {
        ArrayList<Slot> specialItems = new ArrayList<Slot>();

        Milktea milktea = new Milktea("Milktea", 135);
        Stack<Item> itemOne = addItem(milktea);
        Slot slot1 = new Slot(itemOne);
        specialItems.add(slot1);

        Flavor okinawa = new Flavor("Okinawa", 30);
        Stack<Item> itemTwo = addItem(okinawa);
        Slot slot2 = new Slot(itemTwo);
        specialItems.add(slot2);

        Flavor wintermelon = new Flavor("Wintermelon", 30);
        Stack<Item> itemThree = addItem(wintermelon);
        Slot slot3 = new Slot(itemThree);
        specialItems.add(slot3);

        Sinker pearls = new Sinker("Pearls", 88, 20);
        Stack<Item> itemFour = addItem(pearls);
        Slot slot4 = new Slot(itemFour);
        specialItems.add(slot4);

        Sinker whitepearls = new Sinker("White Pearls", 90, 20);
        Stack<Item> itemFive = addItem(whitepearls);
        Slot slot5 = new Slot(itemFive);
        specialItems.add(slot5);

        Sinker nata = new Sinker("Nata de Coco", 100, 25);
        Stack<Item> itemSix = addItem(nata);
        Slot slot6 = new Slot(itemSix);
        specialItems.add(slot6);

        Sinker grassjelly = new Sinker("Grass Jelly", 93, 25);
        Stack<Item> itemSeven = addItem(grassjelly);
        Slot slot7 = new Slot(itemSeven);
        specialItems.add(slot7);

        Sinker eggpudding = new Sinker("Egg Pudding", 105, 30);
        Stack<Item> itemEight = addItem(eggpudding);
        Slot slot8 = new Slot(itemEight);
        specialItems.add(slot8);

        Sinker coffeejelly = new Sinker("Coffee Jelly", 118, 30);
        Stack<Item> itemNine = addItem(coffeejelly);
        Slot slot9 = new Slot(itemNine);
        specialItems.add(slot9);

        return specialItems;

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
