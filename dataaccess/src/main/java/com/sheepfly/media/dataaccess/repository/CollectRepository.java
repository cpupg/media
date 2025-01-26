package com.sheepfly.media.dataaccess.repository;

import com.sheepfly.media.dataaccess.entity.Collect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Collect表的mapper。
 *
 * @author chen
 */
public interface CollectRepository extends JpaRepository<Collect, String>, JpaSpecificationExecutor<Collect> {

}




