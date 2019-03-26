import org.apache.shiro.crypto.hash.SimpleHash;

public class MainTest {
    public static void main(String[] args) {
        System.out.println(new SimpleHash("MD5", "admin", null, 1).toString());
    }
}
