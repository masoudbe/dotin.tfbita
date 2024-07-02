package com.dotin.tfbita.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A AttributeValueGroup.
 */
@Entity
@Table(name = "attribute_value_group")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AttributeValueGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "mandatory")
    private Boolean mandatory;

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "attributeValueGroup")
    @JsonIgnoreProperties(value = { "typeAttribute", "attributeValueGroup", "product" }, allowSetters = true)
    private Set<AttributeValue> values = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "format", "type" }, allowSetters = true)
    private Attribute attribute;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public AttributeValueGroup id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getMandatory() {
        return this.mandatory;
    }

    public AttributeValueGroup mandatory(Boolean mandatory) {
        this.setMandatory(mandatory);
        return this;
    }

    public void setMandatory(Boolean mandatory) {
        this.mandatory = mandatory;
    }

    public String getName() {
        return this.name;
    }

    public AttributeValueGroup name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<AttributeValue> getValues() {
        return this.values;
    }

    public void setValues(Set<AttributeValue> attributeValues) {
        if (this.values != null) {
            this.values.forEach(i -> i.setAttributeValueGroup(null));
        }
        if (attributeValues != null) {
            attributeValues.forEach(i -> i.setAttributeValueGroup(this));
        }
        this.values = attributeValues;
    }

    public AttributeValueGroup values(Set<AttributeValue> attributeValues) {
        this.setValues(attributeValues);
        return this;
    }

    public AttributeValueGroup addValues(AttributeValue attributeValue) {
        this.values.add(attributeValue);
        attributeValue.setAttributeValueGroup(this);
        return this;
    }

    public AttributeValueGroup removeValues(AttributeValue attributeValue) {
        this.values.remove(attributeValue);
        attributeValue.setAttributeValueGroup(null);
        return this;
    }

    public Attribute getAttribute() {
        return this.attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    public AttributeValueGroup attribute(Attribute attribute) {
        this.setAttribute(attribute);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AttributeValueGroup)) {
            return false;
        }
        return getId() != null && getId().equals(((AttributeValueGroup) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AttributeValueGroup{" +
            "id=" + getId() +
            ", mandatory='" + getMandatory() + "'" +
            ", name='" + getName() + "'" +
            "}";
    }
}
