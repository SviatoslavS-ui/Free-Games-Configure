package libs;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigClass {
    public static String configFile = "src/config.properties";

    public static String getValueFromFile(String key, String fileName) throws IOException {
        Properties p = new Properties();
        FileInputStream cfg = new FileInputStream(fileName);
        p.load(cfg);
        cfg.close();
        return p.getProperty(key);
    }

    public static String getConfigValue(String key) throws IOException {
        return getValueFromFile(key, configFile);
    }
}
