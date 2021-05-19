import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;

public class MainTest {

    public static String getHash(byte[] inputBytes,String algorithm){
        String hashValue ="";
        try{
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            messageDigest.update(inputBytes);
            byte[] digestedBytes = messageDigest.digest();
            hashValue = DatatypeConverter.printHexBinary(digestedBytes).toLowerCase();
        }catch (Exception e){

        }
        return hashValue;
    }

    public static void main(String[] args) {
        String s = "parola";
        System.out.println(getHash(s.getBytes(),"SHA-512"));
    }
}

