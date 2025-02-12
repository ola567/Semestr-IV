import java.util.ArrayList;
import java.util.List;

public class Tasks
{
    private List<Integer> listOfNumbers = new ArrayList<>();

    public synchronized Integer take() throws InterruptedException
    {
        while(listOfNumbers.isEmpty())
        {
            wait();
        }
        return listOfNumbers.remove(0);
    }

    public synchronized void put(int task)
    {
        listOfNumbers.add(task);
        notifyAll();
    }

    public List<Integer> getTaskList() {
        return listOfNumbers;
    }

    public void setTaskList(List<Integer> taskList) {
        this.listOfNumbers = taskList;
    }
}
