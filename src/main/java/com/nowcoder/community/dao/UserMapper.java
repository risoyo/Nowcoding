package com.nowcoder.community.dao;

import com.nowcoder.community.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper //Mybatis的注解，标明了是需要装配的dao层的bean
@Repository
public interface UserMapper {
    //在此处按照之后的业务规则来编写增删改查
    //以根据ID查询用户为例
    User selectById(int id); // 传入一个ID，得到一个用户的数据

    User selectByName(String username);

    User selectByEmail(String email);

    int insertUser(User user);//返回受影响的行数，插入了一行应该affected arrow为1

    int updateStatus(int id, int status);

    int updateHeader(int id, String headerUrl);

    int updatePassword(int id, String password);

}
