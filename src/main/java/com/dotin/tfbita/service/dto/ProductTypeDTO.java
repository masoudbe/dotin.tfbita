package com.dotin.tfbita.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the {@link com.dotin.tfbita.domain.ProductType} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ProductTypeDTO implements Serializable {

    private Long id;

    private String description;

    private String modificationDate;

    private String topicCode;

    private Set<TypeAttributeDTO> productTypeAttributes = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(String modificationDate) {
        this.modificationDate = modificationDate;
    }

    public String getTopicCode() {
        return topicCode;
    }

    public void setTopicCode(String topicCode) {
        this.topicCode = topicCode;
    }

    public Set<TypeAttributeDTO> getProductTypeAttributes() {
        return productTypeAttributes;
    }

    public void setProductTypeAttributes(Set<TypeAttributeDTO> productTypeAttributes) {
        this.productTypeAttributes = productTypeAttributes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductTypeDTO)) {
            return false;
        }

        ProductTypeDTO productTypeDTO = (ProductTypeDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, productTypeDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProductTypeDTO{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", modificationDate='" + getModificationDate() + "'" +
            ", topicCode='" + getTopicCode() + "'" +
            ", productTypeAttributes=" + getProductTypeAttributes() +
            "}";
    }
}
