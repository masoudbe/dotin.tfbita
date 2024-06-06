package com.dotin.tfbita.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the {@link com.dotin.tfbita.domain.Product} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ProductDTO implements Serializable {

    private Long id;

    private String code;

    private String modificationDate;

    private String name;

    private String description;

    private String topicCode;

    private String attributeValueGroupName;

    private Set<OrderRegistrationInfoDTO> orderRegistrationInfos = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(String modificationDate) {
        this.modificationDate = modificationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTopicCode() {
        return topicCode;
    }

    public void setTopicCode(String topicCode) {
        this.topicCode = topicCode;
    }

    public String getAttributeValueGroupName() {
        return attributeValueGroupName;
    }

    public void setAttributeValueGroupName(String attributeValueGroupName) {
        this.attributeValueGroupName = attributeValueGroupName;
    }

    public Set<OrderRegistrationInfoDTO> getOrderRegistrationInfos() {
        return orderRegistrationInfos;
    }

    public void setOrderRegistrationInfos(Set<OrderRegistrationInfoDTO> orderRegistrationInfos) {
        this.orderRegistrationInfos = orderRegistrationInfos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductDTO)) {
            return false;
        }

        ProductDTO productDTO = (ProductDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, productDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProductDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", modificationDate='" + getModificationDate() + "'" +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", topicCode='" + getTopicCode() + "'" +
            ", attributeValueGroupName='" + getAttributeValueGroupName() + "'" +
            ", orderRegistrationInfos=" + getOrderRegistrationInfos() +
            "}";
    }
}
