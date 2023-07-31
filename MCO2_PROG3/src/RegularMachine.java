import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Stack;

public class RegularMachine {
    private Item chosenItem;
    private ArrayList<Slot> arraySlots;
    private ArrayList<Transactions> transactions;
    private CashHandler cashHandler;
    private int[][] machineBalance;
    private LocalDate lastRestockDate;

    public RegularMachine(ArrayList<Slot> arraySlots)
    {
        this.chosenItem = null;
        this.cashHandler = new CashHandler();
        this.arraySlots = arraySlots;
        this.transactions = new ArrayList<Transactions>();
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

    public void addTransactions()
    {
        Transactions transaction = new Transactions(getChosenItem().getItemName(), ((Milktea)getChosenItem()).getPrice());
        transactions.add(transaction);
    }
}
