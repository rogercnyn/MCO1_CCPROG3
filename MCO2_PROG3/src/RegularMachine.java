import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Stack;

public class RegularMachine {
    private ArrayList<Slot> arraySlots;
    private ArrayList<Transactions> transactions;
    private CashHandler cashHandler;
    private int[][] machineBalance;
    private LocalDate lastRestockDate;

    public RegularMachine(ArrayList<Slot> arraySlots)
    {
        this.cashHandler = new CashHandler();
        this.arraySlots = arraySlots;
        this.transactions = new ArrayList<Transactions>();
        this.lastRestockDate = LocalDate.now();
    }

    public ArrayList<Slot> getArraySlots()
    {
        return arraySlots;
    }

    public CashHandler getCashHandler() {
        return cashHandler;
    }


}
