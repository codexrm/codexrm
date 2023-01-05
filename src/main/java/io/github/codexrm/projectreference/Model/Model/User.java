package io.github.codexrm.projectreference.model.model;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String username;
    private String password;
    private List<Reference> referenceList;

    public User() { }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.referenceList = new ArrayList<>();
    }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public List<Reference> getReferenceList() { return referenceList; }

    public void setReferenceList(List<Reference> referenceList) { this.referenceList = referenceList; }
}


