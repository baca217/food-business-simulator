package rolls;

import java.util.List;

public class Filling extends RollDecorator
{
    Roll roll;

    public Filling(Roll newRoll)
    {
        this.roll = newRoll;
    }

    public String getDescription()
    {
        return roll.getDescription() + ", extra filling";
    }

    public double cost()
    {
        return roll.cost() + .20;
    }

    public String getRollType()
    {
        return roll.getRollType();
    }
}
