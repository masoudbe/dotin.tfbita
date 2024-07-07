package com.dotin.tfbita.domain;

import jakarta.persistence.*;
import java.io.Serializable;

/**
 * A BasicInfo.
 */
@Entity
@Table(name = "basic_info")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class BasicInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "apply_date")
    private String applyDate;

    @Column(name = "code")
    private String code;

    @Column(name = "disabled")
    private Boolean disabled;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public BasicInfo id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApplyDate() {
        return this.applyDate;
    }

    public BasicInfo applyDate(String applyDate) {
        this.setApplyDate(applyDate);
        return this;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }

    public String getCode() {
        return this.code;
    }

    public BasicInfo code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getDisabled() {
        return this.disabled;
    }

    public BasicInfo disabled(Boolean disabled) {
        this.setDisabled(disabled);
        return this;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BasicInfo)) {
            return false;
        }
        return getId() != null && getId().equals(((BasicInfo) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BasicInfo{" +
            "id=" + getId() +
            ", applyDate='" + getApplyDate() + "'" +
            ", code='" + getCode() + "'" +
            ", disabled='" + getDisabled() + "'" +
            "}";
    }
}
