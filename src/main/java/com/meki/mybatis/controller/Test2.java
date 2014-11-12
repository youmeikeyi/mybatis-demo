package com.meki.mybatis.controller;

import com.meki.mybatis.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;

/**
 * User: jinchao.xu
 * Date: 14-11-5
 * Time: 下午5:49
 */
public class Test2 {


    @Test
    public void testAdd() throws IOException {
        String resource = "conf/mybatis-config.xml";
        //load mybatis config file
        Reader reader = Resources.getResourceAsReader(resource);

        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);

        SqlSession session = sessionFactory.openSession();

        String statement = "com.meki.mybatis.mapperinterface.UserMapper.insertUser";
        //has error with id -1
//        session.insert(statement, new User(-1, "KK", 32));
    }
}
