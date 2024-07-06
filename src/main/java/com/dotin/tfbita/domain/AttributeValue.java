package com.dotin.tfbita.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;

/**
 * A AttributeValue.
 */
@Entity
@Table(name = "attribute_value")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AttributeValue implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "val")
    private String val;

    @Column(name = "custom_value")
    private String customValue;

    @Column(name = "attribute_value_group_name")
    private String attributeValueGroupName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "attribute", "productTypes" }, allowSetters = true)
    private TypeAttribute typeAttribute;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "values", "attribute" }, allowSetters = true)
    private AttributeValueGroup attributeValueGroup;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "attributeValues", "productType", "orderRegistrationInfos", "drafts", "customJustifications" },
        allowSetters = true
    )
    private Product product;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public AttributeValue id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVal() {
        return this.val;
    }

    public AttributeValue val(String val) {
        this.setVal(val);
        return this;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public String getCustomValue() {
        return this.customValue;
    }

    public AttributeValue customValue(String customValue) {
        this.setCustomValue(customValue);
        return this;
    }

    public void setCustomValue(String customValue) {
        this.customValue = customValue;
    }

    public String getAttributeValueGroupName() {
        return this.attributeValueGroupName;
    }

    public AttributeValue attributeValueGroupName(String attributeValueGroupName) {
        this.setAttributeValueGroupName(attributeValueGroupName);
        return this;
    }

    public void setAttributeValueGroupName(String attributeValueGroupName) {
        this.attributeValueGroupName = attributeValueGroupName;
    }

    public TypeAttribute getTypeAttribute() {
        return this.typeAttribute;
    }

    public void setTypeAttribute(TypeAttribute typeAttribute) {
        this.typeAttribute = typeAttribute;
    }

    public AttributeValue typeAttribute(TypeAttribute typeAttribute) {
        this.setTypeAttribute(typeAttribute);
        return this;
    }

    public AttributeValueGroup getAttributeValueGroup() {
        return this.attributeValueGroup;
    }

    public void setAttributeValueGroup(AttributeValueGroup attributeValueGroup) {
        this.attributeValueGroup = attributeValueGroup;
    }

    public AttributeValue attributeValueGroup(AttributeValueGroup attributeValueGroup) {
        this.setAttributeValueGroup(attributeValueGroup);
        return this;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public AttributeValue product(Product product) {
        this.setProduct(product);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AttributeValue)) {
            return false;
        }
        return getId() != null && getId().equals(((AttributeValue) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AttributeValue{" +
            "id=" + getId() +
            ", val='" + getVal() + "'" +
            ", customValue='" + getCustomValue() + "'" +
            ", attributeValueGroupName='" + getAttributeValueGroupName() + "'" +
            "}";
    }
}
