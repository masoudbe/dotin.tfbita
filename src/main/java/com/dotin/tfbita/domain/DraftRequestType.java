package com.dotin.tfbita.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A DraftRequestType.
 */
@Entity
@Table(name = "draft_request_type")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DraftRequestType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "draftRequestType")
    @JsonIgnoreProperties(value = { "draftRequestType" }, allowSetters = true)
    private Set<DraftCertificateType> certificateTypeLists = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public DraftRequestType id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<DraftCertificateType> getCertificateTypeLists() {
        return this.certificateTypeLists;
    }

    public void setCertificateTypeLists(Set<DraftCertificateType> draftCertificateTypes) {
        if (this.certificateTypeLists != null) {
            this.certificateTypeLists.forEach(i -> i.setDraftRequestType(null));
        }
        if (draftCertificateTypes != null) {
            draftCertificateTypes.forEach(i -> i.setDraftRequestType(this));
        }
        this.certificateTypeLists = draftCertificateTypes;
    }

    public DraftRequestType certificateTypeLists(Set<DraftCertificateType> draftCertificateTypes) {
        this.setCertificateTypeLists(draftCertificateTypes);
        return this;
    }

    public DraftRequestType addCertificateTypeList(DraftCertificateType draftCertificateType) {
        this.certificateTypeLists.add(draftCertificateType);
        draftCertificateType.setDraftRequestType(this);
        return this;
    }

    public DraftRequestType removeCertificateTypeList(DraftCertificateType draftCertificateType) {
        this.certificateTypeLists.remove(draftCertificateType);
        draftCertificateType.setDraftRequestType(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DraftRequestType)) {
            return false;
        }
        return getId() != null && getId().equals(((DraftRequestType) o).getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DraftRequestType{" +
            "id=" + getId() +
            "}";
    }
}
