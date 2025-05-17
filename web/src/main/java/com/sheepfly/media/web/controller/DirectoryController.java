package com.sheepfly.media.web.controller;

import com.sheepfly.media.common.http.ResponseData;
import com.sheepfly.media.common.vo.DirectoryVo;
import com.sheepfly.media.service.base.DirectoryService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 资源目录。
 *
 * @author wrote-code
 */
@RestController
@RequestMapping("/directory")
public class DirectoryController {
    @Resource
    private DirectoryService directoryService;

    /**
     * 查询子目录。
     *
     * @return 根目录和根目录的子目录。
     */
    @PostMapping("/queryRootDirectory")
    public ResponseData<List<DirectoryVo>> querySubDirectoryList(long parentDirCode) {
        List<DirectoryVo> list = directoryService.queryDirectoryList();
        return ResponseData.success(list);
    }
}
