package com.pn.entitys;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KnowsType {
    @JsonProperty("id")
    private Integer typeid;

    @JsonProperty("name")
    private String typename;

    @JsonProperty("pId")
    private Integer ptypeid;

    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public Integer getPtypeid() {
        return ptypeid;
    }

    public void setPtypeid(Integer ptypeid) {
        this.ptypeid = ptypeid;
    }
}
