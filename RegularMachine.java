import java.util.ArrayList;

public class RegularMachine 
{
   private static ArrayList<Items> items;
   private double machineCash; 
   private ArrayList<Transactions> transactions;

   public RegularMachine(ArrayList<Items> items, double machineCash)
   {
        this.items = items;
        this.machineCash = machineCash;
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
            this.machineCash += balance;
            check = true;
        }
        return check;
   }

   public void collectMachineBalance()
   {
          System.out.println("Collecting money...");
          System.out.println("Collecting a total of " + this.machineCash);
          this.machineCash = 0;
   }

   public int countItems()
   {
          return items.size();
   }

   /* 
   This shall include the printing format
   Format :
   ~~~~~~~~~~~~~~~~~~~~
   Date : 
   ItemName :
   Quantity :
   Payment : 
   ~~~~~~~~~~~~~~~~~~~~
   */ 
   /*public void printTransactionSummary()
   {
        for(int i=0;i < transactions.size();i++)
        {
          for(int j=0;j<19;j++)
          {
               System.out.print("~");
          }
               System.out.print("~\n");
               System.out.println("Date :\t"+transactions.get(i).getDate());
               System.out.println("ItemName :\t"+transactions.get(i).getItemName());
               System.out.println("Quantity :\t"+transactions.get(i).getQuantity());
               System.out.println("Payment :\t"+transactions.get(i).getPayment());
          for(int j=0;j<19;j++)
          {
               System.out.print("~");
            }
          System.out.print("~\n");
        }
   }*/

   public boolean checkQuantity(int itemIndex)
   {
        boolean result = false;
        
        if (items.get(itemIndex).getItemQuantity() > 0)
        {
               result = true;
        }

        return result;
   }

   //I am not sure if this is right idk what to put on what index doon sa list lalagay ko
   public boolean verifyPayment(int itemIndex, double payment)
   {
        boolean result = false;
     
        if(payment >= items.get(itemIndex).getItemPrice())
            result=true;    

        return result;
   }

   public void produceChange(boolean validPayment, double payment, int itemIndex)
   {
        double changeAmount = payment - items.get(itemIndex).getItemPrice();
        
        if (validPayment)
        {
            this.machineCash += payment;
            this.machineCash -= changeAmount;
            System.out.println("Your change is: " + changeAmount);
            System.out.println("Dispensing change...");
            System.out.println("Please get your change. Thank you!");
        }
   }

   // Working in progress palang 
   public void dispenseItem(boolean validTransact, int itemIndex)
   {
        if(validTransact)
        {
            System.out.println("Dispensing Item...");
            // I suggest creating a method for deducting of quantity para mas easier to ready
            items.get(itemIndex).setItemQuantity(items.get(itemIndex).getItemQuantity() - 1);
            System.out.println("Thank you for buying!");
        }
   }

   //This function displays the vending machines inventory & its information
   public static void displayMachine()
   {
          System.out.println("------------------------------------------------------------");
          /* Format:
           * [1] Cup Noodles (10 available)
           * Php 50 | 150 calories
           */
          for(int i = 0; i < items.size(); i++)
          {
               int j = i + 1; // Only to make the numbering start at 1. Must only be used on the [j] part
               System.out.println("[" + j + "] " + items.get(i).getItemName() + " (" + items.get(i).getItemQuantity() 
                              + " available)");
               System.out.println("Php " + items.get(i).getItemPrice() + " | " + items.get(i).getItemCalories() + " calories");
               
               if (i != items.size() - 1)
               {
                    System.out.println();
               }
          }
          System.out.println("------------------------------------------------------------");
   }

   public void saveTransaction(int itemIndex, double payment)
   {
          String itemName = items.get(itemIndex).getItemName();
          double totalPrice = items.get(itemIndex).getItemPrice();
          Transactions transact = new Transactions(itemName, totalPrice, payment);
          transactions.add(transact);
   }

   public ArrayList<Items> getItem()
   {
          return items;
   }

   public double getMachineCash()
   {
          return machineCash;
   }
}
