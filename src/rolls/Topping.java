package rolls;

public class Topping extends RollDecorator
{
    Roll roll;

    public Topping(Roll newRoll)
    {
        this.roll = newRoll;
    }

    public String getDescription()
    {
        return roll.getDescription() + ", extra toppings";
    }

    public double cost()
    {
        return roll.cost() + .30;
    }
}
