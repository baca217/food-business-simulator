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
        return roll.getDescription() + ", extra topping";
    }

    public double cost()
    {
        return roll.cost() + .30;
    }

    public String getRollType()
    {
        return roll.getRollType();
    }
}
