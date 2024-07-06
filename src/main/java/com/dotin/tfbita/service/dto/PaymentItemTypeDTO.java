package com.dotin.tfbita.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.dotin.tfbita.domain.PaymentItemType} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PaymentItemTypeDTO implements Serializable {

    private Long id;

    private String description;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PaymentItemTypeDTO)) {
            return false;
        }

        PaymentItemTypeDTO paymentItemTypeDTO = (PaymentItemTypeDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, paymentItemTypeDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PaymentItemTypeDTO{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
