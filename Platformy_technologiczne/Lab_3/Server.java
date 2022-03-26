import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server
{
    private static ArrayList<ClientHandler> clients = new ArrayList<>();
    private static ExecutorService pool = Executors.newFixedThreadPool(4);

    public static void main(String[] args) throws IOException, ClassNotFoundException
    {
        ServerSocket serverSocket = new ServerSocket(9797);

        while(true)
        {
            System.out.println("wating for connection...");
            Socket socket = serverSocket.accept();
            System.out.println("connected");

            ClientHandler clientThread = new ClientHandler(socket);
            clients.add(clientThread);
            pool.execute(clientThread);
        }

    }
}
