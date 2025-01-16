package com.sheepfly.media.dataaccess.repository;

import com.sheepfly.media.dataaccess.entity.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 评分curd仓库。
 *
 * @author chen
 */
public interface RateRepository extends JpaRepository<Rate, String>, JpaSpecificationExecutor<Rate> {
}
