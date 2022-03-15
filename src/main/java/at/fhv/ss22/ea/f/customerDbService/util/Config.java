package at.fhv.ss22.ea.f.customerDbService.util;

import java.io.FileInputStream;
import java.util.Optional;
import java.util.Properties;

public class Config {

    private static final String CONFIG_PATH = "./customerServer.conf";
    private static final String DEFAULT_CONFIG_PATH = "./customerServer.default.conf";
    private static final Properties CURRENT_PROPS;
    private static final Properties DEFAULT_PROPS;

    static {
        DEFAULT_PROPS = new Properties();
        try (FileInputStream fis = new FileInputStream(DEFAULT_CONFIG_PATH)) {
            DEFAULT_PROPS.load(fis);
        } catch (Exception e) {
            System.err.println("Failed to load default configuration");
            e.printStackTrace();
        }

        CURRENT_PROPS = new Properties();
        try (FileInputStream fis = new FileInputStream(CONFIG_PATH)) {
            CURRENT_PROPS.load(fis);
        } catch (Exception e) {
            //choose to ignore it because we have default configuration
        }
    }

    //TODO maybe, implement default Values
    public static String getProperty(String key) {
        return Optional.ofNullable(CURRENT_PROPS.getProperty(key)).orElse(DEFAULT_PROPS.getProperty(key));
    }
}
