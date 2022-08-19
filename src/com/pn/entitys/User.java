package com.pn.entitys;

public class User {
    private Integer id;
    private String loginname;
    private String password;
    private String realname;
    private String email;
    private String sex;
    private String phone;
    private Integer did;
    private String dname;
    private String pnames;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public User() {
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getPnames() {
        return pnames;
    }

    public void setPnames(String pnames) {
        this.pnames = pnames;
    }



    public User(Integer id, String loginname, String password, String realname, String email, String sex, String phone, Integer did) {
        this.id = id;
        this.loginname = loginname;
        this.password = password;
        this.realname = realname;
        this.email = email;
        this.sex = sex;
        this.phone = phone;
        this.did = did;
    }
}
