package io.github.codexrm.projectreference.model.model;

import java.util.ArrayList;
import java.util.List;

public class User {

    private Integer id;
    private String username;
    private String password;
    private String name;
    private String lastName;
    private String email;
    private boolean enabled;

    private List<Reference> referenceList;

    public User() { }

    public User(Integer id, String username, String password, String name, String lastName, String email, boolean enabled) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.enabled = enabled;
        this.referenceList = new ArrayList<>();
    }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public boolean isEnabled() { return enabled; }

    public void setEnabled(boolean enabled) { this.enabled = enabled; }

    public List<Reference> getReferenceList() { return referenceList; }

    public void setReferenceList(List<Reference> referenceList) { this.referenceList = referenceList; }
}


