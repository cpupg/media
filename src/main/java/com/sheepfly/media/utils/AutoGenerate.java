package com.sheepfly.media.utils;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

import java.io.PrintWriter;
import java.util.Collections;
import java.util.Scanner;

public class AutoGenerate {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PrintWriter printWriter = new PrintWriter(System.out);
        printWriter.write("输入表名");
        String tableName = scanner.nextLine();
        FastAutoGenerator.create("jdbc:sqlite:database.sqlite", null, null)
                .globalConfig(builder -> builder.outputDir("src\\main\\java")
                        .author("sheepfly")
                        .disableOpenDir()
                        .fileOverride())
                .packageConfig(builder -> builder.mapper("com.sheepfly.media.daos")
                        .parent("")
                        .entity("com.sheepfly.media.entities")
                        .pathInfo(Collections.singletonMap(OutputFile.mapperXml,
                                "src\\main\\resources\\com\\sheepfly\\media\\daos"))
                )
                .strategyConfig(builder -> builder.addInclude(tableName.toUpperCase()))
                .templateEngine(new VelocityTemplateEngine())
                .execute();
    }
}
