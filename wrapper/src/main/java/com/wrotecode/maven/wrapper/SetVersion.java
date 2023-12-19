package com.wrotecode.maven.wrapper;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class SetVersion {
    public static void main(String[] args) throws Exception {
        StringBuilder builder = new StringBuilder();
        for (String e : args) {
            builder.append(e).append(" ");
        }
        System.out.println("运行参数:" + builder);
        if (args.length != 3) {
            throw new Exception("参数错误，必须输入pom.xml全路径和新版本号");
        }
        String path = args[0];
        String version = args[1];
        String type = args[2];
        SAXReader saxReader = new SAXReader();
        InputStream is = new FileInputStream(path);
        InputStreamReader isr = new InputStreamReader(is, "utf-8");
        Document doc = saxReader.read(isr);
        Element rootElement = doc.getRootElement();
        Element versionElement;
        Element versionProperty = null;
        if ("current".equals(type)) {
            System.out.println("修改当前项目版本号");
            versionElement = rootElement.element("version");
            versionProperty = rootElement.element("properties").element("version.media");
        } else if ("parent".equals(type)) {
            System.out.println("修改父项目版本号");
            versionElement = rootElement.element("parent").element("version");
        } else {
            throw new Exception("参数错误，必须是current或parent，当前参数：" + type);
        }
        String oldVersion = versionElement.getText();
        System.out.printf("老版本号:%s,新版本号:%s%s", oldVersion, version, System.lineSeparator());
        if (versionProperty != null) {
            System.out.println("设置版本属性");
            versionProperty.setText(version);
        }
        versionElement.setText(version);
        is.close();
        OutputStream os = new FileOutputStream(path);
        OutputStreamWriter osw = new OutputStreamWriter(os, "utf-8");
        doc.write(osw);
        osw.write("\r\n");
        osw.flush();
        osw.close();
    }

}
