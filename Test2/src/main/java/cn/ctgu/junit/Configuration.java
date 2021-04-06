package cn.ctgu.junit;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class Configuration {
    private Properties props = new Properties();
    /**
     * 读取属性配置文件,即将文件内容写入Properties
     *
     *
     * @param confFile
     */
    public void fromFile(File confFile) {
        try (FileInputStream in = new FileInputStream(confFile);) {
            props.load(new InputStreamReader(in, StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new ValueParseException("配置文件解析错误");
        }
    }

    /**
     * 获取键值
     * 读取指定属性key的属性值value，如果键值不存在，则抛出ParseErrorException异常
     * @param key
     * @return value
     */
    public String getProp(String key) {
        String value = props.getProperty(key);
        if (value != null) {
            return value;
        }
        throw new ValueParseException("键值不存在");
    }

    /**
     * 获取布尔类型键值
     * 读取指定属性key的布尔类型的值，如果解析出错，则抛出ParserErrorException异常
     * @param key
     * @return value
     */
    public boolean getBoolean(String key) {
        String value = props.getProperty(key);
        if ("true".equalsIgnoreCase(value)) {
            return true;
        }
        if ("false".equalsIgnoreCase(value)) {
            return false;
        }
        throw new ValueParseException("键值不存在");
    }

    /**
     * 返回指定键的布尔值；如果值不存在，则返回传入的缺省值
     * @param key
     * @param defaultValue
     * @return
     */
    public Boolean getBooleanWithDefault(String key, Boolean defaultValue) {
        String value = props.getProperty(key);
        if ("true".equalsIgnoreCase(value)) {
            return true;
        }
        if ("false".equalsIgnoreCase(value)) {
            return false;
        }
        return defaultValue;
    }

    /**
     * 返回指定键的整数值；如果键值不存在，则返回缺省值，并添加测试用例测试之
     * @param key
     * @param defaultValue
     * @return
     */
    public Integer getInt(String key, int defaultValue) {
        String value = props.getProperty(key);
        if (value != null) {
            return Integer.valueOf(value);
        }
        return defaultValue;
    }

    /**
     * 返回指定键的日期值；如果键值不存在，则返回缺省值
     * @param key
     * @param defaultValue
     * @return
     */
    public Date getDate(String key, Date defaultValue)  {
        String value = props.getProperty(key);
        if (value != null) {
            try {
                return new SimpleDateFormat("yyyy-MM-dd").parse(value);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return defaultValue;
    }
}