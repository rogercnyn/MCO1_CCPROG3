import java.util.Stack;

public class CashHandler 
{
    private int totalPayable;
    private int[] acceptedDenom = {1, 5, 10, 20, 50, 100, 200, 500, 1000};
    private Stack<Integer> payment;
    private Stack<Integer> change;
    private int[][] machineBalance;

    public CashHandler()
    {
        this.payment = new Stack<Integer>();
        this.change = new Stack<Integer>();
        this.machineBalance = new int[][] {{1, 10}, {5, 10}, {10, 10}, {20, 10},
                                {50, 10}, {100, 10}, {500, 10}, {1000, 10}};
    }

    public void setTotalPayable(int totalPayable) 
    {
        this.totalPayable = totalPayable;
    }

    public int getTotalPayable() 
    {
        return totalPayable;
    }

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

    public void addDenomToPayment(int denom)
    {
        this.payment.push(denom);
    }

    public int[] getAcceptedDenom() 
    {
        return acceptedDenom;
    }

    public String printDenom(int denom)
    {
        String string;
        int amountavailable=0;
        for (int i = 0; i < this.machineBalance.length; i++)
        {
            for(int j = 0; j < this.machineBalance[i].length - 1; j++)
            {
                if (denom == this.machineBalance[i][j])
                {
                    amountavailable++;
                }
            }
        }
        string = denom + " : " + amountavailable;
        return string;
    }

    private void transferPaymentToBalance()
    {
        while(!payment.empty())
        {
            addQuantityToBalance(payment.pop());
        }
    }

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
            for (int i = this.acceptedDenom.length - 1; i >= 0; --i)
            {
                if (totalPayable > this.acceptedDenom[i] && hasDenomStock(this.acceptedDenom[i]))
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
}

