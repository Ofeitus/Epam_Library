package com.epam.ofeitus.library.constant;

import java.util.ResourceBundle;

public class ConfigResourceManager {
    private final static ConfigResourceManager instance = new ConfigResourceManager();

    private final ResourceBundle bundle = ResourceBundle.getBundle("config");

    public static ConfigResourceManager getInstance() {
        return instance;
    }

    public String getValue(String key){
        return bundle.getString(key);
    }
}
