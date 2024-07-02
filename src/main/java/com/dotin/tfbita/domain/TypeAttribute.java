package com.dotin.tfbita.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A TypeAttribute.
 */
@Entity
@Table(name = "type_attribute")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TypeAttribute implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "necessary")
    private Boolean necessary;

    @Column(name = "is_unique")
    private Boolean isUnique;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "format", "type" }, allowSetters = true)
    private Attribute attribute;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "productTypeAttributes")
    @JsonIgnoreProperties(value = { "productTypeAttributes" }, allowSetters = true)
    private Set<ProductType> productTypes = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public TypeAttribute id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getNecessary() {
        return this.necessary;
    }

    public TypeAttribute necessary(Boolean necessary) {
        this.setNecessary(necessary);
        return this;
    }

    public void setNecessary(Boolean necessary) {
        this.necessary = necessary;
    }

    public Boolean getIsUnique() {
        return this.isUnique;
    }

    public TypeAttribute isUnique(Boolean isUnique) {
        this.setIsUnique(isUnique);
        return this;
    }

    public void setIsUnique(Boolean isUnique) {
        this.isUnique = isUnique;
    }

    public Attribute getAttribute() {
        return this.attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    public TypeAttribute attribute(Attribute attribute) {
        this.setAttribute(attribute);
        return this;
    }

    public Set<ProductType> getProductTypes() {
        return this.productTypes;
    }

    public void setProductTypes(Set<ProductType> productTypes) {
        if (this.productTypes != null) {
            this.productTypes.forEach(i -> i.removeProductTypeAttributes(this));
        }
        if (productTypes != null) {
            productTypes.forEach(i -> i.addProductTypeAttributes(this));
        }
        this.productTypes = productTypes;
    }

    public TypeAttribute productTypes(Set<ProductType> productTypes) {
        this.setProductTypes(productTypes);
        return this;
    }

    public TypeAttribute addProductType(ProductType productType) {
        this.productTypes.add(productType);
        productType.getProductTypeAttributes().add(this);
        return this;
    }

    public TypeAttribute removeProductType(ProductType productType) {
        this.productTypes.remove(productType);
        productType.getProductTypeAttributes().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TypeAttribute)) {
            return false;
        }
        return getId() != null && getId().equals(((TypeAttribute) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TypeAttribute{" +
            "id=" + getId() +
            ", necessary='" + getNecessary() + "'" +
            ", isUnique='" + getIsUnique() + "'" +
            "}";
    }
}
