package com.dotin.tfbita.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Category.
 */
@Entity
@Table(name = "category")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "categoryElement")
    @JsonIgnoreProperties(
        value = {
            "orderRegType",
            "requestType",
            "importType",
            "operationTyp",
            "currencyProvisionType",
            "paymentTool",
            "activityType",
            "ownerType",
            "status",
            "externalCustomerType",
            "transportType",
            "currencySupplier",
            "statusPurchase",
            "transportVehicleType",
            "freightLetterType",
            "actionCode",
            "ownershipCode",
            "currencyContainerPlace",
            "draftSource",
            "chargedExchangeBroker",
            "impartType",
            "insuranceLetterType",
            "advisorDepositType",
            "interfaceAdvisorDepositType",
            "paymentType",
            "dealType",
            "coveringAdvisorDepositType",
            "depositType",
            "type",
            "secondaryType",
            "division",
            "productDimension",
            "stateOfDocuments",
            "currencyProvisionFileType",
            "statusDraft",
            "categoryElement",
        },
        allowSetters = true
    )
    private Set<CategoryElement> categoryElements = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Category id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public Category name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return this.code;
    }

    public Category code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Set<CategoryElement> getCategoryElements() {
        return this.categoryElements;
    }

    public void setCategoryElements(Set<CategoryElement> categoryElements) {
        if (this.categoryElements != null) {
            this.categoryElements.forEach(i -> i.setCategoryElement(null));
        }
        if (categoryElements != null) {
            categoryElements.forEach(i -> i.setCategoryElement(this));
        }
        this.categoryElements = categoryElements;
    }

    public Category categoryElements(Set<CategoryElement> categoryElements) {
        this.setCategoryElements(categoryElements);
        return this;
    }

    public Category addCategoryElement(CategoryElement categoryElement) {
        this.categoryElements.add(categoryElement);
        categoryElement.setCategoryElement(this);
        return this;
    }

    public Category removeCategoryElement(CategoryElement categoryElement) {
        this.categoryElements.remove(categoryElement);
        categoryElement.setCategoryElement(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Category)) {
            return false;
        }
        return getId() != null && getId().equals(((Category) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Category{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", code='" + getCode() + "'" +
            "}";
    }
}
