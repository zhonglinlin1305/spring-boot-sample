package cn.kiiwii.framework.dubbo.provider;

import com.alibaba.dubbo.config.*;
import com.alibaba.dubbo.config.spring.AnnotationBean;
import com.alibaba.dubbo.rpc.Exporter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * Created by zhong on 2016/11/22.
 */
@Configuration
@ConditionalOnClass(Exporter.class)
@PropertySource(value = "classpath:/dubbo.properties")
public class DubboConfiguration implements EnvironmentAware {

    @Value("${dubbo.application.name}")
    private String applicationName;


    @Value("${dubbo.registr.protocol}")
    private String protocol;

    @Value("${dubbo.registry.address}")
    private String registryAddress;

    @Value("${dubbo.protocol.name}")
    private String protocolName;

    @Value("${dubbo.protocol.port}")
    private int protocolPort;

    @Value("${dubbo.provider.timeout}")
    private int timeout;

    @Value("${dubbo.provider.retries}")
    private int retries;

    @Value("${dubbo.provider.delay}")
    private int delay;

//    @Value("${dubbo.monitor.protocol}")
    private String monitorProtocol;

//    @Value("${dubbo.monitor.address}")
    private String monitorAddress;

//    @Value("${dubbo.monitor.username}")
    private String monitorUsername;

//    @Value("${dubbo.monitor.password}")
    private String monitorPassword;

//    @Value("${dubbo.monitor.group}")
    private String monitorGroup;

//    @Value("${dubbo.monitor.version}")
    private String monitorVersion;

    @Override
    public void setEnvironment(Environment environment) {
        RelaxedPropertyResolver propertyResolver = new RelaxedPropertyResolver(environment, "dubbo.monitor.");
        monitorProtocol = propertyResolver.getProperty("protocol","");
        monitorAddress = propertyResolver.getProperty("address","");
        monitorUsername = propertyResolver.getProperty("username","");
        monitorPassword = propertyResolver.getProperty("password","");
        monitorGroup = propertyResolver.getProperty("group","");
        monitorVersion = propertyResolver.getProperty("version","");
    }
    /**
     * 设置dubbo扫描包
     * @param packageName
     * @return
     */
    @Bean
    public static AnnotationBean annotationBean(@Value("${dubbo.annotation.package}") String packageName) {
        AnnotationBean annotationBean = new AnnotationBean();
        annotationBean.setPackage(packageName);
        return annotationBean;
    }

    /**
     * 注入dubbo上下文
     *
     * @return
     */
    @Bean
    public ApplicationConfig applicationConfig() {
        // 当前应用配置
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName(this.applicationName);
        return applicationConfig;
    }

    /**
     * 注入dubbo注册中心配置,基于zookeeper
     *
     * @return
     */
    @Bean
    public RegistryConfig registryConfig() {
        // 连接注册中心配置
        RegistryConfig registry = new RegistryConfig();
        registry.setProtocol(protocol);
        registry.setAddress(registryAddress);
        return registry;
    }

    /**
     * 默认基于dubbo协议提供服务
     *
     * @return
     */
    @Bean
    public ProtocolConfig protocolConfig() {
        // 服务提供者协议配置
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName(protocolName);
        protocolConfig.setPort(protocolPort);
        protocolConfig.setThreads(200);
        System.out.println("默认protocolConfig：" + protocolConfig.hashCode());
        return protocolConfig;
    }

    /**
     * dubbo服务提供
     *
     * @param applicationConfig
     * @param registryConfig
     * @param protocolConfig
     * @return
     */
    @Bean(name="myProvider")
    public ProviderConfig providerConfig(ApplicationConfig applicationConfig, RegistryConfig registryConfig, ProtocolConfig protocolConfig) {
        ProviderConfig providerConfig = new ProviderConfig();
        providerConfig.setTimeout(timeout);
        providerConfig.setRetries(retries);
        providerConfig.setDelay(delay);
        providerConfig.setApplication(applicationConfig);
        providerConfig.setRegistry(registryConfig);
        providerConfig.setProtocol(protocolConfig);
        return providerConfig;
    }

    @Bean
    public MonitorConfig monitorConfig(){
        MonitorConfig monitorConfig = new MonitorConfig();
        monitorConfig.setAddress(monitorAddress);
        monitorConfig.setGroup(monitorGroup);
        monitorConfig.setPassword(monitorPassword);
        monitorConfig.setProtocol(monitorProtocol);
        monitorConfig.setUsername(monitorUsername);
        monitorConfig.setVersion(monitorVersion);
        return monitorConfig;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }


    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getRegistryAddress() {
        return registryAddress;
    }

    public void setRegistryAddress(String registryAddress) {
        this.registryAddress = registryAddress;
    }

    public String getProtocolName() {
        return protocolName;
    }

    public void setProtocolName(String protocolName) {
        this.protocolName = protocolName;
    }

    public int getProtocolPort() {
        return protocolPort;
    }

    public void setProtocolPort(int protocolPort) {
        this.protocolPort = protocolPort;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getRetries() {
        return retries;
    }

    public void setRetries(int retries) {
        this.retries = retries;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public String getMonitorProtocol() {
        return monitorProtocol;
    }

    public void setMonitorProtocol(String monitorProtocol) {
        this.monitorProtocol = monitorProtocol;
    }

//    public String getMonitorAddress() {
//        return monitorAddress;
//    }
//
//    public void setMonitorAddress(String monitorAddress) {
//        this.monitorAddress = monitorAddress;
//    }
//
//    public String getMonitorUsername() {
//        return monitorUsername;
//    }
//
//    public void setMonitorUsername(String monitorUsername) {
//        this.monitorUsername = monitorUsername;
//    }
//
//    public String getMonitorPassword() {
//        return monitorPassword;
//    }
//
//    public void setMonitorPassword(String monitorPassword) {
//        this.monitorPassword = monitorPassword;
//    }
//
//    public String getMonitorGroup() {
//        return monitorGroup;
//    }
//
//    public void setMonitorGroup(String monitorGroup) {
//        this.monitorGroup = monitorGroup;
//    }
//
//    public String getMonitorVersion() {
//        return monitorVersion;
//    }
//
//    public void setMonitorVersion(String monitorVersion) {
//        this.monitorVersion = monitorVersion;
//    }
}
