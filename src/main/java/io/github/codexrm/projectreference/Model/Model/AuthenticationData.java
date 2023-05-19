package io.github.codexrm.projectreference.model.model;

public class AuthenticationData {

    private Integer id;
    private String username;
    private String name;
    private String lastName;
    private String email;
    private boolean enabled;
    private String token;
    private String expiredTime;


    public AuthenticationData() { }

    public AuthenticationData(Integer id, String username, String name, String lastName, String email, boolean enabled, String token, String expiredTime) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.enabled = enabled;
        this.token = token;
        this.expiredTime = expiredTime;
    }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public boolean isEnabled() { return enabled; }

    public void setEnabled(boolean enabled) { this.enabled = enabled; }

    public String getToken() { return token; }

    public void setToken(String token) { this.token = token; }

    public String getExpiredTime() { return expiredTime; }

    public void setExpiredTime(String expiredTime) { this.expiredTime = expiredTime; }
}


