package com.dotin.tfbita.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Custom.
 */
@Entity
@Table(name = "custom")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Custom implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "modification_date")
    private String modificationDate;

    @Column(name = "latin_name")
    private String latinName;

    @Column(name = "name")
    private String name;

    @Column(name = "temp_id")
    private Long tempId;

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
    private Draft loadSwitchPlace;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "rel_custom__order_registration_info",
        joinColumns = @JoinColumn(name = "custom_id"),
        inverseJoinColumns = @JoinColumn(name = "order_registration_info_id")
    )
    @JsonIgnoreProperties(
        value = { "licenceInfos", "orderRegServs", "purchaseFromOtherResources", "customs", "productInfos" },
        allowSetters = true
    )
    private Set<OrderRegistrationInfo> orderRegistrationInfos = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "rel_custom__draft",
        joinColumns = @JoinColumn(name = "custom_id"),
        inverseJoinColumns = @JoinColumn(name = "draft_id")
    )
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
    private Set<Draft> drafts = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Custom id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModificationDate() {
        return this.modificationDate;
    }

    public Custom modificationDate(String modificationDate) {
        this.setModificationDate(modificationDate);
        return this;
    }

    public void setModificationDate(String modificationDate) {
        this.modificationDate = modificationDate;
    }

    public String getLatinName() {
        return this.latinName;
    }

    public Custom latinName(String latinName) {
        this.setLatinName(latinName);
        return this;
    }

    public void setLatinName(String latinName) {
        this.latinName = latinName;
    }

    public String getName() {
        return this.name;
    }

    public Custom name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTempId() {
        return this.tempId;
    }

    public Custom tempId(Long tempId) {
        this.setTempId(tempId);
        return this;
    }

    public void setTempId(Long tempId) {
        this.tempId = tempId;
    }

    public Draft getLoadSwitchPlace() {
        return this.loadSwitchPlace;
    }

    public void setLoadSwitchPlace(Draft draft) {
        this.loadSwitchPlace = draft;
    }

    public Custom loadSwitchPlace(Draft draft) {
        this.setLoadSwitchPlace(draft);
        return this;
    }

    public Set<OrderRegistrationInfo> getOrderRegistrationInfos() {
        return this.orderRegistrationInfos;
    }

    public void setOrderRegistrationInfos(Set<OrderRegistrationInfo> orderRegistrationInfos) {
        this.orderRegistrationInfos = orderRegistrationInfos;
    }

    public Custom orderRegistrationInfos(Set<OrderRegistrationInfo> orderRegistrationInfos) {
        this.setOrderRegistrationInfos(orderRegistrationInfos);
        return this;
    }

    public Custom addOrderRegistrationInfo(OrderRegistrationInfo orderRegistrationInfo) {
        this.orderRegistrationInfos.add(orderRegistrationInfo);
        return this;
    }

    public Custom removeOrderRegistrationInfo(OrderRegistrationInfo orderRegistrationInfo) {
        this.orderRegistrationInfos.remove(orderRegistrationInfo);
        return this;
    }

    public Set<Draft> getDrafts() {
        return this.drafts;
    }

    public void setDrafts(Set<Draft> drafts) {
        this.drafts = drafts;
    }

    public Custom drafts(Set<Draft> drafts) {
        this.setDrafts(drafts);
        return this;
    }

    public Custom addDraft(Draft draft) {
        this.drafts.add(draft);
        return this;
    }

    public Custom removeDraft(Draft draft) {
        this.drafts.remove(draft);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Custom)) {
            return false;
        }
        return getId() != null && getId().equals(((Custom) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Custom{" +
            "id=" + getId() +
            ", modificationDate='" + getModificationDate() + "'" +
            ", latinName='" + getLatinName() + "'" +
            ", name='" + getName() + "'" +
            ", tempId=" + getTempId() +
            "}";
    }
}
