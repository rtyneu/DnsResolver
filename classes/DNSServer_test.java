import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.file.attribute.UserPrincipal;
import java.net.SocketAddress;
import java.net.InetSocketAddress;
import java.io.IOException;
import java.net.DatagramPacket;

public class DNSServer_test {
    public static void main(String[] args) {
        try {
            DatagramSocket DNSServer = new DatagramSocket(11111,
                    InetAddress.getByAddress(new byte[] { 10, 0, 0, 118 }));
            byte[] data = new byte[32];
            DatagramPacket testPacket = new DatagramPacket(data, 32,
                    InetAddress.getByAddress(new byte[] { 10, 0, 0, 118 }), 11111);
            System.out.println("the server is running, wait for datagram....");
            while (true) {
                DNSServer.receive(testPacket);
                data = testPacket.getData();
                System.out.println("the packet received from -->" + testPacket.getSocketAddress() + "\nprot--->"
                        + testPacket.getPort());
                System.out.println("the data is: " + new String(data, 0, data.length));
                System.out.println("the size of data is: " + data.length);

            }

        } catch (UnknownHostException error) {
            System.out.println(error);
        } catch (SocketException error) {
            System.out.println(error);
        } catch (IOException error) {
            System.out.println(error);
        }

    }
}
