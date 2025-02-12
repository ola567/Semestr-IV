import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        int numberOfThreads = Integer.parseInt(args[0]);
        Results results = new Results();
        Tasks tasks = new Tasks();
        Calculations calculations = new Calculations(tasks, results);
        List<Thread> arrayOfThreads = new ArrayList<>();
        Scanner scanner=new Scanner(System.in);
        String command = "asd";

        for(int i =0; i<numberOfThreads; i++)
        {
            Thread thread = new Thread(calculations);
            thread.start();
            arrayOfThreads.add(thread);
        }

        while(command.compareTo("end") != 0)
        {
            command = scanner.nextLine();
            if(command.compareTo("print") == 0)
            {
                results.print();
            }
            if(command.compareTo("add")==0)
            {
                command = scanner.nextLine();
                int toAdd = Integer.parseInt(command);
                tasks.put(toAdd);
            }
        }

        for( Thread thread: arrayOfThreads)
        {
            thread.interrupt();
        }
        for(Thread thread: arrayOfThreads)
        {
            try
            {
                thread.join();
            }
            catch (InterruptedException e)
            {

            }
        }
    }
}
