import java.util.ArrayList;
import java.util.Scanner;

import javax.print.attribute.standard.MediaName;

public class Driver 
{
    private void mainMenu()
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
                mainMenu();
            }
        } while (choice < 1 || choice > 3);

        switch (choice) {
            case 1:
                createMachine();
                break;
            
            case 2:
                // TEST VENDING MACHINE
                break;
            
            case 3:
                break;

            default:
                break;
        }
    }

    private void createMachine()
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
                createMachine();
            }
        } while (choice < 1 || choice > 3);

        switch (choice) {
            case 1:
                createRegularMachine();
                break;

            case 2:
                System.out.println("This feature is not yet available.");
                break;
            
            case 3:
                mainMenu();
                break;
        }
    }

    private void createRegularMachine()
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
            System.out.println("[1] Yes or [2] No: ");
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

        RegularMachine regularMachine = new RegularMachine(items, machineCash);

        System.out.println("Congratulations! Your Regular Vending Machine is now ready!");
        mainMenu();
    }
    private void testVendingMachine(int vendingType)
    {
        int choice;
        Scanner sc = new Scanner(System.in);
        System.out.println("----------------------------------------");
        System.out.println("|               [FEATURES]             |");
        System.out.println("|      [1]   Vending Features          |");
        System.out.println("|      [2] Maintenance Features        |");
        System.out.println("|               [3] Exit               |");
        System.out.println("----------------------------------------");
        System.out.print("Enter choice: ");

        do {
            choice = sc.nextInt();
            if (choice < 1 || choice > 3)
            {
                mainMenu();
            }
        } while (choice < 1 || choice > 3);

        switch (choice) {
            case 1:
                //Vending Features
                break;
            
            case 2:
                // Maintenance Features
                break;
          
            default:
                break;
        }
    }

    private void selectingType()
    {
        int choice;
        Scanner sc = new Scanner(System.in);
        System.out.println("----------------------------------------");
        System.out.println("|            [VENDING MACHINE]         |");
        System.out.println("|     [1] Regular Vending Machine      |");
        System.out.println("|     [2] Special Vending Machine      |");
        System.out.println("|               [3] Exit               |");
        System.out.println("----------------------------------------");
        System.out.print("Enter choice: ");

        do {
            choice = sc.nextInt();
            if (choice < 1 || choice > 3)
            {
                mainMenu();
            }
        } while (choice < 1 || choice > 3);

        switch (choice) {
            case 1:
                testVendingMachine(choice);
                break;
            
            case 2:
                System.out.println("This feature is not available");
                selectingType();
                break;
          
            default:
                mainMenu();
                break;
        }
    }

    public static void main(String[] args)
    {
        Driver driver = new Driver();
        driver.mainMenu();
    }
}
