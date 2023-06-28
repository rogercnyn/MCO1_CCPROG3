import java.util.ArrayList;
import java.util.Stack;
import java.util.Scanner;

public class RegularMachine 
{
   private static ArrayList<Items> items; 
   private ArrayList<Transactions> transactions;
   private int[] acceptedDenom = {1, 5, 10, 20, 50, 100, 200, 500, 1000};
   private Stack<Integer> payment = new Stack<Integer>();
   private Stack<Integer> change = new Stack<Integer>();
   private int[][] machineBalance;

   public RegularMachine(ArrayList<Items> items, int[][] machineBalance)
   {
        this.items = items;
        this.machineBalance = machineBalance;
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

   public boolean setPrice(String itemName, int price)
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

   public void replenishMachineBal(int[][] balance)
   {
          int i, j;
          for (i = 0; i < this.machineBalance.length; i++)
          {
               for (j = 0; j < this.machineBalance[i].length - 1; j++)
               {
                    this.machineBalance[i][j+1] += balance[i][j+1];
               }
          }
   }

   public void collectMachineBalance()
   {
          int totalBalance = 0;
          System.out.println("Calculating total balance...");
          int i, j;
          for (i = 0; i < this.machineBalance.length; i++)
          {
               for (j = 0; j < this.machineBalance[i].length - 1; j++)
               {
                    totalBalance += this.machineBalance[i][j+1] * this.machineBalance[i][j];
               }
          }
          System.out.println("Collecting a total of " + totalBalance);
          System.out.println("In denomination of:");
          for (i = 0; i < this.machineBalance.length; i++)
          {
               for (j = 0; j < this.machineBalance[i].length - 1; j++)
               {
                    System.out.println("Php " + this.machineBalance[i][j] + "s: " + this.machineBalance[i][j+1]);
               }
          }
          System.out.println("Machine balance successfully collected. Thank you!");
          for (i = 0; i < this.machineBalance.length; i++)
          {
               for (j = 0; j < this.machineBalance[i].length - 1; j++)
               {
                    this.machineBalance[i][j+1] = 0;
               }
          }
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

   private boolean isDenomAccepted(int input)
   {
          boolean check = false;
          for (int i = 0; i < this.acceptedDenom.length; i++)
          {
               if (input == this.acceptedDenom[i])
               {
                    return true;
               }
          }
          return check;
   }

   public int processPayment(int itemIndex)
   {
          Scanner scan = new Scanner(System.in);
          int i;
          int input = 0;
          int totalPrice = items.get(itemIndex).getItemPrice();
          int totalPayable = items.get(itemIndex).getItemPrice();
          boolean isDenomAccepted;
          int stackReturn;
          System.out.println("[PAYMENT]");
          System.out.println("Insert any of the available denomination: [1, 5, 10, 20, 50, 100, 200, 500, 1000]");
          System.out.println("If you want to cancel the transaction, enter 0.");
          int totalInserted = 0;
          boolean check = false;
          do 
          {
               isDenomAccepted = false;
               System.out.println("Total amount payable: Php " + totalPayable);
               System.out.print("Insert coin / bill: ");
               input = scan.nextInt();
               if (input == 0)
               {
                    System.out.println("Transaction cancelled.");
                    System.out.println("Dispensing your payment");
                    while(!payment.empty())
                    {
                         System.out.println("Php " + payment.pop() + " ");
                    }
                    totalInserted = 0;
               }

               if(isDenomAccepted(input))
               {
                    totalInserted += input;
                    this.payment.push(input);
                    totalPayable -= input;
               }

               else if (!isDenomAccepted(input))
               {
                    System.out.println("Denomination inserted is not accepted.");
               }

          } while (totalPayable > 0 && totalInserted < totalPrice && input != 0);

          while(!payment.empty())
          {
               stackReturn = payment.pop();
               addQuantityToDenom(stackReturn);
          }

          if (totalPayable == 0) // This condition is for only when the payment inserted is exactly the same as the total payment
          {
               System.out.println("Thank you for paying exact amount.");
          }

          if (totalInserted >= totalPrice) // Will only run if there is a change
          {
               check = produceChange(totalInserted, totalPrice);
          }

          return totalInserted;
   }

   private void addQuantityToDenom(int denom)
   {
          int i, j;
          for (i = 0; i < this.machineBalance.length; i++)
          {
               for (j = 0; j < this.machineBalance[i].length - 1; j++)
               {
                    if (denom == this.machineBalance[i][j])
                    {
                         this.machineBalance[i][j+1] += 1;
                    }
               }
          }
   }

   private void deductQuantityToDenom(int denom)
   {
          int i, j;
          for (i = 0; i < this.machineBalance.length; i++)
          {
               for (j = 0; j < this.machineBalance[i].length - 1; j++)
               {
                    if (denom == this.machineBalance[i][j])
                    {
                         this.machineBalance[i][j+1] -= 1;
                    }
               }
          }
   }

   private boolean hasDenomStock(int denom)
   {
          boolean check = false;
          int i, j;
          for (i = 0; i < this.machineBalance.length; i++)
          {
               for (j = 0; j < this.machineBalance[i].length - 1; j++)
               {
                    if (denom == this.machineBalance[i][j])
                    {
                         if (this.machineBalance[i][j+1] > 0)
                              return true;
                    }
               }
          }
          return check;
   }

   public boolean hasEnoughDenom()
   {
          boolean check = true;
          int i, j;
          for (i = 0; i < this.machineBalance.length; i++)
          {
               if (this.machineBalance[i][1] <= 0)
               {
                    check = false;
               }
          }

          for (i = 0; i < this.machineBalance.length; i++)
          {
               for (j = 0; j < this.machineBalance[i].length - 1; j++)
               {
                    System.out.println(this.machineBalance[i][j] + " " + this.machineBalance[i][j+1]);
               }
          }
          return check;
   }

   public boolean produceChange(int totalInserted, int totalPayable)
   {
          boolean check = false;
          int i;
          int changeAmount = totalInserted - totalPayable;
          int dispensedChange = 0;
          boolean isEqualToDenom = false;

          do
          {
               isEqualToDenom = isDenomAccepted(changeAmount);

               if(isEqualToDenom && hasDenomStock(changeAmount))
               {
                    change.push(changeAmount);
                    deductQuantityToDenom(changeAmount);
                    changeAmount -= changeAmount;
               }

               if (!isEqualToDenom)
               {
                    for (i = this.acceptedDenom.length - 1; i >= 0; --i)
                    {
                         if (changeAmount > 0)
                         {
                              if (changeAmount > this.acceptedDenom[i] && hasDenomStock(this.acceptedDenom[i]))
                              {
                                   deductQuantityToDenom(this.acceptedDenom[i]);
                                   change.push(acceptedDenom[i]);
                                   changeAmount -= acceptedDenom[i];
                              }

                              else if (changeAmount % this.acceptedDenom[i] == 0 && hasDenomStock(this.acceptedDenom[i]))
                              {
                                   deductQuantityToDenom(this.acceptedDenom[i]);
                                   change.push(acceptedDenom[i]);
                                   changeAmount -= acceptedDenom[i];
                              }
                         }
                    }
               }

          }while(changeAmount > 0);

          System.out.println("Dispensing change: ");

          while (!change.isEmpty())
          {
               int poppedValue = change.pop();
               System.out.println("Php " + poppedValue);
               dispensedChange += poppedValue;
          }

          System.out.println("Total dispensed change: Php " + dispensedChange);

          if (changeAmount == 0)
          {
               check = true;
          }

          return check;
   }

   public void dispenseItem(int itemIndex)
   {
          System.out.println("Dispensing Item...");
          items.get(itemIndex).setItemQuantity(items.get(itemIndex).getItemQuantity() - 1);
          String name = items.get(itemIndex).getItemName();
          System.out.println("1 " + name + " dispensed.");
          System.out.println("Thank you for buying!");
   }

   //This function displays the vending machines inventory & its information
   public void displayMachine()
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

   public void saveTransaction(int itemIndex, int payment)
   {
          String itemName = items.get(itemIndex).getItemName();
          int totalPrice = items.get(itemIndex).getItemPrice();
          Transactions transact = new Transactions(itemName, totalPrice, payment);
          transactions.add(transact);
   }

   public ArrayList<Items> getItem()
   {
          return items;
   }
}
