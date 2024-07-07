package com.dotin.tfbita.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;

/**
 * A DraftCertificateType.
 */
@Entity
@Table(name = "draft_certificate_type")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DraftCertificateType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "mandatory")
    private Boolean mandatory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "certificateTypeLists" }, allowSetters = true)
    private DraftRequestType draftRequestType;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public DraftCertificateType id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getMandatory() {
        return this.mandatory;
    }

    public DraftCertificateType mandatory(Boolean mandatory) {
        this.setMandatory(mandatory);
        return this;
    }

    public void setMandatory(Boolean mandatory) {
        this.mandatory = mandatory;
    }

    public DraftRequestType getDraftRequestType() {
        return this.draftRequestType;
    }

    public void setDraftRequestType(DraftRequestType draftRequestType) {
        this.draftRequestType = draftRequestType;
    }

    public DraftCertificateType draftRequestType(DraftRequestType draftRequestType) {
        this.setDraftRequestType(draftRequestType);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DraftCertificateType)) {
            return false;
        }
        return getId() != null && getId().equals(((DraftCertificateType) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DraftCertificateType{" +
            "id=" + getId() +
            ", mandatory='" + getMandatory() + "'" +
            "}";
    }
}
