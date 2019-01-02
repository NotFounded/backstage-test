package com.ruoyi.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

/**
 * 配置处理工具类
 * 
 * @author yml
 */
public class YamlUtil
{
	private static final Logger logger = LoggerFactory.getLogger(YamlUtil.class);
	
    public static Map<?, ?> loadYaml(String fileName) throws FileNotFoundException
    {
    	InputStream in = null;
    	String location = System.getProperty("spring.config.location");
    	logger.debug("load yaml location:{},fileName:{}", location, fileName);
    	if(StringUtils.isNotEmpty(location)) {
    		in = new FileInputStream(new File(location + fileName));
    	} else {
    		in = YamlUtil.class.getClassLoader().getResourceAsStream(fileName);
    	}
        return StringUtils.isNotEmpty(fileName) ? (LinkedHashMap<?, ?>) new Yaml().load(in) : null;
    }

    public static void dumpYaml(String fileName, Map<?, ?> map) throws IOException
    {
        if (StringUtils.isNotEmpty(fileName))
        {
            FileWriter fileWriter = new FileWriter(YamlUtil.class.getResource(fileName).getFile());
            DumperOptions options = new DumperOptions();
            options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
            Yaml yaml = new Yaml(options);
            yaml.dump(map, fileWriter);
        }
    }

    public static Object getProperty(Map<?, ?> map, Object qualifiedKey)
    {
        if (map != null && !map.isEmpty() && qualifiedKey != null)
        {
            String input = String.valueOf(qualifiedKey);
            if (!input.equals(""))
            {
                if (input.contains("."))
                {
                    int index = input.indexOf(".");
                    String left = input.substring(0, index);
                    String right = input.substring(index + 1, input.length());
                    return getProperty((Map<?, ?>) map.get(left), right);
                }
                else if (map.containsKey(input))
                {
                    return map.get(input);
                }
                else
                {
                    return null;
                }
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static void setProperty(Map<?, ?> map, Object qualifiedKey, Object value)
    {
        if (map != null && !map.isEmpty() && qualifiedKey != null)
        {
            String input = String.valueOf(qualifiedKey);
            if (!input.equals(""))
            {
                if (input.contains("."))
                {
                    int index = input.indexOf(".");
                    String left = input.substring(0, index);
                    String right = input.substring(index + 1, input.length());
                    setProperty((Map<?, ?>) map.get(left), right, value);
                }
                else
                {
                    ((Map<Object, Object>) map).put(qualifiedKey, value);
                }
            }
        }
    }
}