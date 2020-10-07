package rolls;

public abstract class Roll {
    protected String type;
    public Roll()
    {
        type = this.getClass().getSimpleName();
    }
}
