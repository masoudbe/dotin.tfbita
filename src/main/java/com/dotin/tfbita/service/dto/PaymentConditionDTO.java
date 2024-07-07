package com.dotin.tfbita.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.dotin.tfbita.domain.PaymentCondition} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PaymentConditionDTO implements Serializable {

    private Long id;

    private String latinName;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLatinName() {
        return latinName;
    }

    public void setLatinName(String latinName) {
        this.latinName = latinName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PaymentConditionDTO)) {
            return false;
        }

        PaymentConditionDTO paymentConditionDTO = (PaymentConditionDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, paymentConditionDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PaymentConditionDTO{" +
            "id=" + getId() +
            ", latinName='" + getLatinName() + "'" +
            ", name='" + getName() + "'" +
            "}";
    }
}
