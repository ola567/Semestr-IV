import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Results
{
    private Map<Integer, String> result = new TreeMap<>();
    public synchronized void addResult(int res, String ifPrime)
    {
        result.put(res, ifPrime);
    }

    public synchronized void print()
    {
        if(result.isEmpty())
        {
            System.out.println("There is no numbers");
        }
        else
        {
            for (Map.Entry<Integer, String> pair : result.entrySet())
            {
                System.out.println("Number: " + pair.getKey() + "  Is prime: " + pair.getValue());
            }
        }
    }
}
