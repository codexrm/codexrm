package io.github.codexrm.projectreference.model.model;

import io.github.codexrm.projectreference.model.enums.ERole;

public class Role {

    private Integer id;
    private ERole name;

    public Role() { }

    public Role(Integer id, ERole name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public ERole getName() { return name; }

    public void setName(ERole name) { this.name = name; }
}
