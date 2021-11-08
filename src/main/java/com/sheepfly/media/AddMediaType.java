package com.sheepfly.media;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sheepfly.media.entity.MediaType;
import com.sheepfly.media.mapper.MediaTypeMapper;
import com.sheepfly.media.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class AddMediaType {
    public static void main(String[] args) {
        MybatisUtil.init();
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        MediaTypeMapper mediaTypeMapper = sqlSession.getMapper(MediaTypeMapper.class);
        String typeDescription = "init";
        String typeCode;
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.ssssss");
        QueryWrapper<MediaType> queryWrapper = new QueryWrapper<>();
        while (!"exit".equals(typeDescription)) {
            System.out.print("请输入类型：");
            typeDescription = scanner.nextLine();
            if (typeDescription.length() == 0) {
                System.out.println("请重新输入");
                continue;
            }
            if (typeDescription.equals("exit")) {
                break;
            }
            if ("delete".equals(typeDescription)) {
                System.out.print("请输入要删除的类型:");
                typeDescription = scanner.nextLine();
                try {
                    queryWrapper.eq("TYPE_DESCRIPTION", typeDescription);
                    int deleteCount = mediaTypeMapper.delete(queryWrapper);
                    sqlSession.commit();
                    System.out.println("删除数目:" + deleteCount);
                    queryWrapper.clear();
                } catch (Exception e) {
                    e.printStackTrace();
                    continue;
                }
            }
            typeCode = Integer.toHexString(typeDescription.hashCode());
            MediaType mediaType = new MediaType();
            mediaType.setCreateTime(format.format(new Date()));
            mediaType.setUpdateTime(mediaType.getCreateTime());
            mediaType.setStatus(1);
            mediaType.setTypeDescription(typeDescription);
            mediaType.setTypeCode(typeCode);
            try {
                mediaTypeMapper.insert(mediaType);
                sqlSession.commit();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.print("发生异常，请重新输入：");
            }
        }
        sqlSession.close();
    }
}
