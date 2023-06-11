package com.sheepfly.media.cli.util;

import cn.hutool.core.io.resource.ResourceUtil;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 命令行工具。
 *
 * @author wrote-code
 */
public class CommandLineUtil {
    private CommandLineUtil() {
    }

    /**
     * 输出命令命令语法。
     *
     * @param options 参数。
     * @param syntaxKey 语法对应的key。
     */
    public static void printHelp(Options options, String syntaxKey) {
        HelpFormatter formatter = new HelpFormatter();
        InputStream is = ResourceUtil.getStream("config/commandline.properties");
        Properties properties = new Properties();
        try {
            properties.load(is);
            formatter.printHelp(properties.getProperty(syntaxKey), options);
        } catch (IOException e) {
            // 不需要异常堆栈
        }
    }
}
