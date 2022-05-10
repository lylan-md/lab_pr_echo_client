import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class EchoClient {
    private final String host;
    private final int port;

    public EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void connect() {
        Socket socket = new Socket();
        InetAddress inetAddress;

        try {
            inetAddress = InetAddress.getByName(this.host);
            if (!inetAddress.isReachable(5)) {
                System.out.println("Host is unreachable!");
            }

            SocketAddress socketAddress = new InetSocketAddress(inetAddress, this.port);
            socket.connect(socketAddress);

            BufferedOutputStream output = new BufferedOutputStream(socket.getOutputStream());;
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader inKeyboard = new BufferedReader(new InputStreamReader(System.in));

            System.out.println(in.readLine());

            while (true) {
                String line = inKeyboard.readLine();
                output.write(line.getBytes());
                output.write("\r\n".getBytes());
                output.flush();
                System.out.println(in.readLine());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }
    }
}
