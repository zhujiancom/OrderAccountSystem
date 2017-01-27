package com.os.utils;

import com.os.config.GlobalSettings;
import org.apache.commons.configuration.ConversionException;
import org.apache.commons.configuration.PropertyConverter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Properties;

/**
 * Created by jian zhu on 1-16-17.
 */
public class PropertyUtils {
    private static Properties p;
    private static final Log logger = LogFactory.getLog(PropertyUtils.class);

    static{
        loadPropertiesResources(GlobalSettings.getInstance().getPropertyResourcesNames());
    }

    private static void loadPropertiesResources(String[] propertiesFiles){
        p = new Properties();
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        try {
            for (String propertiesFile : propertiesFiles) {
                Resource[] resources = resourcePatternResolver.getResources(propertiesFile);
                for(Resource resource:resources){
                    PropertiesLoaderUtils.fillProperties(p, resource);
                }
            }
        }
        catch (IOException ioe) {
            logger.error(ioe);
            throw new RuntimeException("Loading property resources occurs error!",ioe);
        }
    }

    public static Object getProperties(String key){
        return p.get(key);
    }

    public static String getStringValue(String key){
        return p.getProperty(key, "");
    }

    public static Integer getIntegerValue(String key){
        Object value = getProperties(key);
        try
        {
            return PropertyConverter.toInteger(value);
        }
        catch (ConversionException e)
        {
            throw new ConversionException('\'' + key + "' doesn't map to an Integer object", e);
        }
    }

    public static BigDecimal getBigDecimalValue(String key){
        Object value = getProperties(key);
        try
        {
            return PropertyConverter.toBigDecimal(value);
        }
        catch (ConversionException e)
        {
            throw new ConversionException('\'' + key + "' doesn't map to an BigDecimal object", e);
        }
    }
}
