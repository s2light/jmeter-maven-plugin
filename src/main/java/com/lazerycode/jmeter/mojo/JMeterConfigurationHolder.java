/**
 *
 */
package com.lazerycode.jmeter.mojo;

import com.lazerycode.jmeter.properties.ConfigurationFiles;
import com.lazerycode.jmeter.properties.PropertiesMapping;

import java.io.File;
import java.util.Collections;
import java.util.Map;

/**
 * Holds configuration 
 */
public class JMeterConfigurationHolder {
    private static final JMeterConfigurationHolder INSTANCE = new JMeterConfigurationHolder();
    private String runtimeJarName;
    private File workingDirectory;
    private Map<ConfigurationFiles, PropertiesMapping> propertiesMap;

    private boolean configurationFrozen;

    /**
     *
     */
    private JMeterConfigurationHolder() {
        super();
    }

    public static JMeterConfigurationHolder getInstance() {
        return INSTANCE;
    }

    /**
     * @return the runtimeJarName
     */
    public String getRuntimeJarName() {
        return runtimeJarName;
    }

    /**
     * @param runtimeJarName the runtimeJarName to set
     */
    public void setRuntimeJarName(String runtimeJarName) {
        if (configurationFrozen) {
            throw new IllegalStateException("setRuntimeJarName called while JMeter configuration already frozen");
        }
        this.runtimeJarName = runtimeJarName;
    }

    /**
     * @return the workingDirectory
     */
    public File getWorkingDirectory() {
        return workingDirectory;
    }

    /**
     * @param workingDirectory the workingDirectory to set
     */
    public void setWorkingDirectory(File workingDirectory) {
        if (configurationFrozen) {
            throw new IllegalStateException("setWorkingDirectory called while JMeter configuration already frozen");
        }
        this.workingDirectory = workingDirectory;
    }

    /**
     * @return the propertiesMap
     */
    public Map<ConfigurationFiles, PropertiesMapping> getPropertiesMap() {
        return propertiesMap;
    }

    /**
     * @param propertiesMap the propertiesMap to set
     */
    public void setPropertiesMap(Map<ConfigurationFiles, PropertiesMapping> propertiesMap) {
        if (configurationFrozen) {
            throw new IllegalStateException("setPropertiesMap called while JMeter configuration already frozen");
        }
        this.propertiesMap = Collections.unmodifiableMap(propertiesMap);
    }

    /**
     * Freeze configuration
     */
    public void freezeConfiguration() {
        this.configurationFrozen = true;
    }

    /**
     * Allow to reset configuration
     */
    public void resetConfiguration() {
        workingDirectory = null;
        runtimeJarName = null;
        propertiesMap = null;
        this.configurationFrozen = false;
    }
}
