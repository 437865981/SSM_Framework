package com.fx.service.impl;

import com.fx.dao.TestDao;
import com.fx.entity.TestEntity;
import com.fx.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestDao testDao;

    @Override
    public void addTestEntity(TestEntity testEntity) {
        this.testDao.addTestEntity(testEntity);
    }

    @Override
    public void delTestEntityById(Integer id) {
        this.testDao.delTestEntityById(id);
    }

    @Override
    public void modifyTestEntity(TestEntity testEntity) {
        this.testDao.modifyTestEntity(testEntity);
    }

    @Override
    public TestEntity queryTestEntityById(Integer id) {
        return this.testDao.queryTestEntityById(id);
    }

    @Override
    public List<TestEntity> queryAllTestEntity() {
        return this.testDao.queryAllTestEntity();
    }
}
