package io.github.codexrm.projectreference.model.model;

public class User {

    private Integer id;
    private String userCodex;
    private String password;

    public User() {}

    public User(String userCodex, String password) {
        this.userCodex = userCodex;
        this.password = password;
        id = 1;
    }

    public Integer getId() {return id;}

    public void setId(Integer id) {this.id = id;}

    public String getUserCodex() {return userCodex;}

    public void setUserCodex(String userCodex) {this.userCodex = userCodex;}

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}
}


