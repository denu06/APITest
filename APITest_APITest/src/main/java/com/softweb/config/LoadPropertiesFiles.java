package com.softweb.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoadPropertiesFiles {

    public LoadPropertiesFiles() {
    }

    public static Properties loadProperties(String filePath) throws IOException {
        Properties prop = new Properties();
        InputStream ism = new FileInputStream(filePath);
        prop.load(ism);
        ism.close();
        return prop;
    }
}
