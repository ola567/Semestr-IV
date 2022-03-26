import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler implements Runnable
{
    private Socket client;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    public ClientHandler(Socket client) throws IOException
    {
        this.client = client;
        outputStream = new ObjectOutputStream(client.getOutputStream());
        inputStream = new ObjectInputStream(client.getInputStream());
    }

    @Override
    public void run()
    {
        String mes1 = "ready";
        String mes2 = "ready for messages";
        String mes3 = "finished";
        int n = 0;
        int counter = 0;

        try
        {
            outputStream.writeObject(mes1);
            while(client.isConnected())
            {
                try
                {
                    Object request = inputStream.readObject();
                    if (request instanceof Integer)
                    {
                        n = (int)request;
                        outputStream.writeObject(mes2);
                    }
                    if (request instanceof Message)
                    {
                        System.out.println(request.toString());
                        counter++;
                    }
                    if(counter == n)
                    {
                        System.out.println("\n");
                        outputStream.writeObject(mes3);
                        counter = 0;
                    }
                }
                catch (IOException e)
                {
                    System.out.println("Client is ended");
                    break;
                }
                catch (ClassNotFoundException e)
                {
                    e.printStackTrace();
                }

            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        try
        {
            inputStream.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        try
        {
            outputStream.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
