package com.dotin.tfbita.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Product.
 */
@Entity
@Table(name = "product")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "modification_date")
    private String modificationDate;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "topic_code")
    private String topicCode;

    @Column(name = "attribute_value_group_name")
    private String attributeValueGroupName;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "rel_product__order_registration_info",
        joinColumns = @JoinColumn(name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "order_registration_info_id")
    )
    @JsonIgnoreProperties(
        value = { "licenceInfos", "orderRegServs", "purchaseFromOtherResources", "customs", "productInfos" },
        allowSetters = true
    )
    private Set<OrderRegistrationInfo> orderRegistrationInfos = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "rel_product__draft",
        joinColumns = @JoinColumn(name = "product_id"),
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "products", "receipts", "draftCustomJustifications" }, allowSetters = true)
    private DraftReceipt draftProductInfos;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Product id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public Product code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getModificationDate() {
        return this.modificationDate;
    }

    public Product modificationDate(String modificationDate) {
        this.setModificationDate(modificationDate);
        return this;
    }

    public void setModificationDate(String modificationDate) {
        this.modificationDate = modificationDate;
    }

    public String getName() {
        return this.name;
    }

    public Product name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public Product description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTopicCode() {
        return this.topicCode;
    }

    public Product topicCode(String topicCode) {
        this.setTopicCode(topicCode);
        return this;
    }

    public void setTopicCode(String topicCode) {
        this.topicCode = topicCode;
    }

    public String getAttributeValueGroupName() {
        return this.attributeValueGroupName;
    }

    public Product attributeValueGroupName(String attributeValueGroupName) {
        this.setAttributeValueGroupName(attributeValueGroupName);
        return this;
    }

    public void setAttributeValueGroupName(String attributeValueGroupName) {
        this.attributeValueGroupName = attributeValueGroupName;
    }

    public Set<OrderRegistrationInfo> getOrderRegistrationInfos() {
        return this.orderRegistrationInfos;
    }

    public void setOrderRegistrationInfos(Set<OrderRegistrationInfo> orderRegistrationInfos) {
        this.orderRegistrationInfos = orderRegistrationInfos;
    }

    public Product orderRegistrationInfos(Set<OrderRegistrationInfo> orderRegistrationInfos) {
        this.setOrderRegistrationInfos(orderRegistrationInfos);
        return this;
    }

    public Product addOrderRegistrationInfo(OrderRegistrationInfo orderRegistrationInfo) {
        this.orderRegistrationInfos.add(orderRegistrationInfo);
        return this;
    }

    public Product removeOrderRegistrationInfo(OrderRegistrationInfo orderRegistrationInfo) {
        this.orderRegistrationInfos.remove(orderRegistrationInfo);
        return this;
    }

    public Set<Draft> getDrafts() {
        return this.drafts;
    }

    public void setDrafts(Set<Draft> drafts) {
        this.drafts = drafts;
    }

    public Product drafts(Set<Draft> drafts) {
        this.setDrafts(drafts);
        return this;
    }

    public Product addDraft(Draft draft) {
        this.drafts.add(draft);
        return this;
    }

    public Product removeDraft(Draft draft) {
        this.drafts.remove(draft);
        return this;
    }

    public DraftReceipt getDraftProductInfos() {
        return this.draftProductInfos;
    }

    public void setDraftProductInfos(DraftReceipt draftReceipt) {
        this.draftProductInfos = draftReceipt;
    }

    public Product draftProductInfos(DraftReceipt draftReceipt) {
        this.setDraftProductInfos(draftReceipt);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Product)) {
            return false;
        }
        return getId() != null && getId().equals(((Product) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Product{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", modificationDate='" + getModificationDate() + "'" +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", topicCode='" + getTopicCode() + "'" +
            ", attributeValueGroupName='" + getAttributeValueGroupName() + "'" +
            "}";
    }
}
