package com.meki.mybatis.mapperinterface;

import com.meki.mybatis.model.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * User: jinchao.xu
 * Date: 14-11-6
 * Time: 上午10:36
 */
public interface UserMapper {

    @Insert("insert into users(name, age) values(#{name}, #{age})")
    public void insertUser(User user);

    @Update("update users set name=#{name}, age=#{age} where id=#{id}")
    public void updateUser(User user);

    @Select("select * from users where id=#{id}")
    public User getUser(int id);

    @Delete("delete from users where id=#{id}")
    public void deleteUser(int id);

    @Select("select * from users limit 10")
    public List<User> getAllUser();
}
