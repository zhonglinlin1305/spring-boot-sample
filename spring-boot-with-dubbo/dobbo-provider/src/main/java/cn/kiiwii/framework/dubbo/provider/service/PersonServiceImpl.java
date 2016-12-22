package cn.kiiwii.framework.dubbo.provider.service;

import cn.kiiwii.framework.dubbo.api.IPerson;
import cn.kiiwii.framework.dubbo.provider.dao.ITestDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by zhong on 2016/11/22.
 */
@Component
@com.alibaba.dubbo.config.annotation.Service(version="1.0.0")
public class PersonServiceImpl implements IPerson {

    @Autowired
    ITestDAO testDAO;
    @Override
    public String getFullName(String name) {

        return "getFullName:"+this.testDAO.test().toString();
    }

    @Override
    public String getNickName(int id) {
        return "getNickName:"+this.testDAO.test().toString();
    }
}
