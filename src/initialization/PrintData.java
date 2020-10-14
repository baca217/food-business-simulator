package initialization;

import java.util.HashMap;
import java.util.List;

public class PrintData
{
    public void printRollSales(HashMap<String, Integer> rollSales, List<String> menu)
    {
        for (String item: menu)
        {
            System.out.println("roll type:"+item+" amount sold:"+rollSales.get(item));
        }
    }

}
