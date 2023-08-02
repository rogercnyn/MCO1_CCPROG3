/**
 * Slot is responsible for handling the stock of each item.
 */

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Stack;

public class Slot {
    private Stack<Item> items;
    private int CAPACITY;

    /**
     * This is the constructor that does not accept any parameter.
     * It creates a slot object within an empty stack.
     */
    public Slot()
    {
        this.items = new Stack<Item>();
        this.CAPACITY = 11;
    }

    /**
     * This is the constructor that accepts a stack of items as parameter.
     * @param items - contains a stack of item
     */

    public Slot(Stack<Item> items)
    {
        this.items = items;
        this.CAPACITY = 11;
    }

    /**
     * Dispense the top of the stack.
     * @return the top of slot
     */
    public Item dispenseItem()
    {
        return items.pop();
    }

    /**
     * Peeks at the top of the stack
     * @return the top of slot without removing it
     */
    public Item checkItem()
    {
        return items.peek();
    }

    /**
     * Gets the number of items available at a particular stack
     * @return number of items
     */
    public int getNumberOfStock()
    {
        return items.size() - 1;
    }

    /**
     * Put the item inside the stack
     * @param item - item that the stack will store
     * @return a stack of items
     */
    public Stack<Item> addItem(Item item)
    {
        Stack<Item> stackOfItems = new Stack<Item>();
        for (int i = 0; i < CAPACITY; i++)
        {
            stackOfItems.push(item);
        }

        return stackOfItems;
    }

    /**
     * Add the item to the stack
     * @param item - item to add
     * @param totalNumberOfRestock - number of items to add
     */
    public void addItem(Item item, int totalNumberOfRestock)
    {
        for (int i = 0; i < totalNumberOfRestock; i++)
        {
            items.push(item);
        }
    }

    /**
     * Generates the item for special vending machine
     * @return Arraylist of Slot that holds the items for the machine
     */
    public ArrayList<Slot> specialItems()
    {
        ArrayList<Slot> specialItems = new ArrayList<Slot>();

        Milktea milktea = new Milktea("Milktea", 135);
        Stack<Item> itemOne = addItem(milktea);
        Slot slot1 = new Slot(itemOne);
        specialItems.add(slot1);

        Flavor okinawa = new Flavor("Okinawa", 462);
        Stack<Item> itemTwo = addItem(okinawa);
        Slot slot2 = new Slot(itemTwo);
        specialItems.add(slot2);

        Flavor wintermelon = new Flavor("Wintermelon", 462);
        Stack<Item> itemThree = addItem(wintermelon);
        Slot slot3 = new Slot(itemThree);
        specialItems.add(slot3);

        Sinker pearls = new Sinker("Pearls", 358, 20);
        Stack<Item> itemFour = addItem(pearls);
        Slot slot4 = new Slot(itemFour);
        specialItems.add(slot4);

        Sinker whitepearls = new Sinker("White Pearls", 358, 20);
        Stack<Item> itemFive = addItem(whitepearls);
        Slot slot5 = new Slot(itemFive);
        specialItems.add(slot5);

        Sinker nata = new Sinker("Nata de Coco", 109, 25);
        Stack<Item> itemSix = addItem(nata);
        Slot slot6 = new Slot(itemSix);
        specialItems.add(slot6);

        Sinker grassjelly = new Sinker("Grass Jelly", 94, 25);
        Stack<Item> itemSeven = addItem(grassjelly);
        Slot slot7 = new Slot(itemSeven);
        specialItems.add(slot7);

        Sinker eggpudding = new Sinker("Egg Pudding", 265, 30);
        Stack<Item> itemEight = addItem(eggpudding);
        Slot slot8 = new Slot(itemEight);
        specialItems.add(slot8);

        Sinker coffeejelly = new Sinker("Coffee Jelly", 115, 30);
        Stack<Item> itemNine = addItem(coffeejelly);
        Slot slot9 = new Slot(itemNine);
        specialItems.add(slot9);

        return specialItems;

    }

    /**
     * Generates the item for regular vending machine
     * @return Arraylist of Slot that holds the items for the machine
     */
    public ArrayList<Slot> regularItems()
    {
        ArrayList<Slot> regularItems = new ArrayList<Slot>();

        Flavor okinawa = new Flavor("Okinawa", 462);
        Flavor wintermelon = new Flavor("Wintermelon", 462);
        Flavor hokkaido = new Flavor("Hokkaido", 462);

        Sinker pearls = new Sinker("Pearls", 358);
        Sinker whitepearls = new Sinker("White Pearls", 358);
        Sinker nata = new Sinker("Nata de Coco", 109);

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
