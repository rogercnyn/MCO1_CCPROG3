/**
 * CashHandler is the class responsible for handling all the payment and change system.
 */

import java.util.Stack;

public class CashHandler 
{
    private int totalPayable;
    private int[] acceptedDenom = {1, 5, 10, 20, 50, 100, 200, 500, 1000};
    private Stack<Integer> payment;
    private Stack<Integer> change;
    private int[][] machineBalance;

    /**
     * Constructor that initializes the payment and change stack and set the machine balance.
     */
    public CashHandler()
    {
        this.payment = new Stack<Integer>();
        this.change = new Stack<Integer>();
        setMachineBalance();
    }

    /**
     * Sets the machine balance.
     */
    public void setMachineBalance()
    {
        this.machineBalance = new int[][] {{1, 10}, {5, 10}, {10, 10}, {20, 10},
                                {50, 10}, {100, 10}, {200, 10}, {500, 10}, {1000, 10}};
    }

    /**
     * Updates the value of total payable
     * @param totalPayable - total amount that must be paid
     */
    public void setTotalPayable(int totalPayable) 
    {
        this.totalPayable = totalPayable;
    }

    /**
     * Gets the total payable
     * @return total payable
     */

    public int getTotalPayable() 
    {
        return totalPayable;
    }

    /**
     * Gets the total amount of denomination that is still available
     * @param denom - denom to check
     * @return results
     */
    public int getTotalAvailableDenom(int denom)
    {
        int result = 0;
        int i, j;

        for (i = 0; i < this.machineBalance.length; i++)
        {
            for(j = 0; j < this.machineBalance[i].length - 1; j++)
            {
                if (denom == this.machineBalance[i][j])
                {
                    result++;
                }
            }
        }
        return result;
    }

    /**
     * Add the denom to payment stack
     * @param denom denomination paid
     */
    public void addDenomToPayment(int denom)
    {
        this.payment.push(denom);
    }

    /**
     * Returns the array of accepted denomination
     * @return array of accepted denomination
     */
    public int[] getAcceptedDenom() 
    {
        return acceptedDenom;
    }

    /**
     * Prints the denomination and the current number of stock the machine has
     * @param denom contains the denomination
     * @return string that contains the denomination and amount available
     */
    public String printDenom(int denom)
    {
        String string;
        int amountavailable=0;
        for (int i = 0; i < this.machineBalance.length; i++)
        {
            if (denom == this.machineBalance[i][0])
            {
                amountavailable = this.machineBalance[i][1];
            }
        }
        string = "₱ " + denom + " : " + amountavailable;
        return string;
    }

    /**
     * Transfer the stack of payment to the machine balance
     */
    private void transferPaymentToBalance()
    {
        while(!payment.empty())
        {
            addQuantityToBalance(payment.pop());
        }
    }

    /**
     * Add a stock to the denomination given
     * @param denom paid denomination
     */
    public void addQuantityToBalance(int denom)
    {
        int i, j;

        for (i = 0; i < this.machineBalance.length; i++)
        {
            for(j = 0; j < this.machineBalance[i].length - 1; j++)
            {
                if (denom == this.machineBalance[i][j])
                {
                    this.machineBalance[i][j+1] += 1;
                }
            }
        }
    }

    /**
     * Deducts a denomination in the machine balance
     * @param denom denomination to deduct
     */
    public void deductQuantityToBalance(int denom)
    {
        int i, j;

        for (i = 0; i < this.machineBalance.length; i++)
        {
            for(j = 0; j < this.machineBalance[i].length - 1; j++)
            {
                if (denom == this.machineBalance[i][j])
                {
                    this.machineBalance[i][j+1] -= 1;
                }
            }
        }
    }

    /**
     * Dispense the machine balance
     */
    public void collectBalance()
    {
        int i, j;

        for (i = 0; i < this.machineBalance.length; i++)
        {
            for(j = 0; j < this.machineBalance[i].length - 1; j++)
            {
                this.machineBalance[i][j+1] = 0;
            }
        }
    }

