package customers;

import rolls.EggRoll;
import rolls.JellyRoll;
import rolls.PastryRoll;
import rolls.Roll;
import rolls.SausageRoll;
import rolls.SpringRoll;

import java.util.ArrayList;
import java.util.List;

abstract public class Customer {
    List<Roll> rollOptions = new ArrayList<>() {{
        add(new EggRoll());
        add(new JellyRoll());
        add(new PastryRoll());
        add(new SausageRoll());
        add(new SpringRoll());
    }};

    public abstract List<Roll> rollOrders();

}
