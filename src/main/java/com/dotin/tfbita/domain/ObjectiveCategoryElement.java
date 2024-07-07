package com.dotin.tfbita.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;

/**
 * A ObjectiveCategoryElement.
 */
@Entity
@Table(name = "objective_category_element")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ObjectiveCategoryElement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "entity_class")
    private String entityClass;

    @Column(name = "entity_id")
    private Long entityId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "possibleValues" }, allowSetters = true)
    private ObjectiveCategory objectiveCategory;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ObjectiveCategoryElement id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEntityClass() {
        return this.entityClass;
    }

    public ObjectiveCategoryElement entityClass(String entityClass) {
        this.setEntityClass(entityClass);
        return this;
    }

    public void setEntityClass(String entityClass) {
        this.entityClass = entityClass;
    }

    public Long getEntityId() {
        return this.entityId;
    }

    public ObjectiveCategoryElement entityId(Long entityId) {
        this.setEntityId(entityId);
        return this;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public ObjectiveCategory getObjectiveCategory() {
        return this.objectiveCategory;
    }

    public void setObjectiveCategory(ObjectiveCategory objectiveCategory) {
        this.objectiveCategory = objectiveCategory;
    }

    public ObjectiveCategoryElement objectiveCategory(ObjectiveCategory objectiveCategory) {
        this.setObjectiveCategory(objectiveCategory);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ObjectiveCategoryElement)) {
            return false;
        }
        return getId() != null && getId().equals(((ObjectiveCategoryElement) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ObjectiveCategoryElement{" +
            "id=" + getId() +
            ", entityClass='" + getEntityClass() + "'" +
            ", entityId=" + getEntityId() +
            "}";
    }
}
