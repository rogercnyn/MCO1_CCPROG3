/**
 * Regular Vending Machine is the class that handles the ordinary vending machine.
 */

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Stack;

public class RegularMachine {
    private Item chosenItem;
    private ArrayList<Slot> arraySlots;
    private ArrayList<Transactions> transactions;
    private CashHandler cashHandler;
    private LocalDate lastRestockDate;
    private ArrayList<Slot> inventory;

    /**
    * This is the contructor method that accepts the arraylist of items.
    * @param arraySlots contains the item
    */
    public RegularMachine(ArrayList<Slot> arraySlots)
    {
        this.chosenItem = null;
        this.cashHandler = new CashHandler();
        this.arraySlots = arraySlots;
        this.transactions = new ArrayList<Transactions>();
        this.lastRestockDate = LocalDate.now();
        this.inventory = new ArrayList<Slot>();
    }

    /**
     * Sets the inventory
     * @param arraySlots contains the current state of arrayslots
     */
    public void setInventory(ArrayList<Slot> arraySlots)
    {
        this.inventory = arraySlots;
    }

    /**
     * Returns inventory
     * @return inventory
     */
    public ArrayList<Slot> getInventory() 
    {
        return inventory;
    }

    /**
     * Updates the restock date
     */
    public void updateLastRestockDate()
    {
        this.lastRestockDate = LocalDate.now();
    }

    /**
     * Returns the chosen item 
     * @return chosen item
     */
    public Item getChosenItem() 
    {
        return chosenItem;
    }

    /**
     * Returns the arrayslots
     * @return the arrayslots
     */
    public ArrayList<Slot> getArraySlots()
    {
        return arraySlots;
    }

    /**
     * Return the cash handler
     * @return cash handler
     */
    public CashHandler getCashHandler() 
    {
        return cashHandler;
    }

    /**
     * Get the transactions
     * @return transactions
     */
    public ArrayList<Transactions> geTransactions()
    {
        return transactions;
    }

    /**
     * Get the last restock date
     * @return last restock date
     */
    public LocalDate getLastRestockDate()
    {
        return lastRestockDate;
    }

    /**
     * Sets the chosen item
     * @param chosenItem - chosen item
     */
    public void setChosenItem(Item chosenItem) 
    {
        this.chosenItem = chosenItem;
    }

    /**
     * Gets the index of the chosen item in the ArrayList
     * @return the index if found, -1 if not
     */
    public int getChosenItemIndex()
    {
        int i;
        for (i = 0; i < this.arraySlots.size(); i++)
        {
            if (this.chosenItem.equals(this.arraySlots.get(i).checkItem()))
            {
                return i;
            }
        }
        return -1;
    }

    /**
     * Locate the item in the array
     * @param item - item to find
     * @return the index, -1 if not found
     */
    public int locateItemIndex(Item item)
    {
        int i;
        for (i = 0; i < this.arraySlots.size(); i++)
        {
            if (item.equals(this.arraySlots.get(i).checkItem()))
            {
                return i;
            }
        }
        return -1;
    }

    /**
     * Gets the transaction
     * @return transactions
     */
    public ArrayList<Transactions> getTransactions() 
    {
        return transactions;
    }

    /**
     * Add the transaction
     */
    public void addTransactions()
    {
        Transactions transaction = new Transactions();
        if (chosenItem instanceof Milktea)
        {
            transaction = new Transactions(getChosenItem().getItemName(), ((Milktea)getChosenItem()).getPrice());
        }
        else if (chosenItem instanceof Sinker)
        {
            transaction = new Transactions(getChosenItem().getItemName(), ((Sinker)getChosenItem()).getPrice());
        }

        transactions.add(transaction);
    }
}
