package com.sheepfly.media.config;

import com.sheepfly.media.entity.baseinterface.EntityInterface;
import com.sheepfly.media.entity.baseinterface.LogicDelete;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.util.HashSet;
import java.util.Set;

@Configuration
@Slf4j
public class LogicDeleteEntity {
    @Resource(name = "entityManagerFactory")
    private EntityManagerFactory factory;

    @Bean
    public Set<Class<? extends EntityInterface>> entityCanBeLogicDelete() {
        Set<Class<? extends EntityInterface>> set = new HashSet<>();
        Metamodel metamodel = factory.getMetamodel();
        Set<EntityType<?>> entities = metamodel.getEntities();
        entities.forEach(s -> {
            Class<?> bindableJavaType = s.getBindableJavaType();
            for (Class<?> itf : bindableJavaType.getInterfaces()) {
                if (itf == LogicDelete.class) {
                    log.info("实体{}允许逻辑删除", itf);
                    set.add((Class<? extends EntityInterface>) bindableJavaType);
                }
            }
        });
        return set;
    }
}
