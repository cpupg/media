package com.sheepfly.media.cli.util;

import com.sheepfly.media.common.exception.CommonException;
import com.sheepfly.media.dataaccess.entity.Directory;
import com.sheepfly.media.dataaccess.entity.Directory_;
import com.sheepfly.media.dataaccess.repository.DirectoryRepository;
import com.sheepfly.media.service.base.DirectoryService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 目录缓存。
 *
 * <p>提供目录缓存服务，只能用于单线程环境，在cli环境下提升操作效率。</p>
 *
 * @author wrote-code
 */
@Component
public class DirectoryCache {
    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(DirectoryCache.class);
    @Autowired
    private DirectoryRepository repository;
    @Autowired
    private DirectoryService service;
    /**
     * 目录缓存。
     */
    private Map<String, Directory> directoryMap;

    public DirectoryCache() {
        directoryMap = new ConcurrentHashMap<>();
    }

    /**
     * 返回指定目录。
     *
     * <p>先从缓存中读取，然后从数据库中读取，若都没有，返回null。</p>
     *
     * @param path 目录路径。
     *
     * @return 路径对应的目录对象。
     */
    public Directory get(String path) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.info("从缓存中获取目录:{}", path);
        }
        if (directoryMap.containsKey(path)) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.info("命中缓存:{}", path);
            }
            return directoryMap.get(path);
        }
        Optional<Directory> one = repository.findOne((r, q, b) -> b.equal(r.get(Directory_.PATH), path));
        if (one.isPresent()) {
            put(path, one.orElse(null));
            return one.orElse(null);
        }
        LOGGER.warn("数据库中不存在指定目录{}", path);
        return null;
    }

    public void put(String path, Directory directory) {
        directoryMap.put(path, directory);
    }

    /**
     * 获取或创建目录。
     *
     * <p>先从缓存和数据库中获取目录对象，如果没有获取到，就创建一个新对象并返回，同时加入
     * 缓存。一个目录对象最多可以在缓存中对应两个key，一个key是原始路径，一个key是格式化后
     * 的路径。格式化是指将盘符大写，使用/分隔符，同时目录以/结尾。</p>
     *
     * @param dir 目录路径。
     *
     * @return 路径对应的目录对象。
     *
     * @throws CommonException 异常。
     */
    public Directory getOrCreateDirectory(String dir) throws CommonException {
        String rawDir = dir;
        Directory directory = get(rawDir);
        if (directory != null) {
            return directory;
        }
        // 盘符大写
        dir = dir.substring(0, 1).toUpperCase() + dir.substring(1);
        directory = get(dir);
        if (directory != null) {
            put(rawDir, directory);
            return directory;
        }
        directory = service.createDirectory(dir);
        if (directory == null) {
            throw new CommonException("创建目录失败:" + dir);
        }
        LOGGER.info("目录创建完成，将目录{}和{}加入缓存", rawDir, dir);
        put(rawDir, directory);
        put(dir, directory);
        return directory;
    }
}
