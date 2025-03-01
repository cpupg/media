package com.sheepfly.media.config.bean;

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
public class Version extends Properties {
    private static final String VER = "v1.0.0";
    private String mainVersion = VER;
    private String clientVersion = VER;
    private String serverVersion = VER;

    @Override
    public synchronized void load(InputStream inStream) throws IOException {
        super.load(inStream);
        mainVersion = getProperty("main");
        String vs = getProperty("server");
        String vc = getProperty("client");
        if (!(StringUtils.isEmpty(vs) || "v".equals(vs))) {
            serverVersion = vs;
        }
        if (!(StringUtils.isEmpty(vc) || "v".equals(vc))) {
            clientVersion = vc;
        }
    }

    public String getMainVersion() {
        return this.mainVersion;
    }

    public void setMainVersion(String mainVersion) {
        this.mainVersion = mainVersion;
    }

    public String getClientVersion() {
        return this.clientVersion;
    }

    public void setClientVersion(String clientVersion) {
        this.clientVersion = clientVersion;
    }

    public String getServerVersion() {
        return this.serverVersion;
    }

    public void setServerVersion(String serverVersion) {
        this.serverVersion = serverVersion;
    }
}
