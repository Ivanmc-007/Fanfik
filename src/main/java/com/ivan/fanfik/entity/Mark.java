package com.ivan.fanfik.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Mark extends AbstractEntity {

    private Integer value;

    @ManyToMany
    @JoinTable(name="mark_user",
            joinColumns=
            @JoinColumn(name="mark_id", referencedColumnName="id"),
            inverseJoinColumns=
            @JoinColumn(name="user_id", referencedColumnName="id"))
    private Set<User> users;

    @ManyToMany(mappedBy = "marks")
    private List<Composition> compositions;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public List<Composition> getCompositions() {
        return compositions;
    }

    public void setCompositions(List<Composition> compositions) {
        this.compositions = compositions;
    }
}