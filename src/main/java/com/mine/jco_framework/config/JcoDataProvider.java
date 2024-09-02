package com.mine.jco_framework.config;

import com.sap.conn.jco.ext.DestinationDataEventListener;
import com.sap.conn.jco.ext.DestinationDataProvider;
import com.sap.conn.jco.ext.Environment;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author lynn
 * @date 2024/8/27/15:46
 */
public class JcoDataProvider implements DestinationDataProvider {
    private final Map<String, Properties> clientSettingsProviders = new HashMap<>();
    private DestinationDataEventListener destinationDataEventListener;

    public static JcoDataProvider getSingleton() {
        return JcoDataProviderInstance.INSTANCE;
    }

    public static void registerInEnvironment() {
        Environment.registerDestinationDataProvider(JcoDataProviderInstance.INSTANCE);
    }

    private static Properties referDestinationData(JcoSettings settings) {
        Properties properties = new Properties();
        properties.setProperty(DestinationDataProvider.JCO_ASHOST, settings.getAshost());
        properties.setProperty(DestinationDataProvider.JCO_SYSNR, settings.getSysnr());
        properties.setProperty(DestinationDataProvider.JCO_CLIENT, settings.getClient());
        properties.setProperty(DestinationDataProvider.JCO_USER, settings.getUser());
        properties.setProperty(DestinationDataProvider.JCO_PASSWD, settings.getPasswd());
        properties.setProperty(DestinationDataProvider.JCO_LANG, settings.getLang());
        properties.setProperty(DestinationDataProvider.JCO_PEAK_LIMIT, settings.getPeakLimit());
        properties.setProperty(DestinationDataProvider.JCO_POOL_CAPACITY, settings.getPoolCapacity());
        return properties;
    }

    @Override
    public Properties getDestinationProperties(String destinationName) {
        if (clientSettingsProviders.containsKey(destinationName)) {
            return clientSettingsProviders.get(destinationName);
        }
        return null;
    }

    @Override
    public boolean supportsEvents() {
        return false;
    }

    @Override
    public void setDestinationDataEventListener(DestinationDataEventListener destinationDataEventListener) {
        this.destinationDataEventListener = destinationDataEventListener;
    }

    public void registerClientSettings(JcoSettings settings) {
        clientSettingsProviders.compute(settings.getDefaultKey(), (clientName, clientSettings) -> {
            if (clientSettings != null) {
                throw new RuntimeException("Destination:[" + clientName + "] has been already registered.");
            }
            return referDestinationData(settings);
        });
    }

    private static class JcoDataProviderInstance {
        private static final JcoDataProvider INSTANCE = new JcoDataProvider();
    }
}
