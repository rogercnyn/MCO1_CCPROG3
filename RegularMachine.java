/**
 * Regular Vending Machine is the class that handles the ordinary vending machine.
 * The following are the attributes of the class:
 *   <li> items - an array that contains the items inside the machine
 *   <li> transactions - an array that contains the transactions made by the machine
 *   <li> acceptedDenom - an array that lists all of the denomination that the machine can accept.
 *   <li> payment - a stack that holds the payment of the user
 *   <li> change - a stack that holds the change of the user
 *   <li> machineBalance - contains the balance of the machine (in denomination)
 *   <li> lastRestockDate - date that holds the date when the machine was last restocked
 */

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.text.DateFormatter;

import java.util.Scanner;


public class RegularMachine 
{
     //Kintanar newly added
     private ArrayList<Slot> arraySlots;

   private ArrayList<Transactions> transactions;
   private int[] acceptedDenom = {1, 5, 10, 20, 50, 100, 200, 500, 1000};
   private Stack<Integer> payment = new Stack<Integer>();
   private Stack<Integer> change = new Stack<Integer>();
   private int[][] machineBalance;
   private LocalDate lastRestockDate;

   /**
    * This is the contructor method that accepts the arraylist of items and machine balance and initialize it to the given values.
    * @param items
    * @param machineBalance
    */
   public RegularMachine(ArrayList<Slot> arraySlots, int[][] machineBalance)
   {
     this.arraySlots = arraySlots;
     this.machineBalance = machineBalance;
     this.transactions = new ArrayList<Transactions>();
     this.lastRestockDate = LocalDate.now();
   }

   /**
    * restockItem handles the restock of items.
    * @param itemIndex - contains the itemIndex of the product
    * @param quantity - contains number of stock to be added
    */
   public void restockItem(int slotIndex,int quantity)
   {
     // this method only add one item to the vending machine
     Items item = arraySlots.get(slotIndex).checkItem();
     for(int i=1 ; i<quantity;i++)
     {
     arraySlots.get(slotIndex).addItem(item);
     }
     updateLastRestockDate();
   }

   /**
    * updateLastRestockDate updates the lastRestockDate attribute to current date.
    */
   
   private void updateLastRestockDate()
   {
          this.lastRestockDate = LocalDate.now();
   }

   /**
    * setPrice updates the price of the selected item.
    * @param itemIndex - contains the itemIndex of the product
    * @param price - contains the new price
    */

   public void setPrice(int itemIndex, int price)
   {
     //no idea on how to do this
     arraySlots.get(itemIndex).checkItem();
}

   /**
    * replenishMachineBal updates the stock of the denomination.
    * @param balance - a 2D array that contains the new stock to be added (per denomination)
    */
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

   /**
    * collectMachineBalance collects the machine balance.
    */
   public void collectMachineBalance()
   {
     System.out.println();
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
     System.out.println();
     System.out.println("In denomination of:");
     for (i = 0; i < this.machineBalance.length; i++)
     {
          for (j = 0; j < this.machineBalance[i].length - 1; j++)
          {
               System.out.println("Php " + this.machineBalance[i][j] + "s: " + this.machineBalance[i][j+1]);
          }
     }
     System.out.println();
     System.out.println("Machine balance successfully collected. Thank you!");
     for (i = 0; i < this.machineBalance.length; i++)
     {
          for (j = 0; j < this.machineBalance[i].length - 1; j++)
          {
               this.machineBalance[i][j+1] = 0;
          }
     }
   }

   /**
    * countItems count the total number of items in the machine.
    * @return the number of items
    */
   public int countItems()
   {
     return arraySlots.size();
   }

   /**
    * printSalesSummary printes the sales summary from the date the machine was last restocked up to the day this method was called.
    */
   public void printSalesSummary()
   {
     int i, j;
     int soldCounter = 0;
     LocalDate today = LocalDate.now();
     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");

     System.out.println("Sales Report from: " + formatter.format(this.lastRestockDate) + " to " + formatter.format(today));

     int totalSales = 0;

     for (i = 0; i < transactions.size(); i++)
     {
          if(transactions.get(i).getDate().isAfter(today) || transactions.get(i).getDate().isBefore(today) || transactions.get(i).getDate().equals(today))
          {
               totalSales = totalSales + transactions.get(i).getTotalPrice();
          }
     }

     System.out.println("Total Sales Generated: Php " + totalSales+ "\n");

     System.out.println("Product Sales:");

     for (i = 0; i < arraySlots.size(); i++)
     {
          soldCounter = 0;
          for (j = 0; j < transactions.size(); j++)
          {
               if(transactions.get(j).getDate().isAfter(today) || transactions.get(j).getDate().isBefore(today) || transactions.get(j).getDate().equals(today))
               {
                    if (arraySlots.get(i).checkItem().getItemName().equals(transactions.get(j).getItemName()))
                    {
                         soldCounter++;
                    }
               }
          }
          System.out.println("[" + soldCounter + " sold] " + arraySlots.get(i).checkItem().getItemName());
     }
   }

   /**
    * checkQuantity checks if the quantity of an item is still more than 0.
    * @param itemIndex - contains the index of the product chosen.
    * @return true if the quantity is more than 0, false otherwise.
    */
   public boolean checkQuantity(int itemIndex)
   {
     boolean result = false;
     
     if (arraySlots.get(itemIndex).getCAPACITY() > 0)
     {
          result = true;
     }

     return result;
   }

