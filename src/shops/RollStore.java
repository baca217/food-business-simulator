package shops;
import rolls.*;

public abstract class RollStore
{
    protected abstract void startDay();
    protected abstract void sellRoll(String type);
}
