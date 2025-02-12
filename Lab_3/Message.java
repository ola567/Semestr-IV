import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Message implements Serializable
{
    private int number;
    private String content;
    private List tab;

    public Message(int number, String content)
    {
        this.number = number;
        this.content = content;
        this.tab = new ArrayList();

        //generating 10 random numbers
        Random random = new Random();
        for (int i = 0; i< 10; i++)
        {
            int n = random.nextInt(number);
            tab.add(n);
        }
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List getTab() {
        return tab;
    }

    public void setTab(List tab) {
        this.tab = tab;
    }

    @Override
    public String toString()
    {
        return "Message{" +
                "number=" + number +
                ", content='" + content + '\'' +
                ", tab=" + tab +
                '}';
    }
}
