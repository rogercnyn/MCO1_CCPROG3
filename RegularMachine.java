import java.util.ArrayList;

public class RegularMachine 
{
   private ArrayList<Items> items;
   private double MachineCash;
   private ArrayList<Transactions> transactions;

   public RegularMachine(ArrayList<Items> items, double machineCash)
   {
        this.items = items;
        this.MachineCash = machineCash;
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
            this.MachineCash += balance;
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
    double TotalPrice;
    if(itemIndex<= items.size() && quantity<=items.get(itemIndex).getItemQuantity())
    {
        items.get(itemIndex).setItemQuantity(items.get(itemIndex).getItemQuantity()-quantity);
        TotalPrice= items.get(itemIndex).getItemPrice()*quantity;
        
    }
    return result;
   }
   //I am not sure if this is right idk what to put on what index doon sa list lalagay ko
   public boolean askPayment (double payment)
   {
    boolean result = false;
    if(transactions.get(0).getTotalPrice()==payment)
        result=true;
    return result;
   }

   public boolean produceChange()
   {
    return false;
   }
   // Working in progress palang 
   public boolean dispenseItem(boolean validTransact)
   {
    boolean result = false;
    if(transactions.get(0).)
    return result;
   }
}
