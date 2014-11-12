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
import java.util.List;

/**
 * 对于使用xml方式定义的UserMapper.xml，然后直接使用SqlSession访问statement
 * User: jinchao.xu
 * Date: 14-11-6
 * Time: 上午10:55
 */
public class UserMapperTest {

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
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        try {
            User user = new User();
            user.setName("牛蛙");
            user.setAge(11);
            sqlSession.insert("mapper.UserMapper.insertUser", user);
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
            sqlSession.update("mapper.UserMapper.updateUser", user);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testFind() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            User user = sqlSession.selectOne("mapper.UserMapper.getUser", 1);
            System.out.println(user.getId() + "--" + user.getName() + "--" + user.getAge());
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testDelete() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            sqlSession.delete("mapper.UserMapper.deleteUser", 6);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testGetAllUser() {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        try {
            List<User> userList = sqlSession.selectList("mapper.UserMapper.getAllUser");
            System.out.println(userList.toString());

        } finally {
            sqlSession.close();
        }
    }
}