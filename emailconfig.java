
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class emailconfig {
    private static final String CONFIG_FILE = "email.properties";

    public static Properties loadConfig() throws IOException {
        Properties props = new Properties();
        FileInputStream fis = new FileInputStream(CONFIG_FILE);
        props.load(fis);
        fis.close();
        return props;
    }
}
