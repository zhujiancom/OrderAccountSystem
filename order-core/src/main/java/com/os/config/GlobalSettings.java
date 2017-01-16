package com.os.config;

import com.os.exceptions.ConfigLoaderException;
import com.os.utils.StringUtils;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.net.URL;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;

/**
 * Created by Jian Zhu on 12/27/2016.
 */
public class GlobalSettings {
    private final Log logger = LogFactory.getLog(GlobalSettings.class);

    private String defaultLocale = GlobalConstants.DEFAULT_LOCALE;
    private boolean reloadable = GlobalConstants.DEFAULT_CONFIGURATION_RELOADABLE;
    private PropertiesConfiguration configuration;

    private static class GlobalSettingsHolder{
        private static GlobalSettings instance = new GlobalSettings();
    }

    public static GlobalSettings getInstance(){
        return GlobalSettingsHolder.instance;
    }

    private GlobalSettings(){
        loadGlobalSettings();
    }

    private synchronized void loadGlobalSettings(){
        String propFileName = GlobalConstants.GLOBAL_CONFIG_FILE;
        logger.info("Trying to load global configuration file: " + propFileName);
        try{
            Resource res = new ClassPathResource(propFileName);
            URL url = res.getURL();
            configuration = new PropertiesConfiguration(url);
            if(isReloadable()){
                FileChangedReloadingStrategy strategy = new FileChangedReloadingStrategy();
                logger.debug("---------- refresh interval : "+configuration.getInt(PropertyConstants.GLOBAL_REFRESH_SECOND));
                strategy.setRefreshDelay(1000 * configuration
                        .getInt(PropertyConstants.GLOBAL_REFRESH_SECOND));
                configuration.setReloadingStrategy(strategy);
            }
            if(logger.isInfoEnabled()){
                logger.info("Load " + propFileName + " from " + res.getURL());
            }
        }catch (Exception ex){
            throw new ConfigLoaderException("Problem loading global properties from URL ["+ propFileName + "]",ex);
        }
        logger.info("Finished configuring global properties");
    }

    public boolean isReloadable(){
        try{
            boolean reloadableTemp = configuration.getBoolean(PropertyConstants.CONFIGURATION_RELOADABLE);
            reloadable = reloadableTemp;
        }
        catch(NoSuchElementException e){
            logger.warn(e);
        }
        return reloadable;
    }

    public boolean isI18nReloadable(){
        boolean i18nReload = false;
        try{
            boolean reloadableTemp = configuration.getBoolean(PropertyConstants.I18N_RELOADABLE);
            i18nReload = reloadableTemp;
        }
        catch(NoSuchElementException e){
            logger.warn(e);
        }
        return i18nReload;
    }

    public Locale getDefaultLocale(){
        try{
            String defaultLocaleTemp = configuration.getString(PropertyConstants.LOCALE);
            if(!StringUtils.hasText(defaultLocaleTemp)){
                defaultLocale = defaultLocaleTemp;
            }
        }
        catch(Exception e){
            logger.error(e);
        }
        return StringUtils.parseLocaleString(defaultLocale);
    }

    /**
     * @Function 获取所有配置的资源文件整合到一起
     * @return
     * @author zj
     * @Date 2014年8月7日
     */
    public String[] getPropertyResourcesNames(){
        List<Object> list = configuration
                .getList(PropertyConstants.PROPERTY_RESOURCES);
        return list.toArray(new String[0]);
    }

}
