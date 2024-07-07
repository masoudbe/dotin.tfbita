package com.dotin.tfbita.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A ProductType.
 */
@Entity
@Table(name = "product_type")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ProductType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "modification_date")
    private String modificationDate;

    @Column(name = "topic_code")
    private String topicCode;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "rel_product_type__product_type_attributes",
        joinColumns = @JoinColumn(name = "product_type_id"),
        inverseJoinColumns = @JoinColumn(name = "product_type_attributes_id")
    )
    @JsonIgnoreProperties(value = { "attribute", "productTypes" }, allowSetters = true)
    private Set<TypeAttribute> productTypeAttributes = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ProductType id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return this.description;
    }

    public ProductType description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getModificationDate() {
        return this.modificationDate;
    }

    public ProductType modificationDate(String modificationDate) {
        this.setModificationDate(modificationDate);
        return this;
    }

    public void setModificationDate(String modificationDate) {
        this.modificationDate = modificationDate;
    }

    public String getTopicCode() {
        return this.topicCode;
    }

    public ProductType topicCode(String topicCode) {
        this.setTopicCode(topicCode);
        return this;
    }

    public void setTopicCode(String topicCode) {
        this.topicCode = topicCode;
    }

    public Set<TypeAttribute> getProductTypeAttributes() {
        return this.productTypeAttributes;
    }

    public void setProductTypeAttributes(Set<TypeAttribute> typeAttributes) {
        this.productTypeAttributes = typeAttributes;
    }

    public ProductType productTypeAttributes(Set<TypeAttribute> typeAttributes) {
        this.setProductTypeAttributes(typeAttributes);
        return this;
    }

    public ProductType addProductTypeAttributes(TypeAttribute typeAttribute) {
        this.productTypeAttributes.add(typeAttribute);
        return this;
    }

    public ProductType removeProductTypeAttributes(TypeAttribute typeAttribute) {
        this.productTypeAttributes.remove(typeAttribute);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductType)) {
            return false;
        }
        return getId() != null && getId().equals(((ProductType) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProductType{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", modificationDate='" + getModificationDate() + "'" +
            ", topicCode='" + getTopicCode() + "'" +
            "}";
    }
}
