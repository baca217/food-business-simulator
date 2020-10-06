package rolls;

import javax.lang.model.type.NullType;

public class rollFactory {
    public roll createRoll(String type)
    {
        if(type.equals("egg"))
        {
            return new eggRoll();
        }
        else if(type.equals("jelly"))
        {
            return new jellyRoll();
        }
        else if(type.equals("pastry"))
        {
            return new pastryRoll();
        }
        else if(type.equals("sausage"))
        {
            return new sausageRoll();
        }
        else if(type.equals("spring"))
        {
            return new springRoll();
        }
        System.err.println(type+" is not a roll type!");
        return null;
    }
}
