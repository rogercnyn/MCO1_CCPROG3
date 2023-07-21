public class Meal extends Items implements Sellable {
    private int price;
    private boolean isRiceMeal;

    public Meal (String mealName, int mealCalories, boolean isRiceMeal){
        super(mealName, mealCalories);
        this.isRiceMeal = isRiceMeal;
    }

    public void setPrice(int price)
    {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public boolean isRiceMeal() {
        return isRiceMeal;
    }
}
