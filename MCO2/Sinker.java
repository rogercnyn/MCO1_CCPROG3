/**
 * Sinker implements Sellable and extends Item.
 */

public class Sinker extends Item implements Sellable {
    private int price;

    /**
     * Constructor that accept sinkername and sinkercalories as parameter
     * @param sinkerName - contains the sinker name
     * @param sinkerCalories - contains the calories name
     */
    public Sinker(String sinkerName, int sinkerCalories)
    {
        super(sinkerName, sinkerCalories);
    }

    /**
     * Constructor that accept sinkername, sinkercalories, and price as parameter
     * @param sinkerName - contains the sinker name
     * @param sinkerCalories - contains the calories name
     * @param price - contains the price
     */
    public Sinker(String sinkerName, int sinkerCalories, int price)
    {
        super(sinkerName, sinkerCalories);
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
     * Gets the price
     * @return price
     */
    public int getPrice() 
    {
        return price;
    }

}
