import java.sql.Date;
import java.time.LocalDate;
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
   private Date lastrestockDate;

   public RegularMachine(ArrayList<Items> items, int[][] machineBalance)
   {
     this.items = items;
     this.machineBalance = machineBalance;
     this.transactions = new ArrayList<Transactions>();
     this.lastrestockDate = null;
   }

   public void restockItem(int itemIndex, int quantity,ArrayList<RegularMachine> regular)
   {
     items.get(itemIndex).setItemQuantity(quantity);
     regular.get(itemIndex).setDate();
   }

   public void setPrice(int itemIndex, int price)
   {
     items.get(itemIndex).setItemPrice(price);
   }

   public void replenishMachineBal(int[][] balance)
   {
     int i, j;
     for (i = 0; i < this.machineBalance.length; i++)
     {
          for (j = 0; j < this.machineBalance[i].length - 1; j++)
          {
               this.machineBalance[i][j+1] = this.machineBalance[i][j+1] + balance[i][j+1];
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

   public void printSalesSummary(ArrayList<RegularMachine> regular)
   {
     int totalsales =0;
     int numberSold =0;
     Date thisDate = setDate();
     for(int i=0 ; i<(transactions.size()-1);i++)
     {
        totalsales += transactions.get(i).getTotalPrice();  
     }
     System.out.println("Sales Report from : " +regular.get(regular.size()-1).lastrestockDate+ " to " + thisDate );
     System.out.println("Total Sales Generated : Php "+totalsales);
     for(int j=0;j<(items.size()-1);j++)
     {
          for(int k=0;k<(transactions.size()-1);k++)
          {
               if(items.get(j).getItemName()==transactions.get(k).getItemName())
               {
                  numberSold+=transactions.get(k).getTotalPrice()/items.get(j).getItemPrice();  
               }
               System.out.println("["+numberSold+"] "+items.get(j).getItemName());
          }     
     }
   }

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
               if (!payment.empty())
               {
                    System.out.println("Dispensing your payment: ");
                    while(!payment.empty())
                    {
                         System.out.println("Php " + payment.pop() + " ");
                    }
               }
               totalInserted = 0;
          }

          else if(isDenomAccepted(input))
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

     if (totalPayable == 0) // This condition is for only when the payment inserted is exactly the same as the total payment
     {
          while(!payment.empty())
          {
               int poppedValue = payment.pop();
               addQuantityToDenom(poppedValue);
          }
          System.out.println("Thank you for paying exact amount.");
          check = true;
     }

     else if (totalInserted > totalPrice) // Will only run if there is a change
     {
          check = produceChange(totalInserted, totalPrice);
     }
     
     if (!check)
     {
          totalInserted = 0;
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

   public boolean produceChange(int totalInserted, int totalPayable)
   {
     boolean check = false;
     int i;
     int changeAmount = totalInserted - totalPayable;
     int dispensedChange = 0;
     boolean isEqualToDenom = false;

     isEqualToDenom = isDenomAccepted(changeAmount);

     if(isEqualToDenom && hasDenomStock(changeAmount))
     {
          change.push(changeAmount);
          deductQuantityToDenom(changeAmount);
          changeAmount -= changeAmount;
     }

     else if (!isEqualToDenom)
     {

          for (i = this.acceptedDenom.length - 1; i >= 0 && changeAmount > 0; --i)
          {
               if (changeAmount > this.acceptedDenom[i] && hasDenomStock(this.acceptedDenom[i]))
               {
                    deductQuantityToDenom(this.acceptedDenom[i]);
                    change.push(acceptedDenom[i]);
                    changeAmount -= acceptedDenom[i];
               }

               while(changeAmount % this.acceptedDenom[i] == 0 && hasDenomStock(this.acceptedDenom[i]) && changeAmount > 0)
               {
                    deductQuantityToDenom(this.acceptedDenom[i]);
                    change.push(acceptedDenom[i]);
                    changeAmount -= acceptedDenom[i];
               }
          }
     }

     if (isChangeComplete(totalInserted - totalPayable))
     {
          if (changeAmount == 0)
          {
               check = true;
          }

          while(!payment.empty())
          {
               int poppedValue = payment.pop();
               addQuantityToDenom(poppedValue);
          }

          System.out.println("Dispensing change: ");

          while (!change.isEmpty())
          {
               int poppedValue = change.pop();
               System.out.println("Php " + poppedValue);
               dispensedChange += poppedValue;
          }

          System.out.println("Total dispensed change: Php " + dispensedChange);
     }

     else
     {
          System.out.println("Sorry this machine does not have enough balance to produce change.");
          System.out.println("Dispensing your payment: ");
          while(!payment.empty())
          {
               int poppedValue = payment.pop();
               System.out.println("Php " + poppedValue);
               deductQuantityToDenom(poppedValue);
          }
          System.out.println("Sorry for this inconvience.");

          check = false;
     }

     return check;
   }

   private boolean isChangeComplete(int changeAmount)
   {
     boolean check = false;
     int counter = 0;

     for (int i : change) {
          counter = counter + i;
     }

     if (counter == changeAmount)
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

   public Date setDate()
   {
     return this.lastrestockDate = setDate();
   }

}
