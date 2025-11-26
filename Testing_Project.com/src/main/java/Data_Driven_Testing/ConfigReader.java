package Data_Driven_Testing;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;

    // Static block to load the file once when the class is accessed
    static {
        try {
            // Adjust the path below if your file is in a different location
            String path = "src/test/resources/Config.properties";
            FileInputStream input = new FileInputStream(path);
            properties = new Properties();
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load config.properties file.");
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}