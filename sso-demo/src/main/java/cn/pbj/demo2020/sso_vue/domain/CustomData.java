package cn.pbj.demo2020.sso_vue.domain;


public class CustomData {
    private Integer id;
    private String data;

    public CustomData(Integer id, String data) {
        this.id = id;
        this.data = data;
    }

    public CustomData() {

    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
