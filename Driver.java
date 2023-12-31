/**
 * The driver class serves as the main class.
 * @author Kristian Kintanar
 * @author Roger Canayon Jr.
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Driver 
{
    /**
     * mainMenu displays the main menu in the terminal
     * @param regular arraylist of regular machine
     */
    private void mainMenu(ArrayList<RegularMachine> regular)
    {
        int choice;
        Scanner sc = new Scanner(System.in);
        System.out.println("----------------------------------------");
        System.out.println("|               [OPTIONS]              |");
        System.out.println("|      [1] Create Vending Machine      |");
        System.out.println("|       [2] Test Vending Machine       |");
        System.out.println("|               [3] Exit               |");
        System.out.println("----------------------------------------");
        System.out.print("Enter choice: ");

        do {
            choice = sc.nextInt();
            if (choice < 1 || choice > 3)
            {
                mainMenu(regular);
            }
        } while (choice < 1 || choice > 3);

        switch (choice) {
            case 1:
                createMachine(regular);
                break;
            
            case 2:
                testMachine(regular);
                break;
            
            case 3:
                break;

            default:
                break;
        }
    }

    /**
     * createMachine asks the user on what type of vending machine they want to create.
     * The options are: regular, special.
     * @param regular arraylist of regular machine
     */
    private void createMachine(ArrayList<RegularMachine> regular)
    {
        int choice;
        Scanner sc = new Scanner(System.in);
        System.out.println("----------------------------------------");
        System.out.println("|        [CREATE VENDING MACHINE]      |");
        System.out.println("|      [1] Regular Vending Machine     |");
        System.out.println("|      [2] Special Vending Machine     |");
        System.out.println("|               [3] Back               |");
        System.out.println("----------------------------------------");
        System.out.print("Enter choice: ");

        do {
            choice = sc.nextInt();
            if (choice < 1 || choice > 3)
            {
                createMachine(regular);
            }
        } while (choice < 1 || choice > 3);

        switch (choice) {
            case 1:
                createRegularMachine(regular);
                break;

            case 2:
                System.out.println("This feature is not yet available.");
                mainMenu(regular);
                break;
            
            case 3:
                mainMenu(regular);
                break;
        }
    }

    /**
     * askItemName asks the user for input for the item name.
     * @return the item name
     */
    private String askItemName()
    {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter item name: ");
        String itemName = scan.nextLine();
        return itemName;
    }

    /**
     * askItemPrice asks the user for input for the item price.
     * @return the item price
     */
    private int askItemPrice()
    {
        Scanner scan = new Scanner(System.in);
        int itemPrice;
        do 
        {
            System.out.print("Enter item price: ");
            itemPrice = scan.nextInt();
            if (itemPrice <= 0)
            {
                System.out.println("The price must be a non-negative and non-zero value.");
            }
        } while (itemPrice <= 0);
        return itemPrice;
    }

    /**
     * askItemCalories asks the user for input for the item calories
     * @return the item calories
     */
    private int askItemCalories()
    {
        Scanner scan = new Scanner(System.in);
        int itemCalories;
        do 
        {
            System.out.print("Enter item calories: ");
            itemCalories = scan.nextInt();
            if (itemCalories <= 0)
            {
                System.out.println("The calories must be a non-negative and non-zero value.");
            }
        } while (itemCalories <= 0);
        return itemCalories;
    }

    /**
     * askItemQuantity asks the user for input for the item quantity
     * @return the item quantity
     */
    private int askItemQuantity()
    {
        Scanner scan = new Scanner(System.in);
        int itemQuantity;
        do 
        {
            System.out.print("Enter item quantity: ");
            itemQuantity = scan.nextInt();
            if (itemQuantity < 10)
            {
                System.out.println("Minimum quantity for a product is 10 items.");
            }
        } while (itemQuantity < 10);
        return itemQuantity;
    }

    /**
     * askMachineBal asks the user to input denomination stocks
     * @return 2d array containing the balance of each denomination
     */
    private int[][] askMachineBal()
    {
        int input;
        System.out.println();
        System.out.println("[FOR MACHINE BALANCE]");
        Scanner scan = new Scanner(System.in);
        int[][] balance = { {1, 0},
                            {5, 0},
                            {10, 0},
                            {20, 0},
                            {50, 0},
                            {100, 0},
                            {200, 0},
                            {500, 0},
                            {1000, 0}};
        int i, j;
        for (i = 0; i < balance.length; i++)
        {
            for (j = 0; j < balance[i].length - 1; j++)
            {
                do
                {
                    System.out.println();
                    System.out.print("Enter quantity of Php " + balance[i][j] + "s: ");
                    input = scan.nextInt();
                    if (input < 0)
                    {
                        System.out.println("Negative values are not accepted. The machine only accepts 0 quantity or more.");
                    }
                }while(input < 0);
                
                balance[i][j+1] = input;
            }
            
        }
        return balance;
    }

    /**
     * createRegularMachine creates regular vending machine.
     * @param regular arraylist of regular machine
     */
    private void createRegularMachine(ArrayList<RegularMachine> regular)
    {
        Scanner scan = new Scanner(System.in);
        ArrayList<Items> items = new ArrayList<Items>();
        String itemName;
        int itemPrice, itemCalories;
        int itemQuantity;
        int[][] balance;
        int counter = 0;
        int addMoreItem;
        for (counter = 1; counter < 9; counter++)
        {
            System.out.println();
            System.out.println("Product #" + counter);
            itemName = askItemName();
            itemPrice = askItemPrice();
            itemCalories = askItemCalories();
            itemQuantity = askItemQuantity();
            Items newitem = new Items(itemName, itemPrice, itemCalories, itemQuantity);
            items.add(newitem);
        }

        do 
        {
            System.out.println();
            System.out.println("Do you have more items to add?");
            System.out.print("[1] Yes or [2] No: ");
            addMoreItem = scan.nextInt();
            switch (addMoreItem) {
                case 1:
                    System.out.println();
                    scan.nextLine();
                    System.out.println("Product #" + counter);
                    itemName = askItemName();
                    itemPrice = askItemPrice();
                    itemCalories = askItemCalories();
                    itemQuantity = askItemQuantity();
                    Items newitem = new Items(itemName, itemPrice, itemCalories, itemQuantity);
                    items.add(newitem);
                    counter++;
                    break;
            
                default:
                    break;
            }
        } while (addMoreItem != 2);

        balance = askMachineBal();
        RegularMachine newReg = new RegularMachine(items, balance);
        regular.add(newReg);

        System.out.println();
        System.out.println("Congratulations! Your Regular Vending Machine is now ready!");
        mainMenu(regular);
    }

    /**
     * testMachine displays the options to test the features of the vending machine.
     * @param regular arraylist of regular machine
     */
    private void testMachine(ArrayList<RegularMachine> regular)
    {
        if (regular.size() == 0)
        {
            System.out.println("No available vending machine to test.");
            mainMenu(regular);
        }

        else
        {
            int choice;

            Scanner sc = new Scanner(System.in);
            System.out.println("----------------------------------------");
            System.out.println("|               [FEATURES]             |");
            System.out.println("|         [1] Vending Features         |");
            System.out.println("|       [2] Maintenance Features       |");
            System.out.println("|               [3] Back               |");
            System.out.println("----------------------------------------");
            System.out.print("Enter choice: ");

            do {
                choice = sc.nextInt();
                if (choice < 1 || choice > 3)
                {
                    testMachine(regular);
                }
            } while (choice < 1 || choice > 3);
            

            switch (choice) {
                case 1:
                    testVendingFeatures(regular);
                    break;
                
                case 2:
                    displayMaintenance(regular);
                    break;
                
                case 3:
                    mainMenu(regular);
                    break;
            
                default:
                    mainMenu(regular);
                    break;
            }
        }
    
    }

    /**
     * testVendingFeatures allows the user to do the buying simulation of the vending machine.
     * @param regular arraylist of regular machine
     */
    private void testVendingFeatures(ArrayList<RegularMachine> regular)
    {
        Scanner sc = new Scanner(System.in);
        int choice, itemIndex;
        int insertedPayment;
        RegularMachine testMachine = regular.get(regular.size() - 1);

        do 
        {
            testMachine.displayMachine();
            System.out.println("[1] Buy");
            System.out.println("[2] Exit Test Mode");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            if (choice < 1 || choice > 2)
            {
                System.out.println("Enter valid choice.");
            }
            if (choice == 1)
            {
                do
                {
                    System.out.println();
                    System.out.print("Enter the number you want to buy: ");
                    itemIndex = sc.nextInt();
                    if (itemIndex < 1 || itemIndex > testMachine.countItems())
                    {
                        System.out.println("Enter valid number.");
                    }
                } while(itemIndex < 1 || itemIndex > testMachine.countItems());

                itemIndex--; // get the real index of the item in the array

                if(testMachine.checkQuantity(itemIndex))
                {
                    insertedPayment = testMachine.processPayment(itemIndex);
                    if (insertedPayment > 0)
                    {
                        System.out.println();
                        testMachine.dispenseItem(itemIndex);
                        System.out.println();
                        testMachine.saveTransaction(itemIndex, insertedPayment);
                    }
                }

                else
                {
                    System.out.println();
                    System.out.println("This product is currently unavailable.");
                }
            }
        } while (choice != 2);
        testMachine(regular);
    }

    /**
     * displayMaintenance asks the user to choose a maintenance feature to accomplish.
     * @param regular arraylist of regular machine
     */
    public void displayMaintenance(ArrayList<RegularMachine> regular)
    {
        int choice;

        Scanner sc = new Scanner(System.in);
        System.out.println("----------------------------------------");
        System.out.println("|               [FEATURES]             |");
        System.out.println("|           [1] Restock Item           |");
        System.out.println("|           [2] Change Price           |");
        System.out.println("|      [3] Collect Machine Balance     |");
        System.out.println("|     [4] Replenish Machine Balance    |");
        System.out.println("|        [5] Print Sales Report        |");
        System.out.println("|               [6] Back               |");
        System.out.println("----------------------------------------");
        System.out.print("Enter choice: ");
        do {
            choice = sc.nextInt();
            if (choice < 1 || choice > 5)
            {
                System.out.println("Enter a valid input");
                displayMaintenance(regular);
            }
        } while (choice < 1 || choice > 5);
        
        switch (choice) {
            case 1:
                displayRestock(regular);
                break;
            case 2:
                changePrice(regular);
                break;
            case 3:
                collectMachineBal(regular);
                break;
            case 4:
                replenishMachineBal(regular);
                break; 
            case 5:
                salesReport(regular);
                break;
            default:
                testMachine(regular);
                break;
        }
    }

    /**
     * salesReport displays the sales summary from the last restock date to today.
     * @param regular arraylist of regular machine
     */
    public void salesReport(ArrayList<RegularMachine> regular)
    {
        RegularMachine testMachine = regular.get(regular.size() - 1);
        System.out.println();
        testMachine.printSalesSummary();
        testMachine(regular);
    }

    /**
     * displayRestock processes the restocking of a certain item.
     * @param regular arraylist of regular machine
     */
    public void displayRestock(ArrayList<RegularMachine> regular)
    {
        Scanner sc = new Scanner(System.in);
        RegularMachine testMachine = regular.get(regular.size() - 1);
        int choice;
        int itemIndex;
        int itemQuantity;
        do 
        {
            testMachine.displayMachine();
            System.out.println("[1] Restock an Item");
            System.out.println("[2] Exit Restock Mode");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            if (choice < 1 || choice > 2)
            {
                System.out.println("Enter valid choice.");
            }

            System.out.println();

            if (choice == 1)
            {
                do
                {
                    System.out.print("Item number you want to restock: ");
                    itemIndex = sc.nextInt();
                    if (itemIndex < 1 || itemIndex > testMachine.countItems())
                    {
                        System.out.println("Enter valid number.");
                    }
                } while(itemIndex < 1 || itemIndex > testMachine.countItems());

                do 
                {
                    System.out.print("Enter additional quantity: ");
                    itemQuantity = sc.nextInt();
                    if (itemQuantity <= 0)
                    {
                        System.out.println("Please enter a correct value.");
                    }
                } while (itemQuantity <= 0);

                testMachine.restockItem(itemIndex-1, itemQuantity);
                System.out.println();
                System.out.println("Item is successfully restocked.");
                System.out.println();
            }
        } while (choice !=2);

        testMachine(regular);
    }

    /**
     * changePrice facilitates the changing of price of a certain item.
     * @param regular arraylist of regular machine
     */
    public void changePrice(ArrayList<RegularMachine> regular)
    {
        Scanner sc = new Scanner(System.in);
        RegularMachine testMachine = regular.get(regular.size() - 1);
        int choice;
        int itemIndex;
        int itemPrice;
        do 
        {
            testMachine.displayMachine();
            System.out.println("[1] Change Price of an Item");
            System.out.println("[2] Exit Edit Mode");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            if (choice < 1 || choice > 2)
            {
                System.out.println("Enter valid choice.");
            }

            System.out.println();

            if (choice == 1)
            {
                do
                {
                    System.out.print("Item number you want to change its price: ");
                    itemIndex = sc.nextInt();
                    if (itemIndex < 1 || itemIndex > testMachine.countItems())
                    {
                        System.out.println("Enter valid number.");
                    }
                } while(itemIndex < 1 || itemIndex > testMachine.countItems());

                do 
                {
                    System.out.print("Enter new price: ");
                    itemPrice = sc.nextInt();
                    if (itemPrice <= 0)
                    {
                        System.out.println("Please enter a correct value.");
                    }
                } while (itemPrice <= 0);

                testMachine.setPrice(itemIndex-1, itemPrice);
                System.out.println();
                System.out.println("Item price successfully changed.");
                System.out.println();
            }
        } while (choice !=2);

        testMachine(regular);
    }

    /**
     * replenishBalance handles the restocking of machine balance's denominations.
     * @param regular arraylist of regular machine
     */
    public void replenishMachineBal(ArrayList<RegularMachine> regular)
    {
        RegularMachine testMachine = regular.get(regular.size() - 1);
        int[][] replenishBal = askMachineBal();
        testMachine.replenishMachineBal(replenishBal);
        System.out.println();
        System.out.println("Adding it to the machine...");
        System.out.println("Replenishing machine balance is successful!");

        testMachine(regular);
    }

    /**
     * collectMachineBal facilitates the collecting of machine's balance.
     * @param regular arraylist of regular machine
     */
    public void collectMachineBal(ArrayList<RegularMachine> regular)
    {
        RegularMachine testMachine = regular.get(regular.size() - 1);
        testMachine.collectMachineBalance();
        testMachine(regular);
    }

    public static void main(String[] args)
    {
        ArrayList<RegularMachine> regular = new ArrayList<RegularMachine>();
        ArrayList<Items> itemlist = new ArrayList<Items>();

        // This is just for debugging. Must be removed or edited before submitting.
        Items item1 = new Items("Cup Noodle", 150, 385, 10);
        itemlist.add(item1);

        Items item2 = new Items("Soda", 50, 100, 10);
        itemlist.add(item2);

        Items item3 = new Items("Water", 50, 1, 10);
        itemlist.add(item3);

        Items item4 = new Items("Juice", 60, 85, 12);
        itemlist.add(item4);

        Items item5 = new Items("Coffee", 75, 50, 15);
        itemlist.add(item5);

        Items item6 = new Items("Oreo", 30, 125, 15);
        itemlist.add(item6);

        Items item7 = new Items("Bread", 45, 70, 11);
        itemlist.add(item7);

        Items item8 = new Items("Cookies", 50, 150, 10);
        itemlist.add(item8);

        int[][] balance = { {1, 1},
                            {5, 1},
                            {10, 1},
                            {20, 1},
                            {50, 1},
                            {100, 1},
                            {200, 1},
                            {500, 1},
                            {1000, 1}};

        RegularMachine sampleMachine = new RegularMachine(itemlist, balance);
        regular.add(sampleMachine);

        Driver driver = new Driver();
        driver.mainMenu(regular);
    }
}
