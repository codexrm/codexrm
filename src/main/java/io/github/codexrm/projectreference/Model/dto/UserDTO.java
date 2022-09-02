package io.github.codexrm.projectreference.model.dto;

public class UserDTO {

    private String id;
    private String userCodex;
    private String password;

    public UserDTO() {}

    public UserDTO(String id, String userCodex, String password) {
        this.id = id;
        this.userCodex = userCodex;
        this.password = password;
    }

    public String getId() {return id;}

    public void setId(String id) {this.id = id;}

    public String getUserCodex() {return userCodex;}

    public void setUserCodex(String userCodex) {this.userCodex = userCodex;}

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}
}
