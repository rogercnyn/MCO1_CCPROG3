/**
 * SpecialMachine class handles the responsibility of the special machine features.
 * This class extend RegularMachine.
 */

import java.util.ArrayList;

public class SpecialMachine extends RegularMachine {

    /**
     * The constructor that accepts arrayslots as parameter/
     * @param arraySlots - contains the items
     */
    public SpecialMachine(ArrayList<Slot> arraySlots)
    {
        super(arraySlots);
    }

    /**
     * Record the chosen flavor of the customizeable milk tea in transaction.
     * @param flavor - chosen flavor the user.
     */
    public void addTransactions(Flavor flavor)
    {
        Transactions transaction = new Transactions(flavor.getItemName());
        super.getTransactions().add(transaction);
    }

    /**
     * Record the chosen sinker of the customizeable milk tea in transaction.
     * @param sinker - chosen sinker the user.
     */
    public void addTransactions(Sinker sinker)
    {
        Transactions transaction = new Transactions(sinker.getItemName());
        super.getTransactions().add(transaction);
    }
}
