import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class DNSQuestion {
    private byte[] QNAME;
    private short QTYPE;
    private short QCLASS;

    public DNSQuestion() throws IOException{
        QNAME = formatQName("google.com");
        QTYPE = Short.parseShort("1", 2);
        QCLASS = Short.parseShort("1", 2);

    }

    private byte[] formatQName(String name) throws IOException {
        ByteArrayOutputStream qname = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(qname);

        String[] nameParts = name.split("\\.");

        for (int index = 0; index < nameParts.length; index++) {
            dataOutputStream.writeByte(nameParts[index].length());
            dataOutputStream.write(nameParts[index].getBytes());
        }
        dataOutputStream.writeByte(0);

        return qname.toByteArray();
    }

    public byte[] getQuestionPartInByteArray() throws IOException{
        ByteArrayOutputStream questionPartInByteArray = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(questionPartInByteArray);

        dataOutputStream.write(QNAME);
        dataOutputStream.writeShort(QTYPE);
        dataOutputStream.writeShort(QCLASS);

        return questionPartInByteArray.toByteArray();
    }
}
