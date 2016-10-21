package cn.icedoge.model;

/**
 * Created by Trialiet on 2016/10/21.
 */
public class Config {
    private String configName;
    private String configValue;

    public Config(){

    }

    public Config(String configName, String configValue) {
        this.configName = configName;
        this.configValue = configValue;
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }
}
