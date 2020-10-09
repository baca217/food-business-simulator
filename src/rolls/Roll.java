package rolls;

public abstract class Roll {
    protected String type;
    protected String description;
    protected double cost;

    public Roll()
    {
        type = "unknown roll";
        cost = 0;
        description = type;
    }

    public String getRollType()
    {
        return this.type;
    }

    public String getDescription()
    {
        return description;
    }

    public double cost()
    {
        return this.cost;
    }
}
