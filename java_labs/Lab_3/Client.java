import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client
{
    public static void main(String[] args) throws IOException, ClassNotFoundException
    {
        Socket socket = new Socket("localhost", 9797);
        Scanner scanner = new Scanner(System.in);
        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

        int n = 0;
        while(socket.isConnected())
        {
            String received = (String)inputStream.readObject();
            System.out.println(received);
            if (received.equals("ready"))
            {
                String a = scanner.nextLine();
                if (a.equals("end"))
                    break;
                n = Integer.parseInt(a);
                outputStream.writeObject(n);
            }
            else if(received.equals("ready for messages"))
            {
                for (int i = 1; i<= n; i++)
                {
                    outputStream.writeObject(new Message(i, "message: "+ i));
                }
            }
            else if(received.equals("finished"))
            {
                System.out.println("ready");
                String a = scanner.nextLine();
                if (a.equals("end"))
                    break;
                n = Integer.parseInt(a);
                outputStream.writeObject(n);
            }
        }
        socket.close();
        outputStream.close();
    }
}
