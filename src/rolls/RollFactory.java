package rolls;

public class RollFactory {
    public Roll createRoll(String type)
    {
        if(type.equals("egg"))
        {
            return new EggRoll();
        }
        else if(type.equals("jelly"))
        {
            return new JellyRoll();
        }
        else if(type.equals("pastry"))
        {
            return new PastryRoll();
        }
        else if(type.equals("sausage"))
        {
            return new SausageRoll();
        }
        else if(type.equals("spring"))
        {
            return new SpringRoll();
        }
        System.err.println(type+" is not a roll type!");
        return null;
    }
}
