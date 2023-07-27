public class Sinker extends Item implements Sellable {
    private int price;

    public Sinker(String sinkerName, int sinkerCalories)
    {
        super(sinkerName, sinkerCalories);
    }

    public Sinker(String sinkerName, int sinkerCalories, int price)
    {
        super(sinkerName, sinkerCalories);
        setPrice(price);
    }

    public void setPrice(int price)
    {
        this.price = price;
    }

    public int getPrice() 
    {
        return price;
    }

}
