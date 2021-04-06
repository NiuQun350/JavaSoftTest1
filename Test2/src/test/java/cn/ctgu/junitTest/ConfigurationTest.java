package cn.ctgu.junitTest;


import cn.ctgu.junit.Configuration;
import cn.ctgu.junit.ValueParseException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;


/**
 *
 * 1.补充上面案例缺失的测试用例，尽量考虑提供更多的测试用例；
 * 2.修改案例代码让测试用例通过；
 * 3.实现public Boolean getBooleanWithDefault(String key, Boolean defaultValue)方法，
 *   返回指定键的布尔值；如果值不存在，则返回传入的缺省值，并添加测试用例测试之
 * 4.重构测试代码，以符合DRY（Donot Repeat yourself）原则
 * 5.实现public Integer getInt(String key, int defaultValue)方法，返回指定键的整数值；
 *   如果键值不存在，则返回缺省值，并添加测试用例测试之；
 * 6.实现public Date getDate(String key, Date defaultValue)方法，返回指定键的日期值；
 *   如果键值不存在，则返回缺省值，并添加测试用例测试之；
 */
public class ConfigurationTest {
    @TempDir
    static Path tempDir;

    /**
     * 生成配置
     * @return
     * @throws IOException
     */
    Configuration create_test_file() throws IOException {
        Path path = tempDir.resolve("app.conf");
        Files.write(path, Arrays.asList(
                "ea=false",
                "closeable=true",
                "length=FALSE",
                "open=True",
                "width=1000",
                "size=50",
                "height=900",
                "yesterday=2021-03-25"
        ));
        Configuration configuration = new Configuration();
        configuration.fromFile(path.toFile());
        return configuration;
    }

    /**
     * 测试：当键存在时应该返回键值; 当键值不存在时，则抛出ParseErrorException异常测试
     * @throws IOException
     */
    @Test
    @DisplayName("测试getProp(String key)方法")
    void value_should_exit_when_key_exist() throws IOException {
        Configuration configuration = create_test_file();
        // act
        String[] value = new String[4];
        value[0] = configuration.getProp("ea");
        value[1] = configuration.getProp("closeable");
        value[2] = configuration.getProp("length");
        value[3] = configuration.getProp("open");

        Throwable throwable = assertThrows(ValueParseException.class, () ->{
            configuration.getProp("notexist");
        });

        // assert
        assertEquals("false", value[0]);
        assertEquals("true", value[1]);
        assertEquals("FALSE", value[2]);
        assertEquals("True", value[3]);
        assertAll(
                () -> assertEquals("键值不存在",throwable.getMessage())
        );
    }

    /**
     * 测试：读取指定属性key的布尔类型的值，并返回值; 如果解析出错，则抛出ParserErrorException异常
     * @throws IOException
     */
    @Test
    @DisplayName("测试getBoolean(String key)方法")
    void throw_ValueParseException_when_key_not_exist() throws IOException {
        // arrange
        Configuration conf = create_test_file();
        Boolean[] value= new Boolean[4];
        // act
        value[0] = conf.getBoolean("ea");
        value[1] = conf.getBoolean("closeable");
        value[2] = conf.getBoolean("length");
        value[3] = conf.getBoolean("open");
        Throwable throwable = assertThrows(ValueParseException.class, () ->{
            conf.getBoolean("notexist");
        });
      
        // assert
        assertEquals(false, value[0]);
        assertEquals(true, value[1]);
        assertEquals(false, value[2]);
        assertEquals(true, value[3]);
        assertAll(
                () -> assertEquals("键值不存在",throwable.getMessage())
        );
    }

    /**
     * 返回指定键的布尔值；如果值不存在，则返回传入的缺省值
     * @return
     * @throws IOException
     */
    @Test
    @DisplayName("测试getBooleanWithDefault(String key, Boolean defaultValue)方法")
    public void return_boolean_defaultValue_when_value_not_exist() throws IOException {
        Configuration conf = create_test_file();

        Boolean[] value = new Boolean[5];
        // act
        value[0] = conf.getBooleanWithDefault("ea", true);
        value[1] = conf.getBooleanWithDefault("closeable", true);
        value[2] = conf.getBooleanWithDefault("length", true);
        value[3] = conf.getBooleanWithDefault("open", true);
        value[4] = conf.getBooleanWithDefault("notexist", true);

        // assert
        assertEquals(false, value[0]);
        assertEquals(true, value[1]);
        assertEquals(false, value[2]);
        assertEquals(true, value[3]);
        assertEquals(true, value[4]);

    }

    /**
     * 如果键存在，返回指定键的整数值；如果键不存在，则返回缺省值
     * @throws IOException
     */
    @Test
    @DisplayName("测试getInt(String key, int defaultValue)方法")
    public void return_int_defaultValue_when_value_not_exist() throws IOException {
        Configuration conf = create_test_file();

        Integer[] value = new Integer[4];
        value[0] = conf.getInt("width", 0);
        value[1] = conf.getInt("size", 0);
        value[2] = conf.getInt("height", 0);
        value[3] = conf.getInt("notexist", 0);

        assertEquals(1000,value[0]);
        assertEquals(50, value[1]);
        assertEquals(900, value[2]);
        assertEquals(0, value[3]);
    }

    /**
     * 如果键存在，则返回指定键的日期值；如果键不存在，则返回缺省值
     * @throws IOException
     * @throws ParseException
     */
    @Test
    @DisplayName("测试getDate(String key, Date defaultValue)方法")
    public void return_date_defaultValue() throws IOException, ParseException {
        Configuration conf = create_test_file();

        Date date = conf.getDate("yesterday", new Date(0));
        Date date1 = conf.getDate("tomorrow", new Date(0));

        assertEquals(new SimpleDateFormat("yyyy-MM-dd").parse("2021-03-25"), date);
        assertEquals(new Date(0), date1);
    }


}