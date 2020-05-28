package com.pty.grape2.core.util;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.List;

/**
 * @author lxl
 */
public class PropertiesUtilWriter extends PropertiesConfiguration.PropertiesWriter {
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");
    private static final char[] SEPARATORS = new char[]{'=', ':'};
    private static final char[] WHITE_SPACE = new char[]{' ', '\t', '\f'};

    public PropertiesUtilWriter(Writer writer, char delimiter) {
        super(writer, delimiter);
    }

    private static final int BUF_SIZE = 8;
    private char delimiter;
    private String currentSeparator;
    private String globalSeparator;
    private String lineSeparator;

    @Override
    public void writeProperty(String key, Object value, boolean forceSingleLine) throws IOException {
        String v;
        if (value instanceof List) {
            List<?> values = (List)value;
            if (!forceSingleLine) {
                this.writeProperty(key, values);
                return;
            }

            v = this.makeSingleLineValue(values);
        } else {
            v = (String) value;
        }

        this.write(this.escapeKey(key));
        this.write(this.fetchSeparator(key, value));
        this.write(v);
        this.writeln(null);
    }

    private String escapeKey(String key) {
        StringBuilder newkey = new StringBuilder();

        for(int i = 0; i < key.length(); ++i) {
            char c = key.charAt(i);
            if (!ArrayUtils.contains(SEPARATORS, c) && !ArrayUtils.contains(WHITE_SPACE, c)) {
                newkey.append(c);
            } else {
                newkey.append('\\');
                newkey.append(c);
            }
        }

        return newkey.toString();
    }

    private String escapeValue(Object value, boolean inList) {
        String escapedValue = this.handleBackslashs(value, inList);
        if (this.delimiter != 0) {
            escapedValue = StringUtils.replace(escapedValue, String.valueOf(this.delimiter), "\\" + this.delimiter);
        }

        return escapedValue;
    }

    private String handleBackslashs(Object value, boolean inList) {
        String strValue = String.valueOf(value);
        if (inList && strValue.indexOf("\\\\") >= 0) {
            char esc = "\\".charAt(0);
            StringBuilder buf = new StringBuilder(strValue.length() + 8);

            for(int i = 0; i < strValue.length(); ++i) {
                if (strValue.charAt(i) == esc && i < strValue.length() - 1 && strValue.charAt(i + 1) == esc) {
                    buf.append("\\\\").append("\\\\");
                    ++i;
                } else {
                    buf.append(strValue.charAt(i));
                }
            }

            strValue = buf.toString();
        }

        return StringEscapeUtils.escapeJava(strValue);
    }

    private String makeSingleLineValue(List<?> values) {
        if (!values.isEmpty()) {
            Iterator<?> it = values.iterator();
            String lastValue = this.escapeValue(it.next(), true);
            StringBuilder buf = new StringBuilder(lastValue);

            while(it.hasNext()) {
                if (lastValue.endsWith("\\") && countTrailingBS(lastValue) / 2 % 2 != 0) {
                    buf.append("\\").append("\\");
                }

                buf.append(this.delimiter);
                lastValue = this.escapeValue(it.next(), true);
                buf.append(lastValue);
            }

            return buf.toString();
        } else {
            return null;
        }
    }

    private static int countTrailingBS(String line) {
        int bsCount = 0;

        for(int idx = line.length() - 1; idx >= 0 && line.charAt(idx) == '\\'; --idx) {
            ++bsCount;
        }

        return bsCount;
    }

}
