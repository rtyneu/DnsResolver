import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class DNSPacketManager {
    private byte[] HEADER;
    private byte[] QUESTION;
    private byte[] finalForm;
    private int finalFormLength;

    public DNSPacketManager() throws IOException {
        HEADER = new DNSHeader().getHeaderPartInByteArray();
        QUESTION = new DNSQuestion().getQuestionPartInByteArray();
        finalForm = createDNSPacket();
        finalFormLength = getFinalFormLength();
    }

    private byte[] createDNSPacket() throws IOException{
        ByteArrayOutputStream finalPacket = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(finalPacket);

        dataOutputStream.write(getHeader());
        dataOutputStream.write(getQuestion());

        return finalPacket.toByteArray();
    }

    public byte[] getHeader() {
        return this.HEADER;
    }

    public byte[] getQuestion() {
        return this.QUESTION;
    }

    public byte[] getFinalForm() {
        return this.finalForm;
    }

    public int getFinalFormLength() {
        return finalForm.length;
    }
}
