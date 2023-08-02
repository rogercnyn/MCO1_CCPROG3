import java.util.ArrayList;

public class SpecialMachine extends RegularMachine {
    public SpecialMachine(ArrayList<Slot> arraySlots)
    {
        super(arraySlots);
    }

    // to record the chosen flavor of the customizeable milktea as well.
    public void addTransactions(Flavor flavor)
    {
        Transactions transaction = new Transactions(flavor.getItemName());
        super.getTransactions().add(transaction);
    }

    // to record the chosen sinker of the customizeable milktea as well.
    public void addTransactions(Sinker sinker)
    {
        Transactions transaction = new Transactions(sinker.getItemName());
        super.getTransactions().add(transaction);
    }
}
