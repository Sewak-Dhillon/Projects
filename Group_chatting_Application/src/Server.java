import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Server implements Runnable{
    Socket socket;
    public static Vector client = new Vector();

    public Server(Socket socket)
    {
        this.socket = socket;

    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            client.add(writer);

            while (true)
            {
                String data = reader.readLine().trim();

                for(int i = 0;i<client.size();i++)
                {
                    BufferedWriter bw = (BufferedWriter) client.get(i);
                    bw.write(data);
                    bw.write("\r\n");
                    bw.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws IOException {
        ServerSocket s = new ServerSocket(2003);
        while(true)
        {
            Socket socket = s.accept();
            Server server = new Server(socket);
            Thread thread = new Thread(server);
            thread.start();
        }
    }

}
