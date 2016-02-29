package com.karrye.meetsession.adapters;

/**
 * Created by student on 2016-02-26.
 */
public class SearchResultItem {
    private int userId;
    private String name;
    private int age;

    public SearchResultItem(int userId_,String name_, int age_){
        this.userId = userId_;
        this.name = name_;
        this.age = age_;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
}
