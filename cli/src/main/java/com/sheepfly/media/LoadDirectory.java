package com.sheepfly.media;

import com.sheepfly.media.config.LoadDirectoryConfig;
import com.sheepfly.media.task.Task;
import com.sheepfly.media.util.CommandLineUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:configs/springboot.xml")
@Slf4j
public class LoadDirectory {
    private static final String LOAD_DIRECTORY = "loadDirectory";

    public static void main(String[] args) throws Exception {
        log.info("解析程序参数");
        CommandLine commandLine = parseArgs(args);
        if (commandLine == null) {
            return;
        }
        log.info("配置任务");
        LoadDirectoryConfig config = new LoadDirectoryConfig();
        config(config, commandLine);

        log.info("初始化spring上下文");
        SpringApplication springApplication = new SpringApplication(LoadDirectory.class);
        springApplication.setWebApplicationType(WebApplicationType.NONE);
        ConfigurableApplicationContext context = springApplication.run(args);
        log.info("初始化完成,获取任务");
        Task task = context.getBean("loadDirectoryTaskImpl", Task.class);
        task.setTaskConfig(config);
        task.initializeTaskConfig();
        if (!task.ready()) {
            return;
        }
        log.info("执行任务");
        task.executeTask();
        task.getExecuteResult();
        task.afterTaskFinish();
        log.info("任务完成");
    }

    private static CommandLine parseArgs(String[] args) {
        // 要扫描的目录
        Option targetDirOption = new Option("t", "targetDir", true, "要扫描的目录");
        targetDirOption.setRequired(true);
        // 作者名称
        Option author = new Option("a", "author", true, "作者名称");
        // 作者主键，当有重名作者时需要传入此项。
        Option authorId = new Option("ai", "authorId", true, "作者唯一标识");

        Options options = new Options();
        options.addOption(author);
        options.addOption(targetDirOption);
        options.addOption(authorId);
        CommandLineParser parser = new DefaultParser();
        CommandLine cli;
        try {
            cli = parser.parse(options, args);
        } catch (ParseException e) {
            // 不需要堆栈
            CommandLineUtil.printHelp(options, LOAD_DIRECTORY);
            return null;
        }
        return cli;
    }

    private static LoadDirectoryConfig config(LoadDirectoryConfig config, CommandLine cli) {
        config.setTargetDir(cli.getOptionValue("targetDir"));
        config.setAuthorName(cli.getOptionValue("author"));
        config.setAuthorId(cli.getOptionValue("ai"));
        if (StringUtils.isEmpty(config.getAuthorName())) {
            config.setAuthorName("默认作者");
        }
        return config;
    }


}
