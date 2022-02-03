package com.sheepfly.media;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class AutoGenerate {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入表名，使用逗号分隔：");
        String tableName = scanner.nextLine().toUpperCase();
        List<String> tableList = Arrays.asList(tableName.split(","));
        FastAutoGenerator.create("jdbc:sqlite:database.sqlite", null, null)
                .globalConfig(builder -> builder.outputDir("src\\main\\java")
                        .author("sheepfly")
                        .disableOpenDir()
                        .fileOverride())
                .packageConfig(builder -> builder.parent("com.sheepfly.media")
                        .mapper("dao")
                        .service("service")
                        .serviceImpl("service.impl")
                        .entity("entity")
                        .pathInfo(Collections.singletonMap(OutputFile.mapperXml,
                                "src\\main\\resources\\configs\\mappers\\com\\sheepfly\\media\\mapper"))
                )
                .strategyConfig(builder -> builder.addInclude(tableList)
                        .entityBuilder().enableTableFieldAnnotation())
                .templateEngine(new VelocityTemplateEngine())
                .execute();
    }
}
