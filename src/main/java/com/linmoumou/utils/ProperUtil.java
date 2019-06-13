package com.linmoumou.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

public class ProperUtil {
    private static final Logger logger = LoggerFactory.getLogger(ProperUtil.class);

    public static Properties getProperties(String path) {
        Properties props = new Properties();
        try {
            props.load(ProperUtil.class.getClassLoader().getResourceAsStream(path));
        } catch (IOException e) {
            logger.error("read properties failed",e);
        }
        return props;
    }
}
