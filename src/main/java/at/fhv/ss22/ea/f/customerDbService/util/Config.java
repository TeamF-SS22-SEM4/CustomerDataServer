package at.fhv.ss22.ea.f.customerDbService.util;

import java.io.FileInputStream;
import java.util.Properties;

public class Config {

    private static final String CONFIG_PATH = "./customerServer.conf";
    private static Properties props;

    static {
        props = new Properties();
        try (FileInputStream fis = new FileInputStream(CONFIG_PATH)) {
            props.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //TODO maybe, implement default Values

    public static String getProperty(String key) {
        return props.getProperty(key);
    }
}