   /**
    * isDenomAccepted checks if the denomination entered by the user is accepted
    * @param input - contains the input of the user
    * @return true if the denomination is accepted, false otherwise.
    */
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

   /**
    * processPayment asks user for their payment and calls produceChange once the payment is equals to the total payable or more than.
    * @param itemIndex - contains the item index of the selected product.
    * @return the value of total payment inserted, returns 0 if the user cancelled the transaction.
    */
   public int processPayment(int itemIndex)
   {
          Scanner scan = new Scanner(System.in);
          int i;
          int input = 0;
          int totalPrice = arraySlots.get(itemIndex).checkItem(); // no idea how to do this
          int totalPayable = items.get(itemIndex).getItemPrice();
          boolean isDenomAccepted;
          System.out.println();
          System.out.println("[PAYMENT]");
          System.out.println("Insert any of the available denomination: [1, 5, 10, 20, 50, 100, 200, 500, 1000]");
          System.out.println("If you want to cancel the transaction, enter 0.");
          int totalInserted = 0;
          boolean check = false;
          do 
          {
               isDenomAccepted = false;
               System.out.println();
               System.out.println("Total amount payable: Php " + totalPayable);
               System.out.print("Insert coin / bill: ");
               input = scan.nextInt();
               if (input == 0)
               {
                    System.out.println();
                    System.out.println("Transaction cancelled.");
                    if (!payment.empty())
                    {
                         System.out.println("Dispensing your payment: ");
                         while(!payment.empty())
                         {
                              System.out.println("Php " + payment.pop() + " ");
                         }
                    }
                    System.out.println();
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
     
     /**
      * addQuantityToDenom adds a value of 1 to the denomination passed.
      * @param denom - contains the denomination.
      */
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

   /**
     * addQuantityToDenom deducts a value of 1 to the denomination passed.
     * @param denom - contains the denomination.
     */
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

   /**
    * hasDenomStock checks if the given denomination has more a stock of more than 0.
    * @param denom - contains the denomination
    * @return true if the denomination's stock is more than 0, false otherwise.
    */
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

   /**
    * produceChange calculates the change of the user.
    * @param totalInserted - contains the total amount of payment that the user inserted.
    * @param totalPayable - contains the total payable of the user.
    * @return true if producing of change is successful, false otherwise.
    */
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

          System.out.println();

          System.out.println("Dispensing change: ");

          while (!change.isEmpty())
          {
               int poppedValue = change.pop();
               System.out.println("Php " + poppedValue);
               dispensedChange += poppedValue;
          }

          System.out.println();

          System.out.println("Total dispensed change: Php " + dispensedChange);
     }

     else if (!isChangeComplete(totalInserted - totalPayable))
     {
          System.out.println();
          System.out.println("Sorry this machine does not have enough balance to produce change.");
          System.out.println();
          System.out.println("Dispensing your payment: ");
          while(!payment.empty())
          {
               int poppedValue = payment.pop();
               System.out.println("Php " + poppedValue);
          }
          while (!change.isEmpty())
          {
               int poppedValue = change.pop();
               addQuantityToDenom(poppedValue);
          }
          System.out.println();
          System.out.println("Sorry for this inconvience.");

          check = false;
     }

     return check;
   }

   /**
    * isChangeComplete computes the total of the elements inside the change stack and checks if it is equals to expected change amount
    * @param changeAmount - contains the expected change amount.
    * @return true if the elements of the change stack is equals to the expected change amount, false otherwise.
    */
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

   /**
    * dispenseItem deducts the quantity of the selected item
    * @param itemIndex - contains the index of the selected product.
    */
   public void dispenseItem(int itemIndex)
   {
     System.out.println("Dispensing Item...");
     arraySlots.get(itemIndex).dispenseItem();
     String name = arraySlots.get(itemIndex).checkItem().getItemName();
     System.out.println("1 " + name + " dispensed.");
     System.out.println("Thank you for buying!");
   }

   
   /**
    * displayMachine prints the details of the machine in the terminal.
    */
   public void displayMachine()
   {
     System.out.println("------------------------------------------------------------");
     /* Format:
          * [1] Cup Noodles (10 available)
          * Php 50 | 150 calories
          */
     for(int i = 0; i < arraySlots.size(); i++)
     {
          int j = i + 1; // Only to make the numbering start at 1. Must only be used on the [j] part
          System.out.println("[" + j + "] " + arraySlots.get(i).checkItem().getItemName() + " (" + arraySlots.get(i).getCAPACITY() 
                         + " available)");
          System.out.println("Php " + arraySlots.get(i).checkItem().getItemPrice() + " | " + arraySlots.get(i).checkItem().getItemCalories() + " calories");
          
          if (i != arraySlots.size() - 1)
          {
               System.out.println();
          }
     }
     System.out.println("------------------------------------------------------------");
   }

   /**
    * saveTransaction saves the transaction once it is successful.
    * @param itemIndex - contains the index of the itemindex.
    * @param payment - contains the total amount that the user paid.
    */
   public void saveTransaction(int slotIndex, int payment)
   {
     String itemName = arraySlots.get(slotIndex).getItems().get(slotIndex).getItemName();
     int totalPrice = arraySlots.get(slotIndex).getItems;
     Transactions transact = new Transactions(itemName, totalPrice, payment);
     transactions.add(transact);
   }
}