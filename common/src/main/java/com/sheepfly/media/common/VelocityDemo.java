package com.sheepfly.media.common;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.FileWriter;
import java.util.Properties;

public class VelocityDemo {
    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        properties.load(VelocityDemo.class.getClassLoader().getResourceAsStream("velocity.properties"));
        Velocity.init(properties);
        VelocityContext context = new VelocityContext();
        context.put("msg", "world");
        Template template = Velocity.getTemplate("hello.vm");
        FileWriter fileWriter = new FileWriter("hello.txt");
        template.merge(context, fileWriter);
        fileWriter.flush();
        fileWriter.close();
    }
}
