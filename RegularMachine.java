import java.util.ArrayList;

public class RegularMachine 
{
   private ArrayList<Items> items;
   private double MachineCash;     // is this the income or the change that is in the machine ???
   private ArrayList<Transactions> transactions;

   public RegularMachine(ArrayList<Items> items, double machineCash)
   {
        this.items = items;
        this.MachineCash = machineCash;
        this.transactions = new ArrayList<Transactions>();
   }

   public void addItem(String name, double price, double calories, int quantity)
   {
        Items item = new Items(name, price, calories, quantity);
        items.add(item);
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

   // This shall include the printing format
   public void printTransactionSummary()
   {
        for(int i=0;i < transactions.size();i++)
        {
            System.out.println(transactions.get(i));
        }
   }

   // Hindi pa tapos ung setTransaction :) 
   public boolean setTransaction(int itemIndex, int quantity)
   {
        boolean result = false;
        double TotalPrice;
        if (checkQuantity(itemIndex, quantity))
        {
          result = true;
          dispenseItem(result);
          deductItem(itemIndex, quantity);
        }

        return result;
   }

   //I am not sure if this is right idk what to put on what index doon sa list lalagay ko
   public boolean askPayment (double payment, double total)
   {
        boolean result = false;

        if(payment >= total)
            result=true;

            
        return result;
   }

   public boolean produceChange(boolean validPayment, double payment, double total)
   {
        boolean result = false;
        double changeAmount = payment - total;
        
        if (validPayment)
        {
            this.MachineCash += payment;
            this.MachineCash -= changeAmount;
            System.out.println("Your change is: " + changeAmount);
            System.out.println("Dispensing change...");
            System.out.println("Please get your change. Thank you!");
            result = true;
        }

        return result;
   }

   // Working in progress palang 
   public boolean dispenseItem(boolean validTransact)
   {
        boolean result = false;
        if(validTransact)
        {
            System.out.println("Dispensing Item...");
            // I suggest creating a method for deducting of quantity para mas easier to ready
            items.get(itemIndex).setItemQuantity(items.get(itemIndex).getItemQuantity()-quantity);

            System.out.println("Thank you for buying!");
            result = true;
        }

        return result;
   }
   //This method simply deducts the Bought item at the vending machine - Kintanar
   public boolean deductItem(int itemIndex, int quantity)
   {
     boolean result = false;
     if (items.get(itemIndex).getItemQuantity()>=quantity)
     {
          items.get(itemIndex).setItemQuantity(items.get(itemIndex).getItemQuantity()-quantity);
          result = true;
     }
     return result;
   }
   // This method checks if the quantity entered is valid - Kintanar
   public boolean checkQuantity(int itemIndex, int quantity)
   {
     boolean result = false;
     if (items.get(itemIndex).getItemQuantity()>=quantity)
     {
          result = true;
     }
     return result;
   } 

   public void saveTransaction(String itemName, int quantity, double totalPrice, double payment, String date)
   {
        Transactions transact = new Transactions(itemName, quantity, totalPrice, payment, date);
        transactions.add(transact);
   }
}
