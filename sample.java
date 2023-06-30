import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import javax.swing.text.DateFormatter;

public class sample {
    public static void main(String[] args)
    {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");
        System.out.println(formatter.format(today));
        /*int input;
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
                    System.out.print("Enter quantity of " + balance[i][j] + "s: ");
                    input = scan.nextInt();
                    if (input < 0)
                    {
                        System.out.println("Negative values are not accepted. The machine only accepts 0 quantity or more.");
                    }
                }while(input < 0);
                
                balance[i][j+1] = input;
            }
        }

                int[][] newbalance = { {1, 3},
                            {5, 4},
                            {10, 8},
                            {20, 12},
                            {50, 16},
                            {100, 15},
                            {200, 14},
                            {500, 12},
                            {1000, 9}};

          for (i = 0; i < balance.length; i++)
          {
               for (j = 0; j < balance[i].length - 1; j++)
               {
                    balance[i][j+1] += newbalance[i][j+1];
               }
          }

          int totalBalance = 0;
          System.out.println("Calculating total balance...");
          for (i = 0; i < balance.length; i++)
          {
               for (j = 0; j < balance[i].length - 1; j++)
               {
                    totalBalance += balance[i][j+1] * balance[i][j];
               }
          }

        System.out.println("Collecting a total of " + totalBalance);

        for (i = 0; i < balance.length; i++)
        {
            for (j = 0; j < balance[i].length - 1; j++)
            {
                System.out.println(balance[i][j] + " " + balance [i][j + 1]);
            }
        }

        for (i = 0; i < balance.length; i++)
          {
               for (j = 0; j < balance[i].length - 1; j++)
               {
                    balance[i][j+1] = 0;
               }
          }

        for (i = 0; i < balance.length; i++)
        {
            for (j = 0; j < balance[i].length - 1; j++)
            {
                System.out.println(balance[i][j] + " " + balance [i][j + 1]);
            }
        }*/
    }
}
