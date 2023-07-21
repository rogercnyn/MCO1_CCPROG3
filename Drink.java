public class Drink extends Items implements Sellable{
    private double price;
    
    public Drink(String drinkName, int drinkCalories){
        super(drinkName, drinkCalories);
    }

    public void setPrice()
    {
        this.price = price;
    }
}
