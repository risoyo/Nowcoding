package com.nowcoder.community.service;

import com.nowcoder.community.dao.AlphaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
public class AlphaService {
    private final AlphaDao alphaDao;

    @Autowired
    public AlphaService(AlphaDao alphaDao) {
        this.alphaDao = alphaDao;
        System.out.println("实例化AplaService");
    }

    @PostConstruct // 此方法会在构造器之后调用，适用于初始化方法
    public void init() {
        System.out.println("Service bean init");
    }

    @PreDestroy  // 此方法会在调用销毁之前调用，可以释放一些资源
    public void destroy() {
        System.out.println("destroy AlphaService");
    }

    public String find() {
        return alphaDao.select();
    }
}
