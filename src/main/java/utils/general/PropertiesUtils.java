package utils.general;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtils {

    private static final Logger logger = LoggerFactory.getLogger(PropertiesUtils.class);
    private static InputStream inputStream = null;
    private static Properties properties = null;
    private static String propFile = null;

    static {
        logger.trace("Inside static block..");
        properties = new Properties();
    }

    public static String getPropertyFileName() {
        return propFile;
    }

    public static void load(String resource) {
        logger.trace("loading resource..");
        if (resource.trim().length() == 0) {
            logger.debug("resource name is empty");
            return;
        }
        propFile = resource;
        inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream(propFile);
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    public static String get(String key) {
        String value = properties.getProperty(key);
        logger.debug("property retrieved {} = {}", key, value);
        return value;
    }

}
