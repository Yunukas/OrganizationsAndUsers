package com.yy.OrganizationDemo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    // id field for both subclasses ( Organization and User )
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",updatable = false,nullable = false)
    private Long id;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Override
    public int hashCode() {
        return  Objects.hash(this.getClass().getName(),getId());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass())
            return false;

        if (this == obj)
            return true;

        BaseEntity baseEntity = (BaseEntity) obj;

        return  Objects.equals(this.toString(),baseEntity.toString());
    }

    @Override
    public String toString() {
        return "@["+this.getClass().getName()+ "]="+getId();
    }
}
