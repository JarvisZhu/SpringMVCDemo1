package javacommon.util;

import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Jarvis on 2015/9/12.
 */
public class ConfigUtil {
    private static Properties prop = null;
    private static final String CONFIGFILE_PATH = "/config.properties";

    public static String getProp(String key) {
        load();
        return prop.getProperty(key);
    }

    private static void load() {
        if (prop == null) {
            try {
                prop = new Properties();
                InputStream in = ConfigUtil.class.getResourceAsStream(CONFIGFILE_PATH);
                prop.load(in);
            } catch (Exception e) {
                prop = null;
                System.err.println("读取application.properties异常：" + e.toString());
            }
        }
    }
}
