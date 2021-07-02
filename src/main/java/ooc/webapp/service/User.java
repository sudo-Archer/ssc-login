package ooc.webapp.service;

public class User {
    private String username;
    private String name;
    private String info;

    public User(String username, String name, String info) {
        this.username = username;
        this.name = name;
        this.info = info;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }
}
