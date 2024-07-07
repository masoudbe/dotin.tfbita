package com.dotin.tfbita.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * A OrderRegServ.
 */
@Entity
@Table(name = "order_reg_serv")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OrderRegServ implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "amount", precision = 21, scale = 2)
    private BigDecimal amount;

    @Column(name = "currency_amount", precision = 21, scale = 2)
    private BigDecimal currencyAmount;

    @Column(name = "unit")
    private String unit;

    @Column(name = "title")
    private String title;

    @Column(name = "code")
    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "licenceInfos", "orderRegServs", "purchaseFromOtherResources", "customs", "productInfos" },
        allowSetters = true
    )
    private OrderRegistrationInfo orderRegService;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orderRegServ")
    @JsonIgnoreProperties(value = { "product", "orderRegServ", "licenceInfo" }, allowSetters = true)
    private Set<LicenceInfo> licenceInfos = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public OrderRegServ id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public OrderRegServ amount(BigDecimal amount) {
        this.setAmount(amount);
        return this;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getCurrencyAmount() {
        return this.currencyAmount;
    }

    public OrderRegServ currencyAmount(BigDecimal currencyAmount) {
        this.setCurrencyAmount(currencyAmount);
        return this;
    }

    public void setCurrencyAmount(BigDecimal currencyAmount) {
        this.currencyAmount = currencyAmount;
    }

    public String getUnit() {
        return this.unit;
    }

    public OrderRegServ unit(String unit) {
        this.setUnit(unit);
        return this;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getTitle() {
        return this.title;
    }

    public OrderRegServ title(String title) {
        this.setTitle(title);
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return this.code;
    }

    public OrderRegServ code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public OrderRegistrationInfo getOrderRegService() {
        return this.orderRegService;
    }

    public void setOrderRegService(OrderRegistrationInfo orderRegistrationInfo) {
        this.orderRegService = orderRegistrationInfo;
    }

    public OrderRegServ orderRegService(OrderRegistrationInfo orderRegistrationInfo) {
        this.setOrderRegService(orderRegistrationInfo);
        return this;
    }

    public Set<LicenceInfo> getLicenceInfos() {
        return this.licenceInfos;
    }

    public void setLicenceInfos(Set<LicenceInfo> licenceInfos) {
        if (this.licenceInfos != null) {
            this.licenceInfos.forEach(i -> i.setOrderRegServ(null));
        }
        if (licenceInfos != null) {
            licenceInfos.forEach(i -> i.setOrderRegServ(this));
        }
        this.licenceInfos = licenceInfos;
    }

    public OrderRegServ licenceInfos(Set<LicenceInfo> licenceInfos) {
        this.setLicenceInfos(licenceInfos);
        return this;
    }

    public OrderRegServ addLicenceInfo(LicenceInfo licenceInfo) {
        this.licenceInfos.add(licenceInfo);
        licenceInfo.setOrderRegServ(this);
        return this;
    }

    public OrderRegServ removeLicenceInfo(LicenceInfo licenceInfo) {
        this.licenceInfos.remove(licenceInfo);
        licenceInfo.setOrderRegServ(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrderRegServ)) {
            return false;
        }
        return getId() != null && getId().equals(((OrderRegServ) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OrderRegServ{" +
            "id=" + getId() +
            ", amount=" + getAmount() +
            ", currencyAmount=" + getCurrencyAmount() +
            ", unit='" + getUnit() + "'" +
            ", title='" + getTitle() + "'" +
            ", code='" + getCode() + "'" +
            "}";
    }
}
