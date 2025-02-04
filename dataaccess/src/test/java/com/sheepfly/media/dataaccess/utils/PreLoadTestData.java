package com.sheepfly.media.dataaccess.utils;

import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.text.csv.CsvReadConfig;
import cn.hutool.core.text.csv.CsvReader;
import com.sheepfly.media.dataaccess.DataAccessTestConfiguration;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * 预加载单元测试需要的数据。
 *
 * @author chen
 */
@SpringBootTest(classes = DataAccessTestConfiguration.class)
@RunWith(SpringRunner.class)
public class PreLoadTestData {
    private static final CsvReadConfig CSV_READ_CONFIG = new CsvReadConfig();
    private static final Logger LOGGER = LoggerFactory.getLogger(PreLoadTestData.class);

    static {
        CSV_READ_CONFIG.setContainsHeader(true);
        CSV_READ_CONFIG.setErrorOnDifferentFieldCount(true);
    }

    /**
     * 加载csv数据，调用saveAll方法入库。
     *
     * <p>此方法不带事务，需要手动在测试过程中加事务。</p>
     *
     * @param file 文件名。
     * @param repository 入库用的仓库。
     * @param c csv对应的实体类。
     * @param <T> 实体类泛型。
     */
    public static <T> void loadCsv(String file, JpaRepository repository, Class<T> c) {
        LOGGER.info("加载测试数据{}", file);
        CsvReader csvReader = new CsvReader(CSV_READ_CONFIG);
        List<T> list = csvReader.read(ResourceUtil.getUtf8Reader(file), c);
        List list2 = repository.saveAllAndFlush(list);
        LOGGER.info("加载完成{}", list2.size());
    }

}
