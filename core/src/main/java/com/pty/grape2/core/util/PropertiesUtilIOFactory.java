package com.pty.grape2.core.util;

import org.apache.commons.configuration.PropertiesConfiguration;

import java.io.Writer;

/**
 * @author lxl
 */
public class PropertiesUtilIOFactory extends PropertiesConfiguration.DefaultIOFactory {
    @Override
    public PropertiesConfiguration.PropertiesWriter createPropertiesWriter(Writer out, char delimiter) {
        return new PropertiesUtilWriter(out, delimiter);
    }
}
