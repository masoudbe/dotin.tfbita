package com.dotin.tfbita.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the {@link com.dotin.tfbita.domain.TypeAttribute} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TypeAttributeDTO implements Serializable {

    private Long id;

    private Boolean necessary;

    private Boolean isUnique;

    private AttributeDTO attribute;

    private Set<ProductTypeDTO> productTypes = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getNecessary() {
        return necessary;
    }

    public void setNecessary(Boolean necessary) {
        this.necessary = necessary;
    }

    public Boolean getIsUnique() {
        return isUnique;
    }

    public void setIsUnique(Boolean isUnique) {
        this.isUnique = isUnique;
    }

    public AttributeDTO getAttribute() {
        return attribute;
    }

    public void setAttribute(AttributeDTO attribute) {
        this.attribute = attribute;
    }

    public Set<ProductTypeDTO> getProductTypes() {
        return productTypes;
    }

    public void setProductTypes(Set<ProductTypeDTO> productTypes) {
        this.productTypes = productTypes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TypeAttributeDTO)) {
            return false;
        }

        TypeAttributeDTO typeAttributeDTO = (TypeAttributeDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, typeAttributeDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TypeAttributeDTO{" +
            "id=" + getId() +
            ", necessary='" + getNecessary() + "'" +
            ", isUnique='" + getIsUnique() + "'" +
            ", attribute=" + getAttribute() +
            ", productTypes=" + getProductTypes() +
            "}";
    }
}
