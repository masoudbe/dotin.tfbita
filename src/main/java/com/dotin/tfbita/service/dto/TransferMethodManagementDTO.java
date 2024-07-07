package com.dotin.tfbita.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.dotin.tfbita.domain.TransferMethodManagement} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TransferMethodManagementDTO implements Serializable {

    private Long id;

    private String code;

    private String desc;

    private CategoryElementDTO type;

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public CategoryElementDTO getType() {
        return type;
    }

    public void setType(CategoryElementDTO type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TransferMethodManagementDTO)) {
            return false;
        }

        TransferMethodManagementDTO transferMethodManagementDTO = (TransferMethodManagementDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, transferMethodManagementDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TransferMethodManagementDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", desc='" + getDesc() + "'" +
            ", type=" + getType() +
            "}";
    }
}
