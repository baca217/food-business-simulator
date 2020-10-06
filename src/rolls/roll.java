package rolls;

public abstract class roll {
    private String type;
    public roll()
    {
        type = this.getClass().getSimpleName();
    }
}
