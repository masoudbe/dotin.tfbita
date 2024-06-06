package com.dotin.tfbita.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the {@link com.dotin.tfbita.domain.Custom} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CustomDTO implements Serializable {

    private Long id;

    private String modificationDate;

    private String latinName;

    private String name;

    private Long tempId;

    private Set<OrderRegistrationInfoDTO> orderRegistrationInfos = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(String modificationDate) {
        this.modificationDate = modificationDate;
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

    public Long getTempId() {
        return tempId;
    }

    public void setTempId(Long tempId) {
        this.tempId = tempId;
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
        if (!(o instanceof CustomDTO)) {
            return false;
        }

        CustomDTO customDTO = (CustomDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, customDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CustomDTO{" +
            "id=" + getId() +
            ", modificationDate='" + getModificationDate() + "'" +
            ", latinName='" + getLatinName() + "'" +
            ", name='" + getName() + "'" +
            ", tempId=" + getTempId() +
            ", orderRegistrationInfos=" + getOrderRegistrationInfos() +
            "}";
    }
}
