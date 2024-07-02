package com.dotin.tfbita.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the {@link com.dotin.tfbita.domain.StringValue} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class StringValueDTO implements Serializable {

    private Long id;

    private String value;

    private Set<OrderRegistrationInfoDTO> orderRegistrationInfos = new HashSet<>();

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
        if (!(o instanceof StringValueDTO)) {
            return false;
        }

        StringValueDTO stringValueDTO = (StringValueDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, stringValueDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "StringValueDTO{" +
            "id=" + getId() +
            ", value='" + getValue() + "'" +
            ", orderRegistrationInfos=" + getOrderRegistrationInfos() +
            "}";
    }
}
