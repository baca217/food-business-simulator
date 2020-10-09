package rolls;

public class Sauce extends RollDecorator
{
    Roll roll;

    public Sauce(Roll newRoll)
    {
        this.roll = newRoll;
    }

    public String getDescription()
    {
        return roll.getDescription() + ", extra sauce";
    }

    public double cost()
    {
        return roll.cost() + .25;
    }
}
