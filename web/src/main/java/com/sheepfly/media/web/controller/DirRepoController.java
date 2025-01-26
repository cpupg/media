package com.sheepfly.media.web.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.page.PageMethod;
import com.sheepfly.media.common.form.data.DirRepoData;
import com.sheepfly.media.common.form.param.DirRepoParam;
import com.sheepfly.media.common.http.ResponseData;
import com.sheepfly.media.common.http.TableResponse;
import com.sheepfly.media.common.vo.DirRepoVo;
import com.sheepfly.media.dataaccess.entity.DirRepo;
import com.sheepfly.media.service.base.DirRepoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/dirRepo")
public class DirRepoController {
    @Autowired
    private DirRepoService service;

    /**
     * 保存一个目录仓库。
     *
     * @param data 表单。
     *
     * @return 保存成功的数据。
     */
    @PostMapping("/saveDirRepo")
    public ResponseData<DirRepo> saveDirRepo(@Validated @RequestBody DirRepoData data) {
        DirRepo dirRepo = new DirRepo();
        BeanUtils.copyProperties(data, dirRepo);
        dirRepo.setDirCode(service.createDirCode());
        dirRepo.setCreateTime(new Date());
        DirRepo saved = service.save(dirRepo);
        return ResponseData.success(saved);
    }

    /**
     * 查询目录仓库清单。
     *
     * @return 符合条件的清单。
     */
    @PostMapping("queryAllDirRepoList")
    public TableResponse<DirRepoVo> queryAllDirRepoList(@Validated @RequestBody DirRepoParam filter) {
        Page<Object> page = PageMethod.startPage(filter.getCurrent(), filter.getPageSize());
        List<DirRepoVo> list = service.queryAllDirRepoList();
        return TableResponse.success(list, page.getTotal());
    }
}
