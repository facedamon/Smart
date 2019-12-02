package com.facedamon.smart.quartz.task;

import org.springframework.stereotype.Component;

/**
 * @Description: 测试定时任务
 * @Author: facedamon
 * @CreateDate: 2018/11/27 14:01
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/11/27 14:01
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Component("testTask")
public class TestTask {

    public void yesParams(String params) {
        System.out.println("执行带参数方法:" + params);
    }

    public void noParams() {
        System.out.println("执行无参方法");
    }
}
