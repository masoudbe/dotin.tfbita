package com.dotin.tfbita.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;

/**
 * A InsuranceCompanyInfo.
 */
@Entity
@Table(name = "insurance_company_info")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class InsuranceCompanyInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "modification_date")
    private String modificationDate;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = {
            "draftReceipts",
            "draftUsedAssurances",
            "draftFactors",
            "draftCustomJustifications",
            "draftExtends",
            "draftTaxes",
            "customs",
            "products",
            "services",
        },
        allowSetters = true
    )
    private Draft insuranceCompanyInfo;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public InsuranceCompanyInfo id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModificationDate() {
        return this.modificationDate;
    }

    public InsuranceCompanyInfo modificationDate(String modificationDate) {
        this.setModificationDate(modificationDate);
        return this;
    }

    public void setModificationDate(String modificationDate) {
        this.modificationDate = modificationDate;
    }

    public String getName() {
        return this.name;
    }

    public InsuranceCompanyInfo name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Draft getInsuranceCompanyInfo() {
        return this.insuranceCompanyInfo;
    }

    public void setInsuranceCompanyInfo(Draft draft) {
        this.insuranceCompanyInfo = draft;
    }

    public InsuranceCompanyInfo insuranceCompanyInfo(Draft draft) {
        this.setInsuranceCompanyInfo(draft);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InsuranceCompanyInfo)) {
            return false;
        }
        return getId() != null && getId().equals(((InsuranceCompanyInfo) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InsuranceCompanyInfo{" +
            "id=" + getId() +
            ", modificationDate='" + getModificationDate() + "'" +
            ", name='" + getName() + "'" +
            "}";
    }
}
