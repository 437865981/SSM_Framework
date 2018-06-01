package com.fx.service;

import com.fx.entity.TestEntity;

import java.util.List;

public interface TestService {
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
     * @param Id
     */
    public TestEntity queryTestEntityById(Integer Id);

    /**
     * 查（所有）
     */
    public List<TestEntity> queryAllTestEntity();
}
