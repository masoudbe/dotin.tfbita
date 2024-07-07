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

    private ProductTypeDTO productType;

    private Set<OrderRegistrationInfoDTO> orderRegistrationInfos = new HashSet<>();

    private Set<DraftDTO> drafts = new HashSet<>();

    private Set<CustomJustificationDTO> customJustifications = new HashSet<>();

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

    public ProductTypeDTO getProductType() {
        return productType;
    }

    public void setProductType(ProductTypeDTO productType) {
        this.productType = productType;
    }

    public Set<OrderRegistrationInfoDTO> getOrderRegistrationInfos() {
        return orderRegistrationInfos;
    }

    public void setOrderRegistrationInfos(Set<OrderRegistrationInfoDTO> orderRegistrationInfos) {
        this.orderRegistrationInfos = orderRegistrationInfos;
    }

    public Set<DraftDTO> getDrafts() {
        return drafts;
    }

    public void setDrafts(Set<DraftDTO> drafts) {
        this.drafts = drafts;
    }

    public Set<CustomJustificationDTO> getCustomJustifications() {
        return customJustifications;
    }

    public void setCustomJustifications(Set<CustomJustificationDTO> customJustifications) {
        this.customJustifications = customJustifications;
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
            ", productType=" + getProductType() +
            ", orderRegistrationInfos=" + getOrderRegistrationInfos() +
            ", drafts=" + getDrafts() +
            ", customJustifications=" + getCustomJustifications() +
            "}";
    }
}
