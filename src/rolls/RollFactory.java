package rolls;

public class RollFactory {
    public Roll createRoll(String type)
    {
        switch (type)
        {
            case "egg roll":
                return new EggRoll();
            case "jelly roll":
                return new JellyRoll();
            case "pastry roll":
                return new PastryRoll();
            case "sausage roll":
                return new SausageRoll();
            case "spring roll":
                return new SpringRoll();
            default:
                System.err.println(type+" is not a roll type!");
                return null;
        }
    }
}
