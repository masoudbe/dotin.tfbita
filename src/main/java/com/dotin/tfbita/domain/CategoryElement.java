package com.dotin.tfbita.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;

/**
 * A CategoryElement.
 */
@Entity
@Table(name = "category_element")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CategoryElement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "value")
    private String value;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "code")
    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "licenceInfos", "orderRegServs", "purchaseFromOtherResources", "customs", "productInfos" },
        allowSetters = true
    )
    private OrderRegistrationInfo orderRegType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "licenceInfos", "orderRegServs", "purchaseFromOtherResources", "customs", "productInfos" },
        allowSetters = true
    )
    private OrderRegistrationInfo requestType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "licenceInfos", "orderRegServs", "purchaseFromOtherResources", "customs", "productInfos" },
        allowSetters = true
    )
    private OrderRegistrationInfo importType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "licenceInfos", "orderRegServs", "purchaseFromOtherResources", "customs", "productInfos" },
        allowSetters = true
    )
    private OrderRegistrationInfo operationTyp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "licenceInfos", "orderRegServs", "purchaseFromOtherResources", "customs", "productInfos" },
        allowSetters = true
    )
    private OrderRegistrationInfo currencyProvisionType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "licenceInfos", "orderRegServs", "purchaseFromOtherResources", "customs", "productInfos" },
        allowSetters = true
    )
    private OrderRegistrationInfo paymentTool;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "licenceInfos", "orderRegServs", "purchaseFromOtherResources", "customs", "productInfos" },
        allowSetters = true
    )
    private OrderRegistrationInfo activityType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "licenceInfos", "orderRegServs", "purchaseFromOtherResources", "customs", "productInfos" },
        allowSetters = true
    )
    private OrderRegistrationInfo ownerType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "licenceInfos", "orderRegServs", "purchaseFromOtherResources", "customs", "productInfos" },
        allowSetters = true
    )
    private OrderRegistrationInfo status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "licenceInfos", "orderRegServs", "purchaseFromOtherResources", "customs", "productInfos" },
        allowSetters = true
    )
    private OrderRegistrationInfo externalCustomerType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "licenceInfos", "orderRegServs", "purchaseFromOtherResources", "customs", "productInfos" },
        allowSetters = true
    )
    private OrderRegistrationInfo transportType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "purchaseFromOtherResources" }, allowSetters = true)
    private PurchaseFromOtherResources currencySupplier;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "purchaseFromOtherResources" }, allowSetters = true)
    private PurchaseFromOtherResources statusPurchase;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "licenceInfos", "orderRegServs", "purchaseFromOtherResources", "customs", "productInfos" },
        allowSetters = true
    )
    private OrderRegistrationInfo transportVehicleType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = {
            "draftReceipts",
            "draftUsedAssurances",
            "draftFactors",
            "draftCustomJustifications",
            "draftExtends",
            "draftTaxes",
            "draftStatusInfos",
            "customs",
            "products",
            "services",
        },
        allowSetters = true
    )
    private Draft freightLetterType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = {
            "draftReceipts",
            "draftUsedAssurances",
            "draftFactors",
            "draftCustomJustifications",
            "draftExtends",
            "draftTaxes",
            "draftStatusInfos",
            "customs",
            "products",
            "services",
        },
        allowSetters = true
    )
    private Draft actionCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = {
            "draftReceipts",
            "draftUsedAssurances",
            "draftFactors",
            "draftCustomJustifications",
            "draftExtends",
            "draftTaxes",
            "draftStatusInfos",
            "customs",
            "products",
            "services",
        },
        allowSetters = true
    )
    private Draft ownershipCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = {
            "draftReceipts",
            "draftUsedAssurances",
            "draftFactors",
            "draftCustomJustifications",
            "draftExtends",
            "draftTaxes",
            "draftStatusInfos",
            "customs",
            "products",
            "services",
        },
        allowSetters = true
    )
    private Draft currencyContainerPlace;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = {
            "draftReceipts",
            "draftUsedAssurances",
            "draftFactors",
            "draftCustomJustifications",
            "draftExtends",
            "draftTaxes",
            "draftStatusInfos",
            "customs",
            "products",
            "services",
        },
        allowSetters = true
    )
    private Draft draftSource;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = {
            "draftReceipts",
            "draftUsedAssurances",
            "draftFactors",
            "draftCustomJustifications",
            "draftExtends",
            "draftTaxes",
            "draftStatusInfos",
            "customs",
            "products",
            "services",
        },
        allowSetters = true
    )
    private Draft chargedExchangeBroker;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = {
            "draftReceipts",
            "draftUsedAssurances",
            "draftFactors",
            "draftCustomJustifications",
            "draftExtends",
            "draftTaxes",
            "draftStatusInfos",
            "customs",
            "products",
            "services",
        },
        allowSetters = true
    )
    private Draft impartType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = {
            "draftReceipts",
            "draftUsedAssurances",
            "draftFactors",
            "draftCustomJustifications",
            "draftExtends",
            "draftTaxes",
            "draftStatusInfos",
            "customs",
            "products",
            "services",
        },
        allowSetters = true
    )
    private Draft insuranceLetterType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = {
            "draftReceipts",
            "draftUsedAssurances",
            "draftFactors",
            "draftCustomJustifications",
            "draftExtends",
            "draftTaxes",
            "draftStatusInfos",
            "customs",
            "products",
            "services",
        },
        allowSetters = true
    )
    private Draft advisorDepositType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = {
            "draftReceipts",
            "draftUsedAssurances",
            "draftFactors",
            "draftCustomJustifications",
            "draftExtends",
            "draftTaxes",
            "draftStatusInfos",
            "customs",
            "products",
            "services",
        },
        allowSetters = true
    )
    private Draft interfaceAdvisorDepositType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = {
            "draftReceipts",
            "draftUsedAssurances",
            "draftFactors",
            "draftCustomJustifications",
            "draftExtends",
            "draftTaxes",
            "draftStatusInfos",
            "customs",
            "products",
            "services",
        },
        allowSetters = true
    )
    private Draft paymentType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = {
            "draftReceipts",
            "draftUsedAssurances",
            "draftFactors",
            "draftCustomJustifications",
            "draftExtends",
            "draftTaxes",
            "draftStatusInfos",
            "customs",
            "products",
            "services",
        },
        allowSetters = true
    )
    private Draft dealType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = {
            "draftReceipts",
            "draftUsedAssurances",
            "draftFactors",
            "draftCustomJustifications",
            "draftExtends",
            "draftTaxes",
            "draftStatusInfos",
            "customs",
            "products",
            "services",
        },
        allowSetters = true
    )
    private Draft coveringAdvisorDepositType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "advisingBank", "interfaceAdvisingBank", "coveringBank" }, allowSetters = true)
    private AdvisorDefinition depositType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "draftType" }, allowSetters = true)
    private DraftType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "draftType" }, allowSetters = true)
    private DraftType secondaryType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "draftType" }, allowSetters = true)
    private DraftType division;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "products", "receipts", "draftCustomJustifications" }, allowSetters = true)
    private DraftReceipt productDimension;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "products", "receipts", "draftCustomJustifications" }, allowSetters = true)
    private DraftReceipt stateOfDocuments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "products", "receipts", "draftCustomJustifications" }, allowSetters = true)
    private DraftReceipt currencyProvisionFileType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "statusInfo" }, allowSetters = true)
    private DraftStatusInfo statusDraft;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "categoryElements" }, allowSetters = true)
    private Category categoryElement;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public CategoryElement id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return this.value;
    }

    public CategoryElement value(String value) {
        this.setValue(value);
        return this;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public CategoryElement categoryName(String categoryName) {
        this.setCategoryName(categoryName);
        return this;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCode() {
        return this.code;
    }

    public CategoryElement code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public OrderRegistrationInfo getOrderRegType() {
        return this.orderRegType;
    }

    public void setOrderRegType(OrderRegistrationInfo orderRegistrationInfo) {
        this.orderRegType = orderRegistrationInfo;
    }

    public CategoryElement orderRegType(OrderRegistrationInfo orderRegistrationInfo) {
        this.setOrderRegType(orderRegistrationInfo);
        return this;
    }

    public OrderRegistrationInfo getRequestType() {
        return this.requestType;
    }

    public void setRequestType(OrderRegistrationInfo orderRegistrationInfo) {
        this.requestType = orderRegistrationInfo;
    }

    public CategoryElement requestType(OrderRegistrationInfo orderRegistrationInfo) {
        this.setRequestType(orderRegistrationInfo);
        return this;
    }

    public OrderRegistrationInfo getImportType() {
        return this.importType;
    }

    public void setImportType(OrderRegistrationInfo orderRegistrationInfo) {
        this.importType = orderRegistrationInfo;
    }

    public CategoryElement importType(OrderRegistrationInfo orderRegistrationInfo) {
        this.setImportType(orderRegistrationInfo);
        return this;
    }

    public OrderRegistrationInfo getOperationTyp() {
        return this.operationTyp;
    }

    public void setOperationTyp(OrderRegistrationInfo orderRegistrationInfo) {
        this.operationTyp = orderRegistrationInfo;
    }

    public CategoryElement operationTyp(OrderRegistrationInfo orderRegistrationInfo) {
        this.setOperationTyp(orderRegistrationInfo);
        return this;
    }

    public OrderRegistrationInfo getCurrencyProvisionType() {
        return this.currencyProvisionType;
    }

    public void setCurrencyProvisionType(OrderRegistrationInfo orderRegistrationInfo) {
        this.currencyProvisionType = orderRegistrationInfo;
    }

    public CategoryElement currencyProvisionType(OrderRegistrationInfo orderRegistrationInfo) {
        this.setCurrencyProvisionType(orderRegistrationInfo);
        return this;
    }

    public OrderRegistrationInfo getPaymentTool() {
        return this.paymentTool;
    }

    public void setPaymentTool(OrderRegistrationInfo orderRegistrationInfo) {
        this.paymentTool = orderRegistrationInfo;
    }

    public CategoryElement paymentTool(OrderRegistrationInfo orderRegistrationInfo) {
        this.setPaymentTool(orderRegistrationInfo);
        return this;
    }

    public OrderRegistrationInfo getActivityType() {
        return this.activityType;
    }

    public void setActivityType(OrderRegistrationInfo orderRegistrationInfo) {
        this.activityType = orderRegistrationInfo;
    }

    public CategoryElement activityType(OrderRegistrationInfo orderRegistrationInfo) {
        this.setActivityType(orderRegistrationInfo);
        return this;
    }

    public OrderRegistrationInfo getOwnerType() {
        return this.ownerType;
    }

    public void setOwnerType(OrderRegistrationInfo orderRegistrationInfo) {
        this.ownerType = orderRegistrationInfo;
    }

    public CategoryElement ownerType(OrderRegistrationInfo orderRegistrationInfo) {
        this.setOwnerType(orderRegistrationInfo);
        return this;
    }

    public OrderRegistrationInfo getStatus() {
        return this.status;
    }

    public void setStatus(OrderRegistrationInfo orderRegistrationInfo) {
        this.status = orderRegistrationInfo;
    }

    public CategoryElement status(OrderRegistrationInfo orderRegistrationInfo) {
        this.setStatus(orderRegistrationInfo);
        return this;
    }

    public OrderRegistrationInfo getExternalCustomerType() {
        return this.externalCustomerType;
    }

    public void setExternalCustomerType(OrderRegistrationInfo orderRegistrationInfo) {
        this.externalCustomerType = orderRegistrationInfo;
    }

    public CategoryElement externalCustomerType(OrderRegistrationInfo orderRegistrationInfo) {
        this.setExternalCustomerType(orderRegistrationInfo);
        return this;
    }

    public OrderRegistrationInfo getTransportType() {
        return this.transportType;
    }

    public void setTransportType(OrderRegistrationInfo orderRegistrationInfo) {
        this.transportType = orderRegistrationInfo;
    }

    public CategoryElement transportType(OrderRegistrationInfo orderRegistrationInfo) {
        this.setTransportType(orderRegistrationInfo);
        return this;
    }

    public PurchaseFromOtherResources getCurrencySupplier() {
        return this.currencySupplier;
    }

    public void setCurrencySupplier(PurchaseFromOtherResources purchaseFromOtherResources) {
        this.currencySupplier = purchaseFromOtherResources;
    }

    public CategoryElement currencySupplier(PurchaseFromOtherResources purchaseFromOtherResources) {
        this.setCurrencySupplier(purchaseFromOtherResources);
        return this;
    }

    public PurchaseFromOtherResources getStatusPurchase() {
        return this.statusPurchase;
    }

    public void setStatusPurchase(PurchaseFromOtherResources purchaseFromOtherResources) {
        this.statusPurchase = purchaseFromOtherResources;
    }

    public CategoryElement statusPurchase(PurchaseFromOtherResources purchaseFromOtherResources) {
        this.setStatusPurchase(purchaseFromOtherResources);
        return this;
    }

    public OrderRegistrationInfo getTransportVehicleType() {
        return this.transportVehicleType;
    }

    public void setTransportVehicleType(OrderRegistrationInfo orderRegistrationInfo) {
        this.transportVehicleType = orderRegistrationInfo;
    }

    public CategoryElement transportVehicleType(OrderRegistrationInfo orderRegistrationInfo) {
        this.setTransportVehicleType(orderRegistrationInfo);
        return this;
    }

    public Draft getFreightLetterType() {
        return this.freightLetterType;
    }

    public void setFreightLetterType(Draft draft) {
        this.freightLetterType = draft;
    }

    public CategoryElement freightLetterType(Draft draft) {
        this.setFreightLetterType(draft);
        return this;
    }

    public Draft getActionCode() {
        return this.actionCode;
    }

    public void setActionCode(Draft draft) {
        this.actionCode = draft;
    }

    public CategoryElement actionCode(Draft draft) {
        this.setActionCode(draft);
        return this;
    }

    public Draft getOwnershipCode() {
        return this.ownershipCode;
    }

    public void setOwnershipCode(Draft draft) {
        this.ownershipCode = draft;
    }

    public CategoryElement ownershipCode(Draft draft) {
        this.setOwnershipCode(draft);
        return this;
    }

    public Draft getCurrencyContainerPlace() {
        return this.currencyContainerPlace;
    }

    public void setCurrencyContainerPlace(Draft draft) {
        this.currencyContainerPlace = draft;
    }

    public CategoryElement currencyContainerPlace(Draft draft) {
        this.setCurrencyContainerPlace(draft);
        return this;
    }

    public Draft getDraftSource() {
        return this.draftSource;
    }

    public void setDraftSource(Draft draft) {
        this.draftSource = draft;
    }

    public CategoryElement draftSource(Draft draft) {
        this.setDraftSource(draft);
        return this;
    }

    public Draft getChargedExchangeBroker() {
        return this.chargedExchangeBroker;
    }

    public void setChargedExchangeBroker(Draft draft) {
        this.chargedExchangeBroker = draft;
    }

    public CategoryElement chargedExchangeBroker(Draft draft) {
        this.setChargedExchangeBroker(draft);
        return this;
    }

    public Draft getImpartType() {
        return this.impartType;
    }

    public void setImpartType(Draft draft) {
        this.impartType = draft;
    }

    public CategoryElement impartType(Draft draft) {
        this.setImpartType(draft);
        return this;
    }

    public Draft getInsuranceLetterType() {
        return this.insuranceLetterType;
    }

    public void setInsuranceLetterType(Draft draft) {
        this.insuranceLetterType = draft;
    }

    public CategoryElement insuranceLetterType(Draft draft) {
        this.setInsuranceLetterType(draft);
        return this;
    }

    public Draft getAdvisorDepositType() {
        return this.advisorDepositType;
    }

    public void setAdvisorDepositType(Draft draft) {
        this.advisorDepositType = draft;
    }

    public CategoryElement advisorDepositType(Draft draft) {
        this.setAdvisorDepositType(draft);
        return this;
    }

    public Draft getInterfaceAdvisorDepositType() {
        return this.interfaceAdvisorDepositType;
    }

    public void setInterfaceAdvisorDepositType(Draft draft) {
        this.interfaceAdvisorDepositType = draft;
    }

    public CategoryElement interfaceAdvisorDepositType(Draft draft) {
        this.setInterfaceAdvisorDepositType(draft);
        return this;
    }

    public Draft getPaymentType() {
        return this.paymentType;
    }

    public void setPaymentType(Draft draft) {
        this.paymentType = draft;
    }

    public CategoryElement paymentType(Draft draft) {
        this.setPaymentType(draft);
        return this;
    }

    public Draft getDealType() {
        return this.dealType;
    }

    public void setDealType(Draft draft) {
        this.dealType = draft;
    }

    public CategoryElement dealType(Draft draft) {
        this.setDealType(draft);
        return this;
    }

    public Draft getCoveringAdvisorDepositType() {
        return this.coveringAdvisorDepositType;
    }

    public void setCoveringAdvisorDepositType(Draft draft) {
        this.coveringAdvisorDepositType = draft;
    }

    public CategoryElement coveringAdvisorDepositType(Draft draft) {
        this.setCoveringAdvisorDepositType(draft);
        return this;
    }

    public AdvisorDefinition getDepositType() {
        return this.depositType;
    }

    public void setDepositType(AdvisorDefinition advisorDefinition) {
        this.depositType = advisorDefinition;
    }

    public CategoryElement depositType(AdvisorDefinition advisorDefinition) {
        this.setDepositType(advisorDefinition);
        return this;
    }

    public DraftType getType() {
        return this.type;
    }

    public void setType(DraftType draftType) {
        this.type = draftType;
    }

    public CategoryElement type(DraftType draftType) {
        this.setType(draftType);
        return this;
    }

    public DraftType getSecondaryType() {
        return this.secondaryType;
    }

    public void setSecondaryType(DraftType draftType) {
        this.secondaryType = draftType;
    }

    public CategoryElement secondaryType(DraftType draftType) {
        this.setSecondaryType(draftType);
        return this;
    }

    public DraftType getDivision() {
        return this.division;
    }

    public void setDivision(DraftType draftType) {
        this.division = draftType;
    }

    public CategoryElement division(DraftType draftType) {
        this.setDivision(draftType);
        return this;
    }

    public DraftReceipt getProductDimension() {
        return this.productDimension;
    }

    public void setProductDimension(DraftReceipt draftReceipt) {
        this.productDimension = draftReceipt;
    }

    public CategoryElement productDimension(DraftReceipt draftReceipt) {
        this.setProductDimension(draftReceipt);
        return this;
    }

    public DraftReceipt getStateOfDocuments() {
        return this.stateOfDocuments;
    }

    public void setStateOfDocuments(DraftReceipt draftReceipt) {
        this.stateOfDocuments = draftReceipt;
    }

    public CategoryElement stateOfDocuments(DraftReceipt draftReceipt) {
        this.setStateOfDocuments(draftReceipt);
        return this;
    }

    public DraftReceipt getCurrencyProvisionFileType() {
        return this.currencyProvisionFileType;
    }

    public void setCurrencyProvisionFileType(DraftReceipt draftReceipt) {
        this.currencyProvisionFileType = draftReceipt;
    }

    public CategoryElement currencyProvisionFileType(DraftReceipt draftReceipt) {
        this.setCurrencyProvisionFileType(draftReceipt);
        return this;
    }

    public DraftStatusInfo getStatusDraft() {
        return this.statusDraft;
    }

    public void setStatusDraft(DraftStatusInfo draftStatusInfo) {
        this.statusDraft = draftStatusInfo;
    }

    public CategoryElement statusDraft(DraftStatusInfo draftStatusInfo) {
        this.setStatusDraft(draftStatusInfo);
        return this;
    }

    public Category getCategoryElement() {
        return this.categoryElement;
    }

    public void setCategoryElement(Category category) {
        this.categoryElement = category;
    }

    public CategoryElement categoryElement(Category category) {
        this.setCategoryElement(category);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CategoryElement)) {
            return false;
        }
        return getId() != null && getId().equals(((CategoryElement) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CategoryElement{" +
            "id=" + getId() +
            ", value='" + getValue() + "'" +
            ", categoryName='" + getCategoryName() + "'" +
            ", code='" + getCode() + "'" +
            "}";
    }
}
