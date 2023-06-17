import java.util.ArrayList;

public class RegularMachine 
{
   private ArrayList<Items> items;
   private double machineCash;
   private ArrayList<Transactions> transactions;

   public RegularMachine(ArrayList<Items> items, double machineCash)
   {
        this.items = items;
        this.machineCash = machineCash;
        this.transactions = new ArrayList<Transactions>();
   }

   public boolean restockItem(String itemName, int quantity)
   {
        boolean check = false;
        int i;
        for (i = 0; i < items.size(); i++)
        {
            if (items.get(i).getItemName() == itemName)
            {
                // This just replace the older amount to the amount this variable holds.
                // Let's clarify if we should add the new quantity to the old one or completely put a new amount.
                items.get(i).setItemQuantity(quantity);
                check = true;
            }
        }
        return check;
   }

   public boolean setPrice(String itemName, double price)
   {
        boolean check = false;
        int i;
        for (i = 0; i < items.size(); i++)
        {
            if (items.get(i).getItemName() == itemName)
            {
                items.get(i).setItemPrice(price);
                check = true;
            }
        }
        return check;
   }

   public boolean replenishMachineBal(double balance)
   {
        boolean check = false;
        if (balance > 0)
        {
            this.machineCash += balance;
            check = true;
        }
        return check;
   }

   public void printTransactionSummary()
   {
    for( int i=0;i < transactions.size();i++)
    {
        System.out.println(transactions.get(i));
    }
   }
   // Hindi pa tapos ung setTransaction :)
   public boolean setTransaction(int itemIndex, int quantity)
   {
    boolean result = false;
    double totalprice;

    if(itemIndex<= items.size() && quantity<=items.get(itemIndex).getItemQuantity())
    {
        transactions.add(itemIndex, );
    }
    return result;
   }
}
