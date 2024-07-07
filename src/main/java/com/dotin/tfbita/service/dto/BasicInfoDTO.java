package com.dotin.tfbita.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.dotin.tfbita.domain.BasicInfo} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class BasicInfoDTO implements Serializable {

    private Long id;

    private String applyDate;

    private String code;

    private Boolean disabled;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BasicInfoDTO)) {
            return false;
        }

        BasicInfoDTO basicInfoDTO = (BasicInfoDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, basicInfoDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BasicInfoDTO{" +
            "id=" + getId() +
            ", applyDate='" + getApplyDate() + "'" +
            ", code='" + getCode() + "'" +
            ", disabled='" + getDisabled() + "'" +
            "}";
    }
}
