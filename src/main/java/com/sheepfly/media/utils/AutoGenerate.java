package com.sheepfly.media.utils;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

import java.util.Collections;
import java.util.Scanner;

public class AutoGenerate {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入表名：");
        String tableName = scanner.nextLine();
        FastAutoGenerator.create("jdbc:sqlite:database.sqlite", null, null)
                .globalConfig(builder -> builder.outputDir("src\\main\\java")
                        .author("sheepfly")
                        .disableOpenDir()
                        .fileOverride())
                .packageConfig(builder -> builder.mapper("mappers")
                        .parent("com.sheepfly.media")
                        .service("services")
                        .serviceImpl("services.impl")
                        .entity("entities")
                        .pathInfo(Collections.singletonMap(OutputFile.mapperXml,
                                "src\\main\\resources\\com\\sheepfly\\media\\mappers"))
                )
                .strategyConfig(builder -> builder.addInclude(tableName.toUpperCase())
                        .entityBuilder().enableTableFieldAnnotation())
                .templateEngine(new VelocityTemplateEngine())
                .execute();
    }
}
