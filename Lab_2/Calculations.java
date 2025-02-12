public class Calculations implements Runnable
{
    private Tasks tasks;
    private Results results;

    public Calculations(Tasks tasks, Results results)
    {
        this.tasks = tasks;
        this.results = results;
    }

    @Override
    public void run()
    {
        try
        {
            while(!Thread.interrupted())
            {
                int number = tasks.take();
                String ifPrime = "yes";

                if (number<=1)
                {
                    ifPrime = "no";
                }
                for(int i = 2; i*i<=number; i++)
                {
                    if(number % i == 0)
                    {
                        ifPrime = "no";
                        break;
                    }
                }
                results.addResult(number, ifPrime);
            }
        }
        catch (InterruptedException e)
        {

        }
    }

}
