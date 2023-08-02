/**
 * Milktea extends Item and implements Sellable.
 */

public class Milktea extends Item implements Sellable {
    private int price;
    private Flavor flavor;
    private Sinker sinker;

    /**
     * Constructor that accept name and price as parameter
     * @param name - contains the name
     * @param price - contains the price
     */
    public Milktea(String name, int price)
    {
        super(name);
        this.flavor = null;
        this.sinker = null;
        setPrice(price);
    }

    /**
     * Constructor that accept name, flavor, sinker and price as parameter
     * @param name - contains the name
     * @param flavor - contains the flavor
     * @param sinker - contains the sinker
     * @param price - contains the price
     */
    public Milktea(String name, Flavor flavor, Sinker sinker, int price)
    {
        super(name, flavor.getItemCalories() + sinker.getItemCalories());
        this.flavor = flavor;
        this.sinker = sinker;
        setPrice(price);
    }

    /**
     * Sets the price
     * @param price - contains the price
     */
    public void setPrice(int price)
    {
        this.price = price;
    }

    /**
     * Gets the flavor of the milktea
     * @return flavor
     */
    public Flavor getFlavor() 
    {
        return flavor;
    }

    /**
     * Sets the flavor
     * @param flavor - chosen flavor
     */
    public void setFlavor(Flavor flavor) 
    {
        this.flavor = flavor;
    }

    /**
     * Gets the sinker of the milktea
     * @return sinker 
     */
    public Sinker getSinker() 
    {
        return sinker;
    }

    /**
     * Sets the sinker
     * @param sinker - chosen sinker
     */
    public void setSinker(Sinker sinker) 
    {
        this.sinker = sinker;
    }

    /**
     * Returns the price
     * @return price
     */
    public int getPrice() 
    {
        return price;
    }
}
