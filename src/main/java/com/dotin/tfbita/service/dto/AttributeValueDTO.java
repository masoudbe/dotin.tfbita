package com.dotin.tfbita.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.dotin.tfbita.domain.AttributeValue} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AttributeValueDTO implements Serializable {

    private Long id;

    private String value;

    private String customValue;

    private String attributeValueGroupName;

    private TypeAttributeDTO typeAttribute;

    private AttributeValueGroupDTO attributeValueGroup;

    private ProductDTO product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCustomValue() {
        return customValue;
    }

    public void setCustomValue(String customValue) {
        this.customValue = customValue;
    }

    public String getAttributeValueGroupName() {
        return attributeValueGroupName;
    }

    public void setAttributeValueGroupName(String attributeValueGroupName) {
        this.attributeValueGroupName = attributeValueGroupName;
    }

    public TypeAttributeDTO getTypeAttribute() {
        return typeAttribute;
    }

    public void setTypeAttribute(TypeAttributeDTO typeAttribute) {
        this.typeAttribute = typeAttribute;
    }

    public AttributeValueGroupDTO getAttributeValueGroup() {
        return attributeValueGroup;
    }

    public void setAttributeValueGroup(AttributeValueGroupDTO attributeValueGroup) {
        this.attributeValueGroup = attributeValueGroup;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AttributeValueDTO)) {
            return false;
        }

        AttributeValueDTO attributeValueDTO = (AttributeValueDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, attributeValueDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AttributeValueDTO{" +
            "id=" + getId() +
            ", value='" + getValue() + "'" +
            ", customValue='" + getCustomValue() + "'" +
            ", attributeValueGroupName='" + getAttributeValueGroupName() + "'" +
            ", typeAttribute=" + getTypeAttribute() +
            ", attributeValueGroup=" + getAttributeValueGroup() +
            ", product=" + getProduct() +
            "}";
    }
}
