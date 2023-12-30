import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.nio.file.attribute.UserPrincipal;
import java.net.SocketAddress;
import java.net.InetSocketAddress;
import java.io.IOException;
import java.net.DatagramPacket;
import java.nio.ByteBuffer;
import java.io.DataOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutput;

public class DNSResolver {
    public static void main(String[] args) {

        try {
            DatagramSocket UDPSocket = new DatagramSocket(12345,
                    InetAddress.getByAddress(new byte[] { 10, 0, 0, 118 }));
            // this part will move to constructDNSPacket method later!

            /*
             * short ID = Short.parseShort("0000011101010101", 2);
             * short FLAGS = Short.parseShort("0000000000000000", 2);
             * short QDCOUNT = Short.parseShort("0000000000000001", 2);
             * short ADCOUNT = Short.parseShort("0000000000000000", 2);
             * short NSCOUNT = Short.parseShort("0000000000000000", 2);
             * short ARCOUNT = Short.parseShort("0000000000000000", 2);
             * 
             * ByteArrayOutputStream b = new ByteArrayOutputStream();
             * DataOutputStream DNSHeader = new DataOutputStream(b);
             * DNSHeader.writeShort(ID);
             * DNSHeader.writeShort(FLAGS);
             * DNSHeader.writeShort(QDCOUNT);
             * DNSHeader.writeShort(ADCOUNT);
             * DNSHeader.writeShort(NSCOUNT);
             * DNSHeader.writeShort(ARCOUNT);
             * 
             * // question section
             * ByteArrayOutputStream q = new ByteArrayOutputStream();
             * DataOutputStream DNSQuestion = new DataOutputStream(q);
             * 
             * String name = "google.com";
             * String[] nameParts = name.split("\\.");
             * 
             * for (int index = 0; index < nameParts.length; index++) {
             * DNSQuestion.writeByte(nameParts[index].length());
             * DNSQuestion.write(nameParts[index].getBytes(StandardCharsets.UTF_8));
             * }
             * DNSQuestion.writeByte(0);
             * 
             * short QTYPE = Short.parseShort("1", 2);
             * DNSQuestion.writeShort(QTYPE);
             * 
             * short QCLASS = Short.parseShort("1", 2);
             * DNSQuestion.writeShort(QCLASS);
             * 
             * ByteArrayOutputStream finalPacket = new ByteArrayOutputStream();
             * DataOutputStream DNSPacket = new DataOutputStream(finalPacket);
             * DNSPacket.write(b.toByteArray());
             * DNSPacket.write(q.toByteArray());
             */

            DNSPacketManager packet = new DNSPacketManager();

            int lengthOfPacket = packet.getFinalFormLength();
            DatagramPacket p = new DatagramPacket(packet.getFinalForm(),
                    lengthOfPacket,
                    InetAddress.getByAddress(new byte[] { 1, 1, 1, 1 }), 53);

            UDPSocket.send(p);

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