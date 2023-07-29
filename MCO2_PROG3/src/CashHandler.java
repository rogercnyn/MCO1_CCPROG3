import java.util.Stack;

public class CashHandler 
{
    private int totalPayable;
    private int[] acceptedDenom = {1, 5, 10, 20, 50, 100, 200, 500, 1000};
    private Stack<Integer> payment;
    private Stack<Integer> change;

    public CashHandler()
    {
        this.payment = new Stack<Integer>();
        this.change = new Stack<Integer>();
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
}

