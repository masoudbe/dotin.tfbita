package com.dotin.tfbita.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.dotin.tfbita.domain.ObjectiveCategoryElement} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ObjectiveCategoryElementDTO implements Serializable {

    private Long id;

    private String entityClass;

    private Long entityId;

    private ObjectiveCategoryDTO objectiveCategory;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(String entityClass) {
        this.entityClass = entityClass;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public ObjectiveCategoryDTO getObjectiveCategory() {
        return objectiveCategory;
    }

    public void setObjectiveCategory(ObjectiveCategoryDTO objectiveCategory) {
        this.objectiveCategory = objectiveCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ObjectiveCategoryElementDTO)) {
            return false;
        }

        ObjectiveCategoryElementDTO objectiveCategoryElementDTO = (ObjectiveCategoryElementDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, objectiveCategoryElementDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ObjectiveCategoryElementDTO{" +
            "id=" + getId() +
            ", entityClass='" + getEntityClass() + "'" +
            ", entityId=" + getEntityId() +
            ", objectiveCategory=" + getObjectiveCategory() +
            "}";
    }
}
