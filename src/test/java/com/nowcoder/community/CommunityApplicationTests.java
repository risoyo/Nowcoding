package com.nowcoder.community;

import com.nowcoder.community.dao.AlphaDao;
import com.nowcoder.community.service.AlphaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)  // 指名运行的测试代码的配置类
public class CommunityApplicationTests implements ApplicationContextAware {
    private ApplicationContext applicationContext;
    @Autowired // 此注解标明是注入
    @Qualifier("alphaHibernate")  // 指定注入的bean的名字
    private AlphaDao alphaDao;
    @Autowired
    private SimpleDateFormat simpleDateFormat;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    // 当一个类实现了ApplicationContextAware的setApplicationContext方法时，Spring扫描后会把自身存储进来
    //
    @Test
    public void testApplicationContext() {
        System.out.println(applicationContext);
        AlphaDao alphaDao = applicationContext.getBean(AlphaDao.class); // 在容器中获取AlphaDao.class这个类型的bean
        // 但是在当前接口有多个实现类的时候，就会有两个满足条件的bean
        System.out.println(alphaDao.select());
        alphaDao = applicationContext.getBean("alphaHibernate", AlphaDao.class);// 为bean命名之后，可以用此方法调用指定的bean
        System.out.println(alphaDao.select());
    }

    @Test
    public void testDestroy() {
        AlphaService alphaService = applicationContext.getBean(AlphaService.class);
        System.out.println(alphaService);
        alphaService = applicationContext.getBean(AlphaService.class);
        System.out.println(alphaService);
    }

    @Test
    public void testData() {
        SimpleDateFormat simpleDateFormat = applicationContext.getBean(SimpleDateFormat.class);
        System.out.println(simpleDateFormat.format(new Date()));
    }

    @Test
    public void testDI() {
//		System.out.println(alphaDao.select());
        System.out.println(simpleDateFormat.format(new Date()));
    }
}
