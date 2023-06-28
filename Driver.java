import java.util.ArrayList;
import java.util.Scanner;

public class Driver 
{
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
        sc.close();
    }

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
        sc.close();
    }

    private String askItemName()
    {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter item name: ");
        String itemName = scan.nextLine();
        scan.close();
        return itemName;
    }

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

        scan.close();;
        return itemPrice;
    }

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
        scan.close();
        return itemCalories;
    }

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
        scan.close();
        return itemQuantity;
    }

    private int[][] askMachineBal()
    {
        int input;
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
        scan.close();
        return balance;
    }

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
        int machineCash;
        for (counter = 1; counter < 9; counter++)
        {
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
            System.out.println("Do you have more items to add?");
            System.out.print("[1] Yes or [2] No: ");
            addMoreItem = scan.nextInt();
            switch (addMoreItem) {
                case 1:
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

        scan.close();
        RegularMachine newReg = new RegularMachine(items, balance);
        regular.add(newReg);

        System.out.println("Congratulations! Your Regular Vending Machine is now ready!");
        mainMenu(regular);
    }
    
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
            
            sc.close();

            switch (choice) {
                case 1:
                    testVendingFeatures(regular);
                    break;
                
                case 2:
                    // Maintenance Features
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

    private void testVendingFeatures(ArrayList<RegularMachine> regular)
    {
        Scanner sc = new Scanner(System.in);
        int choice, itemIndex;
        int payment;
        boolean checkPayment;
        RegularMachine testMachine = regular.get(regular.size() - 1);

        if (testMachine.getMachineCash() == 0)
        {
            System.out.println("Sorry, this machine has no balance for your change.");
            testMachine(regular);
        }

        else
        {
            do {
                testMachine.displayMachine();
                System.out.print("[1] Buy | [2] Exit Test Mode: ");
                choice = sc.nextInt();
                if (choice < 1 || choice > 2)
                {
                    System.out.println("Enter valid choice.");
                }
                if (choice == 1)
                {
                    do
                    {
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
                        do {
                            System.out.print("[PAYMENT] ");
                            /*do {
                                
                            } while (payment );*/
                            payment = sc.nextInt();
                            if (!testMachine.verifyPayment(itemIndex, payment))
                            {
                                System.out.println("Enter sufficient amount.");
                            }
                        } while (!testMachine.verifyPayment(itemIndex, payment));
                        checkPayment = true;

                        // Recheck the necessity of calling the callPayment
                        testMachine.produceChange(checkPayment, payment, itemIndex);
                        testMachine.dispenseItem(checkPayment, itemIndex);
                        testMachine.saveTransaction(itemIndex, payment);
                    }

                    else
                    {
                        System.out.println("This product is currently unavailable.");
                    }
                }
            } while (choice != 2);
            sc.close();
            testMachine(regular);
        }
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

        int[][] balance = { {1, 3},
                            {5, 4},
                            {10, 8},
                            {20, 12},
                            {50, 16},
                            {100, 15},
                            {200, 14},
                            {500, 12},
                            {1000, 9}};

        RegularMachine sampleMachine = new RegularMachine(itemlist, balance);
        regular.add(sampleMachine);

        Driver driver = new Driver();
        driver.mainMenu(regular);
    }
}
