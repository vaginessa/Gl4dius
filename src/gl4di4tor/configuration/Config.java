package gl4di4tor.configuration;

import gl4di4tor.assets.AttackMode;
import gl4di4tor.log.LogService;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

/**
 * Created by gladiator on 7/11/17.
 */
public class Config {
    //region Fields
    private int proxyServerPort;
    private int webServerPort;
    private String defacePage;
    private int logLevel;
    private String mode;
    private String phishingMethod;
    private String phishingTargetDomain;
    private String phishingPage;
    //endregion

    //region Virtual Fields
    private AttackMode attackMode;
    //endregion

    private static Config instance;

    private Config() throws Exception {
        load();
    }

    public static Config getInstance() throws Exception {
        if (instance == null) {
            instance = new Config();
        }

        return instance;
    }

    private void load() throws Exception {
        File configFile = new File("Gl4dius.properties");
        FileReader fileReader = new FileReader(configFile);
        Properties config = new Properties();
        config.load(fileReader);
        this.proxyServerPort = Integer.valueOf(config.getProperty("proxy.server.port"));
        this.webServerPort = Integer.valueOf(config.getProperty("web.server.port"));
        this.defacePage = config.getProperty("deface.page");
        this.logLevel = Integer.valueOf(config.getProperty("log.level"));
        this.mode = config.getProperty("mode").toUpperCase();
        this.phishingMethod = config.getProperty("phishing.method");
        this.phishingTargetDomain = config.getProperty("phishing.target.domain");
        this.phishingPage = config.getProperty("phishing.page");

        validateConfiguration();
    }

    private void validateConfiguration() {
        try {
            this.attackMode = AttackMode.valueOf(this.mode);
        } catch (Exception ex) {
            LogService.fatal("Config file is invalid. invalid value for mode property", false);
            throw ex;
        }
    }

    //region Getter and Setter
    public int getProxyServerPort() {
        return proxyServerPort;
    }

    public void setProxyServerPort(int proxyServerPort) {
        this.proxyServerPort = proxyServerPort;
    }

    public String getDefacePage() {
        return defacePage;
    }

    public void setDefacePage(String defacePage) {
        this.defacePage = defacePage;
    }

    public int getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(int logLevel) {
        this.logLevel = logLevel;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public int getWebServerPort() {
        return webServerPort;
    }

    public void setWebServerPort(int webServerPort) {
        this.webServerPort = webServerPort;
    }

    public String getPhishingMethod() {
        return phishingMethod;
    }

    public void setPhishingMethod(String phishingMethod) {
        this.phishingMethod = phishingMethod;
    }

    public AttackMode getAttackMode() {
        return attackMode;
    }

    public void setAttackMode(AttackMode attackMode) {
        this.attackMode = attackMode;
    }

    public String getPhishingTargetDomain() {
        return phishingTargetDomain;
    }

    public void setPhishingTargetDomain(String phishingTargetDomain) {
        this.phishingTargetDomain = phishingTargetDomain;
    }

    public String getPhishingPage() {
        return phishingPage;
    }

    public void setPhishingPage(String phishingPage) {
        this.phishingPage = phishingPage;
    }
    //endregion
}
