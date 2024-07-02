package com.dotin.tfbita.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A ObjectiveCategory.
 */
@Entity
@Table(name = "objective_category")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ObjectiveCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "objectiveCategory")
    @JsonIgnoreProperties(value = { "objectiveCategory" }, allowSetters = true)
    private Set<ObjectiveCategoryElement> possibleValues = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ObjectiveCategory id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public ObjectiveCategory name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ObjectiveCategoryElement> getPossibleValues() {
        return this.possibleValues;
    }

    public void setPossibleValues(Set<ObjectiveCategoryElement> objectiveCategoryElements) {
        if (this.possibleValues != null) {
            this.possibleValues.forEach(i -> i.setObjectiveCategory(null));
        }
        if (objectiveCategoryElements != null) {
            objectiveCategoryElements.forEach(i -> i.setObjectiveCategory(this));
        }
        this.possibleValues = objectiveCategoryElements;
    }

    public ObjectiveCategory possibleValues(Set<ObjectiveCategoryElement> objectiveCategoryElements) {
        this.setPossibleValues(objectiveCategoryElements);
        return this;
    }

    public ObjectiveCategory addPossibleValues(ObjectiveCategoryElement objectiveCategoryElement) {
        this.possibleValues.add(objectiveCategoryElement);
        objectiveCategoryElement.setObjectiveCategory(this);
        return this;
    }

    public ObjectiveCategory removePossibleValues(ObjectiveCategoryElement objectiveCategoryElement) {
        this.possibleValues.remove(objectiveCategoryElement);
        objectiveCategoryElement.setObjectiveCategory(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ObjectiveCategory)) {
            return false;
        }
        return getId() != null && getId().equals(((ObjectiveCategory) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ObjectiveCategory{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
