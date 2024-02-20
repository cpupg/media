package com.sheepfly.media.config.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 应用版本。
 *
 * <p>版本号从配置文件中读取，配置文件在打包时由脚本生成，因此开发环境下没有文件，使用默认值
 * 1.0.0。</p>
 *
 * @author wrote-code
 */
@Getter
@Setter
@ToString
public class Version extends Properties {
    private static final String VER = "v1.0.0";
    private String mainVersion = VER;
    private String clientVersion = VER;
    private String serverVersion = VER;

    @Override
    public synchronized void load(InputStream inStream) throws IOException {
        super.load(inStream);
        mainVersion = getProperty("main");
        if (StringUtils.isEmpty(getProperty("server"))) {
            serverVersion = getProperty("server");
        }
        if (StringUtils.isEmpty(getProperty("client"))) {
            clientVersion = getProperty("client");
        }
    }
}
