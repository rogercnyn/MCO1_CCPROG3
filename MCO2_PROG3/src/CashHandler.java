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

    public void addDenomToPayment(int denom)
    {
        this.payment.push(denom);
    }

    public int[] getAcceptedDenom() 
    {
        return acceptedDenom;
    }

    public void transferPaymentToBalance()
    {
        while(!payment.empty())
        {
            addDenomToPayment(payment.pop());
        }
    }

    private void addQuantityToBalance(int denom)
    {
        int i, j;

        for (i = 0; i < this.machineBalance.length; i++)
        {
            for(j = 0; j < this.machineBalance[i].length; j++)
            {
                if (denom == this.machineBalance[i][j])
                {
                    this.machineBalance[i][j+1] += 1;
                }
            }
        }
    }
}

