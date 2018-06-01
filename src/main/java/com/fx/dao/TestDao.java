package com.fx.dao;

import com.fx.entity.TestEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TestDao {
    /**
     * 增
     * @param testEntity
     */
    public void addTestEntity(TestEntity testEntity);

    /**
     * 删
     * @param id
     */
    public void delTestEntityById(Integer id);

    /**
     * 改
     * @param testEntity
     */
    public void modifyTestEntity(TestEntity testEntity);

    /**
     * 查（id）
     * @param id
     */
    public TestEntity queryTestEntityById(Integer id);

    /**
     * 查（所有）
     */
    public List<TestEntity> queryAllTestEntity();
}
