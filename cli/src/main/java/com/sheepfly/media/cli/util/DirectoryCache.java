package com.sheepfly.media.cli.util;

import com.sheepfly.media.dataaccess.entity.Directory;
import com.sheepfly.media.dataaccess.entity.Directory_;
import com.sheepfly.media.dataaccess.repository.DirectoryRepository;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class DirectoryCache {
    @Autowired
    private DirectoryRepository repository;
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
        if (log.isDebugEnabled()) {
            log.info("从缓存中获取目录:{}", path);
        }
        if (directoryMap.containsKey(path)) {
            if (log.isDebugEnabled()) {
                log.info("命中缓存:{}", path);
            }
            return directoryMap.get(path);
        }
        Optional<Directory> one = repository.findOne((r, q, b) -> b.equal(r.get(Directory_.PATH), path));
        if (one.isPresent()) {
            put(path, one.orElse(null));
            return one.orElse(null);
        }
        log.warn("数据库中不存在指定目录{}", path);
        return null;
    }

    public void put(String path, Directory directory) {
        directoryMap.put(path, directory);
    }
}
