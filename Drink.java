public class Drink extends Items implements Sellable{
    private int price;

    public Drink(String drinkName, int drinkCalories){
        super(drinkName, drinkCalories);
    }

    public void setPrice(int price)
    {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
