public class Extra extends Items implements Sellable{
    private double price;
    
    public Extra (String extraName, int extraCalories){
        super(extraName, extraCalories);
    }

    public void setPrice()
    {
        this.price = price;
    }
}
