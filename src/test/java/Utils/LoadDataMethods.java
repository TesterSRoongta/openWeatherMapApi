package Utils;

import java.io.FileInputStream;
import java.util.Properties;

public class LoadDataMethods {
    Properties prop = new Properties();
    String propertyFilePath= System.getProperty("user.dir") + "/src/main/resources/configuration.properties";
    public LoadDataMethods() {
        try {
            FileInputStream fs = new FileInputStream(propertyFilePath);
            prop.load(fs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getBaseUrl() {
        String url = prop.getProperty("baseUrl");
        return url;
    }

    public String getAppId() {
        String appId = prop.getProperty("appId");
        return appId;
    }
}
