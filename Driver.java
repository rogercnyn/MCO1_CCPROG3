import java.util.Scanner;

public class Driver 
{
    public static void mainMenu()
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

    public static void createMachine()
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
                //REGULAR VENDING MACHINE
                break;

            case 2:
                //SPECIAL VENDING MACHINE
                break;
            
            case 3:
                mainMenu();
                break;
        }
    }

    public static void main(String[] args)
    {
        mainMenu();
    }
}
