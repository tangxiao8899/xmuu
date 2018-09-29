package com.util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtil {
    private static final Logger logger = LoggerFactory.getLogger(PropertyUtil.class);
    private static Properties props;
    static{
        loadProps();
    }

    synchronized static private void loadProps(){
        logger.info("开始加载properties文件内容.......");
        props = new Properties();
        InputStream in = null;
        // String prefixOfProp =System.getenv("pre")==null?"":System.getenv("pre");

        try {
            in = PropertyUtil.class.getClassLoader().getResourceAsStream("payment.properties");
            props.load(in);
        } catch (FileNotFoundException e) {
            logger.error("payment.properties文件未找到");
        } catch (IOException e) {
            logger.error("出现IOException");
        } finally {
            try {
                if(null != in) {
                    in.close();
                }
            } catch (IOException e) {
                logger.error("payment.properties文件流关闭出现异常");
            }
        }
        logger.info("加载payment.properties文件内容完成...........");
        logger.info("payment.properties文件内容：" + props);
    }

    //读取key对应的value
    public static String getProperty(String key){
        if(null == props) {
            loadProps();
        }
        return props.getProperty(key);
    }

    public static String getProperty(String key, String defaultValue) {
        if(null == props) {
            loadProps();
        }
        return props.getProperty(key, defaultValue);
    }

    /**
     * 随机数
     * @return
     */
    public static int random() {
        String str = "";
        str += (int) (Math.random() * 9 + 1);
        for (int i = 0; i < 5; i++) {
            str += (int) (Math.random() * 10);
        }
        int num = Integer.parseInt(str);
        return num;
    }

}
