package shops;
import rolls.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ColoradoRollStore extends RollStore
{
    private HashMap<String, List<Roll>> rollInventory = new HashMap<String, List<Roll>>();
    private String[] rollTypes = {"egg", "jelly", "pastry", "sausage", "spring"};
    private RollFactory rollFactory = new RollFactory();


    public void startDay()
    {
        int i;
        int j;
        for(i = 0; i < rollTypes.length; i++)
        {
            String type = rollTypes[i];
            rollInventory.put(type, new ArrayList<Roll>());
            for(j = 0; j < 30; j++)
            {
                Roll tempRoll = rollFactory.createRoll(type);
                rollInventory.get(type).add(tempRoll);
            }
        }
    }
    public void sellRoll(String type)
    {
        if(rollInventory.get(type).size() > 0)
        {
            rollInventory.get(type).remove(0);
        }
        else
        {
            System.out.println("There are no more "+type+" rolls!");
        }
    }

    public int getInventory(String type)
    {
        return rollInventory.get(type).size();
    }
}
