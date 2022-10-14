package com.sheepfly.media.repository;

import com.sheepfly.media.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 作者仓库。
 *
 * @author 东方红
 */
public interface AuthorRepository extends JpaRepository<Author, String>, JpaSpecificationExecutor<Author> {
}
