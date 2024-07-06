package com.dotin.tfbita.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;

/**
 * A Attribute.
 */
@Entity
@Table(name = "attribute")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Attribute implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "modification_date")
    private String modificationDate;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "category" }, allowSetters = true)
    private CategoryElement format;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "category" }, allowSetters = true)
    private CategoryElement type;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Attribute id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModificationDate() {
        return this.modificationDate;
    }

    public Attribute modificationDate(String modificationDate) {
        this.setModificationDate(modificationDate);
        return this;
    }

    public void setModificationDate(String modificationDate) {
        this.modificationDate = modificationDate;
    }

    public String getName() {
        return this.name;
    }

    public Attribute name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryElement getFormat() {
        return this.format;
    }

    public void setFormat(CategoryElement categoryElement) {
        this.format = categoryElement;
    }

    public Attribute format(CategoryElement categoryElement) {
        this.setFormat(categoryElement);
        return this;
    }

    public CategoryElement getType() {
        return this.type;
    }

    public void setType(CategoryElement categoryElement) {
        this.type = categoryElement;
    }

    public Attribute type(CategoryElement categoryElement) {
        this.setType(categoryElement);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Attribute)) {
            return false;
        }
        return getId() != null && getId().equals(((Attribute) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Attribute{" +
            "id=" + getId() +
            ", modificationDate='" + getModificationDate() + "'" +
            ", name='" + getName() + "'" +
            "}";
    }
}
