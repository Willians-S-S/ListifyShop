package com.willians.ListifyShop.entety;

import com.willians.ListifyShop.enums.RoleName;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "role_table")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private RoleName name;

    public Role() {}

    public Role(UUID id, RoleName name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public RoleName getName() {
        return name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Role name1 = (Role) o;
        return Objects.equals(id, name1.id) && name == name1.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
