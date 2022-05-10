public class Main {
    public static void main(String[] args) {
        EchoClient echoClient = new EchoClient("127.0.0.1", 6000);
        echoClient.connect();
    }
}
