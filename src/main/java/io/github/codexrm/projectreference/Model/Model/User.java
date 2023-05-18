package io.github.codexrm.projectreference.model.model;

import io.github.codexrm.projectreference.model.enums.ERole;

import java.util.ArrayList;
import java.util.List;

public class User {

    private Integer id;
    private String username;
    private String name;
    private String lastName;
    private String email;
    private boolean enabled;
    private String password;
    private List<Reference> referenceList;
    private List<Role> roles;

    public User() {
        referenceList = new ArrayList<>();
        roles = new ArrayList<>();
        id = 1;
        username = "marynes";
        name = "Marynes";
        lastName = "Diaz";
        email = "marynes@gmail.com";
        enabled = true;
        password = "$2a$10$fkIcZdUbQgtMNEf1mVl8ZOtKJSEJLO9FOy6eSaAwhKbUAdQyWrvhS";
        roles.add(new Role(1,ERole.ROLE_USER));
        roles.add(new Role(3,ERole.ROLE_ADMIN));
    }

    public User(Integer id, String username, String name, String lastName, String email, boolean enabled, String password, List<Reference> referenceList, List<Role> roles) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.enabled = enabled;
        this.password = password;
        this.referenceList = referenceList;
        this.roles = roles;
    }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email;}

    public void setEmail(String email) { this.email = email; }

    public boolean isEnabled() { return enabled; }

    public void setEnabled(boolean enabled) { this.enabled = enabled; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public List<Reference> getReferenceList() { return referenceList; }

    public void setReferenceList(List<Reference> referenceList) { this.referenceList = referenceList; }

    public List<Role> getRoles() { return roles; }

    public void setRoles(List<Role> roles) { this.roles = roles; }
}
