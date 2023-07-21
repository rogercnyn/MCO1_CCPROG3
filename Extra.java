public class Extra extends Items implements Sellable{
    private int price;

    public Extra (String extraName, int extraCalories){
        super(extraName, extraCalories);
    }

    public void setPrice(int price)
    {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
