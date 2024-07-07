package com.dotin.tfbita.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.dotin.tfbita.domain.DraftProductInfo} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DraftProductInfoDTO implements Serializable {

    private Long id;

    private String productAmount;

    private String productDimension;

    private ProductDTO product;

    private DraftReceiptDTO draftReceipt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(String productAmount) {
        this.productAmount = productAmount;
    }

    public String getProductDimension() {
        return productDimension;
    }

    public void setProductDimension(String productDimension) {
        this.productDimension = productDimension;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public DraftReceiptDTO getDraftReceipt() {
        return draftReceipt;
    }

    public void setDraftReceipt(DraftReceiptDTO draftReceipt) {
        this.draftReceipt = draftReceipt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DraftProductInfoDTO)) {
            return false;
        }

        DraftProductInfoDTO draftProductInfoDTO = (DraftProductInfoDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, draftProductInfoDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DraftProductInfoDTO{" +
            "id=" + getId() +
            ", productAmount='" + getProductAmount() + "'" +
            ", productDimension='" + getProductDimension() + "'" +
            ", product=" + getProduct() +
            ", draftReceipt=" + getDraftReceipt() +
            "}";
    }
}
