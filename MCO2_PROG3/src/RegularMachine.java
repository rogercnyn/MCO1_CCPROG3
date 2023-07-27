import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Stack;

public class RegularMachine {
    private ArrayList<Slot> arraySlots;
    private ArrayList<Transactions> transactions;
    private int[] acceptedDenom = {1, 5, 10, 20, 50, 100, 200, 500, 1000};
    private Stack<Integer> payment;
    private Stack<Integer> change;
    private int[][] machineBalance;
    private LocalDate lastRestockDate;

    public RegularMachine(ArrayList<Slot> arraySlots)
    {
        this.arraySlots = arraySlots;
        this.transactions = new ArrayList<Transactions>();
        this.payment = new Stack<Integer>();
        this.change = new Stack<Integer>();
        this.lastRestockDate = LocalDate.now();
        this.machineBalance = new int[][] {{1, 10}, {5, 10}, {10, 10}, {20, 10},
                                {50, 10}, {100, 10}, {500, 10}, {1000, 10}};
    }


}
