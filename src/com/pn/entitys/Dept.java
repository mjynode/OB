package com.pn.entitys;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Dept {
    private Integer id;
    @JsonProperty("name")
    private String dname;
    private String ddesc;
    @JsonProperty("pId")
    private Integer pid;
    private String pdname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getDdesc() {
        return ddesc;
    }

    public void setDdesc(String ddesc) {
        this.ddesc = ddesc;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPdname() {
        return pdname;
    }

    public void setPdname(String pdname) {
        this.pdname = pdname;
    }
}
