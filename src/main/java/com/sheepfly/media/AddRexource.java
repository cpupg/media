package com.sheepfly.media;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sheepfly.accountbox.constant.SystemConstant;
import com.sheepfly.media.entity.MediaType;
import com.sheepfly.media.entity.ResourceType;
import com.sheepfly.media.entity.Resources;
import com.sheepfly.media.mapper.MediaTypeMapper;
import com.sheepfly.media.mapper.ResourceTypeMapper;
import com.sheepfly.media.mapper.ResourcesMapper;
import com.sheepfly.media.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class AddRexource {
    private static final Logger log = LoggerFactory.getLogger(AddRexource.class);
    private static final String ROOT = "F:\\Games\\steamapps\\common\\Cities_Skylines\\Tools\\LocalUser\\FtpUser\\temp";
    private static Scanner scanner = new Scanner(System.in);
    private static SqlSession sqlSession;
    private static String input = "input";

    public static void main(String[] args) throws Exception {
        sqlSession = MybatisUtil.getSqlSession();
        while (!"exit".equals(input)) {
            addResource();
        }
        sqlSession.close();
    }

    public static void addResource() throws Exception {
        ResourcesMapper resourceMapper = sqlSession.getMapper(ResourcesMapper.class);
        ResourceTypeMapper resourceTypeMapper = sqlSession.getMapper(ResourceTypeMapper.class);
        Resources resources = new Resources();
        resources.setAuthor("000");
        resources.setScore(0);
        resources.setCreateTime(getTime());
        resources.setUpdateTime(getTime());
        log.info("输入资源名称:");
        resources.setFileName(scanner.nextLine());
        log.info("输入资源目录");
        input = scanner.nextLine();
        File file = new File(input);
        resources.setFileDir(file.getAbsolutePath().replace(ROOT, ""));
        QueryWrapper<Resources> resourceQW = new QueryWrapper<>();
        resourceQW.like("FILE_NAME", resources.getFileName());
        QueryWrapper<ResourceType> resourceTypeQW = new QueryWrapper<>();
        List<Resources> resourceList = resourceMapper.selectList(resourceQW);
        int deleted = 0;
        if (resourceList.size() > 0) {
            log.info("已经插入过相同资源，将删除这些资源");
            for (Resources ele : resourceList) {
                resourceTypeQW.eq("RESOURCE_ID", ele.getId());
                deleted = resourceTypeMapper.delete(resourceTypeQW);
                log.info("删除分类数量:" + deleted);
                resourceQW.clear();
                resourceQW.eq("ID", ele.getId());
                deleted = resourceMapper.delete(resourceQW);
                log.info("删除资源数量:" + deleted);
            }
            sqlSession.commit();
        }
        int result = resourceMapper.insert(resources);
        sqlSession.commit();
        log.info("插入资源:" + result);
        log.info("开始分类");
        addResourceType(resources);
    }

    public static void addResourceType(Resources resources) {
        ResourceTypeMapper resourceTypeMapper = sqlSession.getMapper(ResourceTypeMapper.class);
        MediaTypeMapper mediaTypeMapper = sqlSession.getMapper(MediaTypeMapper.class);
        ResourceType resourceType = new ResourceType();
        resourceType.setCreateTime(getTime());
        resourceType.setUptateTime(getTime());
        resourceType.setResourceId(resources.getId());
        log.info("请输入分类，用逗号分隔:");
        input = scanner.nextLine();
        String[] typeArray = input.split(",");
        List<String> list = new ArrayList<>();
        QueryWrapper<MediaType> queryWrapper = new QueryWrapper<>();
        for (String ele : typeArray) {
            queryWrapper.clear();
            queryWrapper.eq("TYPE_DESCRIPTION", ele);
            MediaType mediaType = mediaTypeMapper.selectOne(queryWrapper);
            if (mediaType == null) {
                log.info("未知类型:" + ele);
                list.add(ele);
                queryWrapper.clear();
            } else {
                resourceType.setTypeCode(mediaType.getTypeCode());
                resourceTypeMapper.insert(resourceType);
                sqlSession.commit();
                log.info("插入分类:" + resourceType);
            }
        }
    }

    public static String getTime() {
        return SystemConstant.FORMAT.format(new Date());
    }
}
