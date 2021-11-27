package com.sheepfly.media;

import com.sheepfly.media.entity.Author;
import com.sheepfly.media.entity.Source;
import com.sheepfly.media.mapper.AuthorMapper;
import com.sheepfly.media.mapper.SourceMapper;
import com.sheepfly.media.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AddAuthor {
    private static final Logger log = LoggerFactory.getLogger(AddAuthor.class);

    public static void main(String[] args) {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        AuthorMapper authorMapper = sqlSession.getMapper(AuthorMapper.class);
        SourceMapper sourceMapper = sqlSession.getMapper(SourceMapper.class);
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd HH:mm:ss.ssssss");
        Scanner scanner = new Scanner(System.in);
        Map<String, String> sourceMap = new HashMap<>();
        List<Source> sourceList = sourceMapper.selectList(null);
        sourceList.forEach(ele -> sourceMap.put(ele.getSite(), ele.getId()));
        int result;
        while (true) {
            Author author = new Author();
            author.setCreateTime(format.format(new Date()));
            author.setUpdateTime(format.format(new Date()));
            log.info("请输入作者id");
            author.setUserId(scanner.nextLine());
            log.info("请输入用户名");
            author.setUserName(scanner.nextLine());
            if (author.getUserId().length() == 0 || author.getUserName().length() == 0) {
                log.warn("输入无效");
                continue;
            }
            log.info("请输入来源");
            author.setSource(sourceMap.get(scanner.nextLine()));
            if (author.getSource() == null) {
                log.error("来源不存在");
                continue;
            }
            try {
                result = authorMapper.insert(author);
                sqlSession.commit();
                log.info("插入数量:" + result);
            } catch (Exception e) {
                log.error("插入失败", e);
            }
        }
    }
}
