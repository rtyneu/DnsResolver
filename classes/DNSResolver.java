import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.file.attribute.UserPrincipal;
import java.net.SocketAddress;
import java.net.InetSocketAddress;
import java.io.IOException;
import java.net.DatagramPacket;

public class DNSResolver {
    public static void main(String[] args) {

        try {
            byte[] data = new byte[32];
            DatagramPacket testPacket = new DatagramPacket(data, 32, InetAddress.getLocalHost(), 11111);
            DatagramSocket UDPSocket = new DatagramSocket(12345,
                    InetAddress.getByAddress(new byte[] { 10, 0, 0, 118 }));
            UDPSocket.send(testPacket);

            System.out.println("the packet successfully sent!");
        } catch (UnknownHostException error) {
            System.out.println(error);
        } catch (SocketException error) {
            System.out.println(error);
        } catch (IOException error) {
            System.out.println(error);
        }
    }
}