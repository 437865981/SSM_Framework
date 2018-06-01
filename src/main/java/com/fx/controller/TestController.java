package com.fx.controller;

import com.fx.entity.TestEntity;
import com.fx.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    /**
     * 增
     * @param testEntity
     */
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    @ResponseBody
    public void addTestEntity(TestEntity testEntity){
        this.testService.addTestEntity(testEntity);
    }

    /**
     * 删
     * @param id
     */
    @RequestMapping(value = "/deleteById",method = RequestMethod.GET)
    @ResponseBody
    public void delTestEntityById(Integer id){
        this.testService.delTestEntityById(id);
    }

    /**
     * 改
     * @param testEntity
     */
    @RequestMapping(value = "/modify",method = RequestMethod.GET)
    @ResponseBody
    public void modifyTestEntity(TestEntity testEntity){
        this.testService.modifyTestEntity(testEntity);
    }

    /**
     * 查（id）
     * @param id
     */
    @RequestMapping(value = "/queryById",method = RequestMethod.GET)
    @ResponseBody
    public TestEntity queryTestEntityById(Integer id){
        return this.testService.queryTestEntityById(id);
    }

    /**
     * 查（所有）
     */
    @RequestMapping(value = "/queryAll",method = RequestMethod.GET)
    @ResponseBody
    public List<TestEntity> queryAllTestEntity(){
        return this.testService.queryAllTestEntity();
    }
}
