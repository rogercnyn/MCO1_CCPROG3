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

    public RegularMachine(ArrayList<Slot> arraySlots)
    {
        this.chosenItem = null;
        this.cashHandler = new CashHandler();
        this.arraySlots = arraySlots;
        this.transactions = new ArrayList<Transactions>();
        this.lastRestockDate = LocalDate.now();
        this.inventory = arraySlots;
    }

    public void updateInventory()
    {
        this.inventory = arraySlots;
    }

    public ArrayList<Slot> getInventory() 
    {
        return inventory;
    }

    public void updateLastRestockDate()
    {
        this.lastRestockDate = LocalDate.now();
    }

    public Item getChosenItem() 
    {
        return chosenItem;
    }

    public ArrayList<Slot> getArraySlots()
    {
        return arraySlots;
    }

    public CashHandler getCashHandler() 
    {
        return cashHandler;
    }

    public ArrayList<Transactions> geTransactions()
    {
        return transactions;
    }

    public LocalDate getLastRestockDate()
    {
        return lastRestockDate;
    }

    public void setChosenItem(Item chosenItem) 
    {
        this.chosenItem = chosenItem;
    }

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

    public ArrayList<Transactions> getTransactions() 
    {
        return transactions;
    }

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
