package com.dotin.tfbita.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.dotin.tfbita.domain.ObjectiveCategory} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ObjectiveCategoryDTO implements Serializable {

    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ObjectiveCategoryDTO)) {
            return false;
        }

        ObjectiveCategoryDTO objectiveCategoryDTO = (ObjectiveCategoryDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, objectiveCategoryDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ObjectiveCategoryDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
