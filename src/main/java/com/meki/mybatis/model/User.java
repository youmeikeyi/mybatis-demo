package com.meki.mybatis.model;

/**
 * User: jinchao.xu
 * Date: 14-11-5
 * Time: 下午4:22
 */
public class User {
    private int id;
    private String name;
    private int age;

    public User(){}

    public User(int id, String name, int age){
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("User [id=");
        builder.append(id);

        return "User [id="+id+",name="+name+",age="+age+"]";
    }
}
