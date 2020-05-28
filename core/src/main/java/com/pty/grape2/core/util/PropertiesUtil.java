package com.pty.grape2.core.util;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.io.File;

/**
 * @author lxl
 */
@Slf4j
public class PropertiesUtil {

    private File file;

    private PropertiesConfiguration propertiesConfiguration;

    public PropertiesUtil(String filePath) {
        log.debug("----->PropertiesUtil初始化文件路径:{}", filePath);
        readProperties(filePath);
    }

    private void readProperties(String filePath) {
        try {
            this.file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            this.propertiesConfiguration = new PropertiesConfiguration();
            this.propertiesConfiguration.setIOFactory(new PropertiesUtilIOFactory());
            this.propertiesConfiguration.setEncoding("utf-8");
            this.propertiesConfiguration.setFileName(filePath);
            this.propertiesConfiguration.setAutoSave(Boolean.TRUE);
            this.propertiesConfiguration.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String key) {
        return propertiesConfiguration.getString(key);
    }

    public void setProperty(String key, String value) {
        propertiesConfiguration.setProperty(key, value);
    }

}