    /**
     * Checks if the machine still has a stock of the given denomination
     * @param denom denomination to check
     * @return true if there is still stock, false otherwise
     */
    public boolean hasDenomStock(int denom)
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
     * Check if the given number is an accepted denomination
     * @param denom a number to check
     * @return true if the number is a denomination, false otherwise
     */
    private boolean isDenom(int denom)
    {
        boolean check = false;
        for (int i = 0; i < this.acceptedDenom.length; i++)
        {
            if (denom == acceptedDenom[i])
            {
                return true;
            }
        }
        return check;
    }

    /**
     * Produce change
     * @return true if change is complete, false otherwise
     */
    public boolean produceChange()
    {
        boolean check = false;
        if (this.totalPayable < 0)
        {
            this.totalPayable *= -1;
        }

        int tempExpectedChange = this.totalPayable;

        if (isDenom(totalPayable) && hasDenomStock(totalPayable))
        {
            change.push(totalPayable);
            deductQuantityToBalance(totalPayable);
            totalPayable -= totalPayable;
        }

        else if (!isDenom(totalPayable))
        {
            for (int i = this.acceptedDenom.length - 1; i >= 0 && totalPayable > 0; --i)
            {

                while(totalPayable > this.acceptedDenom[i] && hasDenomStock(this.acceptedDenom[i]) && totalPayable > 0)
                {
                    deductQuantityToBalance(acceptedDenom[i]);
                    change.push(acceptedDenom[i]);
                    totalPayable -= acceptedDenom[i];
                }

                while (totalPayable % this.acceptedDenom[i] == 0 && hasDenomStock(this.acceptedDenom[i]) && totalPayable > 0)
                {
                    deductQuantityToBalance(acceptedDenom[i]);
                    change.push(acceptedDenom[i]);
                    totalPayable -= acceptedDenom[i];
                }
            }
        }
        if (isChangeComplete(tempExpectedChange))
        {
            transferPaymentToBalance();
            check = true;
        }

        else if (!isChangeComplete(tempExpectedChange))
        {
            check = false;
        }

        return check;
    }

    /**
     * Checks if the calculated change is equals to the expected change
     * @param expectedChange - expected change
     * @return true if it is equal, false otherwise
     */
    private boolean isChangeComplete(int expectedChange)
    {
        boolean check = false;
        int counter = 0;

        for (int i : change)
        {
            counter = counter + i;
        }

        if (counter == expectedChange)
        {
            check = true;
        }

        return check;
    }

    /**
     * Returns a success message if the change is successful
     * @return success message
     */
    public String successChange()
    {
        String output;
        if (change.empty())
        {
            output = "Thank you for paying the exact amount.";
        }

        else
        {
            int countTotal = 0;
            output = "Please get your change:";
            while (!change.empty())
            {
                int poppedValue = change.pop();
                countTotal += poppedValue;
                output += "\n₱" + poppedValue;
            }
            output += "\nTotal change: ₱" + countTotal + ".";
        }
    
        return output;
    }

    /**
     * Returns a fail message if the change is unsuccessful
     * @return an error message
     */
    public String failChange()
    {
        while (!change.empty())
        {
            int poppedValue = change.pop();
            addQuantityToBalance(poppedValue);
        }
        String output = "Sorry, this machine does not have enough balance to produce your change.";
        output += "\nPlease get your payment:";
        while (!payment.empty())
        {
            int poppedValue = payment.pop();
            output += "\n₱" + poppedValue;
        }
        return output;
    }

    /**
     * Produces a string if the user cancelled their order
     * @return a String message
     */
    public String cancel()
    {
         while (!change.empty())
        {
            int poppedValue = change.pop();
            addQuantityToBalance(poppedValue);
        }
        String output = "Transaction is now cancelled.";
        output += "\nPlease get your payment:";
        while (!payment.empty())
        {
            int poppedValue = payment.pop();
            output += "\n₱" + poppedValue;
        }
        return output;
    }

    /**
     * Checks if the payment stack is empty
     * @return true if empty, false otherwise
     */
    public boolean isPaymentStackEmpty()
    {
        boolean check = false;
        if (payment.empty())
        {
            check = true;
        }
        return check;
    }
}

