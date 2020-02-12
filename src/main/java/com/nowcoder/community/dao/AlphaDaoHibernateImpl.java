package com.nowcoder.community.dao;

import org.springframework.stereotype.Repository;

// 这个是Dao接口的实现类
@Repository("alphaHibernate") // 连接数据库的bean，使用@Repository这个注解,后加括号，可以对bean命名
public class AlphaDaoHibernateImpl implements AlphaDao {
    @Override
    public String select() {
        return "Hibernate null";
    }
}
