package shops;
import rolls.*;

public abstract class RollStore
{
    protected abstract void startDay();
    protected abstract Roll getRoll(String type);
}
