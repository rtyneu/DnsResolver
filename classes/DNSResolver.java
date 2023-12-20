import java.net.Socket;
import java.net.InetAddress;
import java.util.Scanner;
import java.util.Arrays;
import java.io.IOException;

public class DNSResolver {
    public static void main(String[] args) {
        System.out.println("Enter the dns server IP: ");
        Scanner userInput = new Scanner(System.in).useDelimiter("\\.");
        byte[] rawDNSIPAdress = new byte[4];
        for (int index = 0; index < 4; index++) {
            rawDNSIPAdress[index] = (byte) userInput.nextInt();
        }

        InetAddress dNSServerIP = InetAddress.getByAddress(rawDNSIPAdress);
        Socket connection = new Socket(InetAddress.getLocalHost(), 53, dNSServerIP, 53);
        System.out.println(connection.isBound());
        System.out.println(connection.isConnected());
    }

}