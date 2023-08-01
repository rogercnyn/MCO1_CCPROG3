public class Milktea extends Item implements Sellable {
    private int price;
    private Flavor flavor;
    private Sinker sinker;
    
    public Milktea(String name, int price)
    {
        super(name);
        this.flavor = null;
        this.sinker = null;
        setPrice(price);
    }

    public Milktea(String name, Flavor flavor, Sinker sinker, int price)
    {
        super(name, flavor.getItemCalories() + sinker.getItemCalories());
        this.flavor = flavor;
        this.sinker = sinker;
        setPrice(price);
    }

    public void setPrice(int price)
    {
        this.price = price;
    }

    public Flavor getFlavor() 
    {
        return flavor;
    }

    public void setFlavor(Flavor flavor) 
    {
        this.flavor = flavor;
    }

    public Sinker getSinker() 
    {
        return sinker;
    }

    public void setSinker(Sinker sinker) 
    {
        this.sinker = sinker;
    }

    public int getPrice() 
    {
        return price;
    }
}
