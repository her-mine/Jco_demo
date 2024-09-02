package com.mine.jco_framework.utils;

import com.mine.jco_framework.config.JcoDataProvider;
import com.mine.jco_framework.config.JcoSettings;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoException;

/**
 * @author lynn
 * @date 2024/8/27/16:14
 */
public class SapUtils {
    private final JcoSettings settings;

    public SapUtils(JcoSettings settings) {
        this.settings = settings;
        initJcoConnection(settings);
    }

    public static void initJcoConnection(JcoSettings settings) {
        try {
            JcoDataProvider.getSingleton().registerClientSettings(settings);

            JCoDestinationManager.getDestination(settings.getDefaultKey()).ping();
        } catch (JCoException e) {
            throw new RuntimeException("Unable to create：[" + settings.getDefaultKey() + "]", e);
        }
    }

    public JCoDestination getDestination() {
        try {
            return JCoDestinationManager.getDestination(this.settings.getDefaultKey());
        } catch (JCoException e) {
            throw new RuntimeException(e);
        }
    }
}
