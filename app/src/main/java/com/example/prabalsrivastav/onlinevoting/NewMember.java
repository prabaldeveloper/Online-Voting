package com.example.prabalsrivastav.onlinevoting;

/**
 * Created by Prabal Srivastav on 24-06-2020.
 */

public class NewMember {
    String name, age, loginId, password, aCardNo,city,logpass;

    public NewMember() {
    }

    public NewMember(String name, String age, String loginId, String password, String aCardNo, String city, String logpass){
        this.name = name;
        this.age = age;
        this.loginId = loginId;
        this.password = password;
        this.aCardNo = aCardNo;
        this.city = city;
        this.logpass = logpass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getaCardNo() {
        return aCardNo;
    }

    public void setaCardNo(String aCardNo) {
        this.aCardNo = aCardNo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLogpass() {
        return logpass;
    }

    public void setLogpass(String logpass) {
        this.logpass = logpass;
    }
}
