import java.util.ArrayList;
import java.util.Scanner;

import javax.print.attribute.standard.MediaName;

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
    }

    private void createRegularMachine(ArrayList<RegularMachine> regular)
    {
        Scanner scan = new Scanner(System.in);
        ArrayList<Items> items = new ArrayList<Items>();
        String itemName;
        double itemPrice, itemCalories;
        int itemQuantity;
        int counter = 0;
        int addMoreItem;
        double machineCash;
        for (counter = 1; counter < 9; counter++)
        {
            System.out.println("Product #" + counter);
            System.out.print("Enter item name: ");
            itemName = scan.nextLine();
            do 
            {
                System.out.print("Enter item price: ");
                itemPrice = scan.nextDouble();
                if (itemPrice <= 0)
                {
                    System.out.println("The price must be a non-negative and non-zero value.");
                }
            } while (itemPrice <= 0);

            do 
            {
                System.out.print("Enter item calories: ");
                itemCalories = scan.nextDouble();
                if (itemCalories <= 0)
                {
                    System.out.println("The calories must be a non-negative and non-zero value.");
                }
            } while (itemCalories <= 0);

            do 
            {
                System.out.print("Enter item quantity: ");
                itemQuantity = scan.nextInt();
                if (itemQuantity < 10)
                {
                    System.out.println("Minimum quantity for a product is 10 items.");
                }
            } while (itemQuantity < 10);
            scan.nextLine();
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
                    System.out.print("Enter item name: ");
                    itemName = scan.nextLine();
                    do 
                    {
                        System.out.print("Enter item price: ");
                        itemPrice = scan.nextDouble();
                        if (itemPrice <= 0)
                        {
                            System.out.println("The price must be a non-negative and non-zero value.");
                        }
                    } while (itemPrice <= 0);

                    do 
                    {
                        System.out.print("Enter item calories: ");
                        itemCalories = scan.nextDouble();
                        if (itemCalories <= 0)
                        {
                            System.out.println("The calories must be a non-negative and non-zero value.");
                        }
                    } while (itemCalories <= 0);
                
                    do 
                    {
                        System.out.print("Enter item quantity: ");
                        itemQuantity = scan.nextInt();
                        if (itemQuantity < 10)
                        {
                            System.out.println("Minimum quantity for a product is 10 items.");
                        }
                    } while (itemQuantity < 10);
                    scan.nextLine();
                    Items newitem = new Items(itemName, itemPrice, itemCalories, itemQuantity);
                    items.add(newitem);
                    counter++;
                    break;
            
                default:
                    break;
            }
        } while (addMoreItem != 2);

        do 
        {
            System.out.print("Enter machine cash balance: ");
            machineCash = scan.nextDouble(); 
            if(machineCash <= 0)
            {
                System.out.println("The balance must be a non-negative and non-zero value.");
            } 
        } while (machineCash <= 0);

        RegularMachine newReg = new RegularMachine(items, machineCash);
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

        int choice;

        Scanner sc = new Scanner(System.in);
        System.out.println("----------------------------------------");
        System.out.println("|               [FEATURES]             |");
        System.out.println("|         [1] Vending Features         |");
        System.out.println("|       [2] Maintenance Features       |");
        System.out.println("|               [3] Exit               |");
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
                // Maintenance Features
                break;
          
            default:
                mainMenu(regular);
                break;
        }
    }

    private void testVendingFeatures(ArrayList<RegularMachine> regular)
    {
        Scanner sc = new Scanner(System.in);
        int choice, itemIndex;
        double payment;
        boolean checkPayment;
        RegularMachine testMachine = regular.get(regular.size() - 1);

        if (testMachine.getMachineCash() == 0)
        {
            System.out.println("Sorry, this machine has no balance for your change.");
        }

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
                        System.out.print("Enter payment: Php ");
                        payment = sc.nextDouble();
                        if (!testMachine.verifyPayment(itemIndex, payment))
                        {
                            System.out.println("Enter sufficient amount.");
                        }
                    } while (!testMachine.verifyPayment(itemIndex, payment));
                    checkPayment = true;
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

        testMachine(regular);
    }

    public static void main(String[] args)
    {
        ArrayList<RegularMachine> regular = new ArrayList<RegularMachine>();

        Driver driver = new Driver();
        driver.mainMenu(regular);
    }
}
