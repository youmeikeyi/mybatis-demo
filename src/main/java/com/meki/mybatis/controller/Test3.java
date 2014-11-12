package com.meki.mybatis.controller;

import com.alibaba.fastjson.JSONObject;
import com.meki.mybatis.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

/**
 * User: jinchao.xu
 * Date: 14-11-5
 * Time: 下午4:47
 */
public class Test3 {

    public static void main(String[] args) throws IOException {

        String resource = "conf/mybatis-config.xml";
        //load mybatis config file
        Reader reader = Resources.getResourceAsReader(resource);

        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);

        SqlSession session = sessionFactory.openSession();
        // map sql string
        String statement = "com.meki.mybatis.mapperinterface.UserMapper"+".getUser";
        //execute query and return a unique user object
        User user = session.selectOne(statement, 1);
        System.out.println(user);
    }

    public void loadResource(String resource){
        InputStream is = Test3.class.getClassLoader().getResourceAsStream(resource);
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);

    }

}
