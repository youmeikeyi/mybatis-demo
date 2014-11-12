package com.meki.mybatis.controller;

import com.meki.mybatis.mapperinterface.UserMapper;
import com.meki.mybatis.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 * 对于使用Mapper接口加注解定义好的Mapper信息
 * 通过SqlSession获取其对应的Mapper接口来操作其中定义好的statement的测试
 * User: jinchao.xu
 * Date: 14-11-6
 * Time: 上午11:52
 */
public class UserMapperTest3 {

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
            user.setName("中文来吧");
            user.setAge(31);

            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            userMapper.insertUser(user);
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
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            userMapper.updateUser(user);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testFind() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = userMapper.getUser(1);

            System.out.println(user.getId() + "--" + user.getName() + "--" + user.getAge());
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testDelete() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            userMapper.deleteUser(2);

            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testGetAllUser() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<User> userList = userMapper.getAllUser();
            System.out.println(userList.toString());
        } finally {
            sqlSession.close();
        }
    }
}
