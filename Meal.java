public class Meal extends Items implements Sellable {
    private double price;

    public Meal (String mealName, int mealCalories){
        super(mealName, mealCalories);
    }

    public void setPrice()
    {
        this.price = price;
    }
}
