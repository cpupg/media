package com.sheepfly.media.web.controller;

import com.sheepfly.media.common.http.ResponseData;
import com.sheepfly.media.common.vo.DirectoryVo;
import com.sheepfly.media.service.base.DirectoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 资源目录。
 *
 * @author wrote-code
 */
@RestController
@RequestMapping("/directory")
public class DirectoryController {
    @Autowired
    private DirectoryService service;

    /**
     * 查询子目录。
     *
     * @return 根目录和根目录的子目录。
     */
    @PostMapping("/queryRootDirectory")
    public ResponseData<DirectoryVo> querySubDirectoryList(long parentDirCode) {
        List<DirectoryVo> list = service.queryDirectoryList();
        return ResponseData.success(list);
    }
}
