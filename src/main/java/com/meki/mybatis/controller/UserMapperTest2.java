package com.meki.mybatis.controller;

import com.meki.mybatis.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;

/**
 * 对于使用Mapper接口加对应的注解来定义的Mapper信息
 * 直接使用SqlSession访问Mapper接口中使用注解的statement
 * User: jinchao.xu
 * Date: 14-11-6
 * Time: 上午11:52
 */
public class UserMapperTest2 {

    String resource = "conf/mybatis-config.xml";

    SqlSessionFactory sqlSessionFactory = null;

    @Before
    public void before() throws IOException {
        System.out.println("before init sqlsessionfactory");
        Reader reader = Resources.getResourceAsReader(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
    }

    @Test
    public void testInsert() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            User user = new User();
            user.setName("金查尔");
            user.setAge(31);

            sqlSession.insert("com.meki.mybatis.mapperinterface.UserMapper.insertUser", user);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testUpdate() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            User user = new User();
            user.setId(1);
            user.setName("李四");
            user.setAge(34);
            sqlSession.update("com.meki.mybatis.mapperinterface.UserMapper.updateUser", user);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testFind() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            User user = sqlSession.selectOne("com.meki.mybatis.mapperinterface.UserMapper.getUser", 1);
            System.out.println(user.getId() + "--" + user.getName() + "--" + user.getAge());
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testDelete() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            sqlSession.delete("com.meki.mybatis.mapperinterface.UserMapper.deleteUser", 2);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }
}
