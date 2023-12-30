import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Random;

public class DNSHeader {

    private short ID;
    private short FLAGS;
    private short QDCOUNT;
    private short ADCOUNT;
    private short NSCOUNT;
    private short ARCOUNT;

    public DNSHeader() {
        ID = generateID();

        // for now I JUST CONSIDER IT IS A HEADER FOR REQUEST SO,
        /*
         * QR ------>0
         * OPCODE--->0000 (standard query)
         * AA ------>0
         * TC ------>0 for now!! I am not sure how to truncate :(
         * RD ------>1
         * RA ------>0 since this is a request
         * Z ------->000
         * RCODE --->0000 response code, here all zeros
         */
        FLAGS = Short.parseShort("00000001000000000", 2);

        QDCOUNT = Short.parseShort("0000000000000001", 2);
        ADCOUNT = Short.parseShort("0000000000000000", 2);
        NSCOUNT = Short.parseShort("0000000000000000", 2);
        ARCOUNT = Short.parseShort("0000000000000000", 2);

    }

    private static short generateID() {
        return (short) new Random().nextInt(65536);
    }

    public byte[] getHeaderPartInByteArray() {
        ByteArrayOutputStream HeaderByteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(HeaderByteArrayOutputStream);

        try {
            dataOutputStream.writeShort(ID);
            dataOutputStream.writeShort(FLAGS);
            dataOutputStream.writeShort(QDCOUNT);
            dataOutputStream.writeShort(ADCOUNT);
            dataOutputStream.writeShort(NSCOUNT);
            dataOutputStream.writeShort(ARCOUNT);
        } catch (IOException e) {
            System.out.println(e);
        }

        return HeaderByteArrayOutputStream.toByteArray();

    }

}