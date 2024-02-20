package com.sheepfly.media.dataaccess.mapper;

import com.sheepfly.media.dataaccess.vo.file.FileInfo;

import java.util.List;

public interface FileMapper {
    List<FileInfo> queryFileList(String businessCode);
}
