package com.dotin.tfbita.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * A OrderRegistrationInfo.
 */
@Entity
@Table(name = "order_registration_info")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OrderRegistrationInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "order_reg_num")
    private String orderRegNum;

    @Column(name = "start_order_reg_date")
    private String startOrderRegDate;

    @Column(name = "end_order_reg_date")
    private String endOrderRegDate;

    @Column(name = "request_number")
    private String requestNumber;

    @Column(name = "bank_code")
    private String bankCode;

    @Column(name = "branch_code")
    private String branchCode;

    @Column(name = "customer_number")
    private String customerNumber;

    @Column(name = "applicant_nationalcode")
    private String applicantNationalcode;

    @Column(name = "performa_number")
    private String performaNumber;

    @Column(name = "performa_date")
    private String performaDate;

    @Column(name = "performa_expiry_date")
    private String performaExpiryDate;

    @Column(name = "performa_date_persian")
    private String performaDatePersian;

    @Column(name = "performa_expiry_date_persian")
    private String performaExpiryDatePersian;

    @Column(name = "info_submission_date")
    private String infoSubmissionDate;

    @Column(name = "seller_name")
    private String sellerName;

    @Column(name = "beneficiary_country")
    private String beneficiaryCountry;

    @Column(name = "source_country")
    private String sourceCountry;

    @Column(name = "multiple_transportable")
    private Boolean multipleTransportable;

    @Column(name = "delivery_time_of_goods")
    private String deliveryTimeOfGoods;

    @Column(name = "total_weight_in_kg")
    private Double totalWeightInKg;

    @Column(name = "possibility_carrying")
    private Boolean possibilityCarrying;

    @Column(name = "possibility_clearance")
    private Boolean possibilityClearance;

    @Column(name = "able_add_service_in_product_order")
    private Boolean ableAddServiceInProductOrder;

    @Column(name = "free_zone")
    private Boolean freeZone;

    @Column(name = "currency_code")
    private String currencyCode;

    @Column(name = "fob_amount", precision = 21, scale = 2)
    private BigDecimal fobAmount;

    @Column(name = "discount", precision = 21, scale = 2)
    private BigDecimal discount;

    @Column(name = "shipment_cost", precision = 21, scale = 2)
    private BigDecimal shipmentCost;

    @Column(name = "othr_cost", precision = 21, scale = 2)
    private BigDecimal othrCost;

    @Column(name = "total_amount", precision = 21, scale = 2)
    private BigDecimal totalAmount;

    @Column(name = "is_file")
    private Boolean isFile;

    @Column(name = "file_number")
    private String fileNumber;

    @Column(name = "extended")
    private Boolean extended;

    @Column(name = "updated")
    private Boolean updated;

    @Column(name = "generate_from_service")
    private Boolean generateFromService;

    @Column(name = "certificate_number")
    private String certificateNumber;

    @Column(name = "central_bank_code")
    private String centralBankCode;

    @Column(name = "is_migrational")
    private Boolean isMigrational;

    @Column(name = "external_customer")
    private Long externalCustomer;

    @Column(name = "sum_redemption_duration")
    private Long sumRedemptionDuration;

    @Column(name = "extended_day_number")
    private Long extendedDayNumber;

    @Column(name = "main_group_product_code")
    private String mainGroupProductCode;

    @Column(name = "producer_countries")
    private String producerCountries;

    @Lob
    @Column(name = "excel_file")
    private byte[] excelFile;

    @Column(name = "excel_file_content_type")
    private String excelFileContentType;

    @Column(name = "commission_transaction_number")
    private String commissionTransactionNumber;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "licenceInfo")
    @JsonIgnoreProperties(value = { "product", "orderRegServ", "licenceInfo" }, allowSetters = true)
    private Set<LicenceInfo> licenceInfos = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orderRegService")
    @JsonIgnoreProperties(value = { "orderRegService", "licenceInfos" }, allowSetters = true)
    private Set<OrderRegServ> orderRegServs = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "purchaseFromOtherResources")
    @JsonIgnoreProperties(value = { "purchaseFromOtherResources" }, allowSetters = true)
    private Set<PurchaseFromOtherResources> purchaseFromOtherResources = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "orderRegistrationInfos")
    @JsonIgnoreProperties(value = { "loadSwitchPlace", "orderRegistrationInfos", "drafts" }, allowSetters = true)
    private Set<Custom> customs = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "orderRegistrationInfos")
    @JsonIgnoreProperties(value = { "orderRegistrationInfos", "drafts", "draftProductInfos" }, allowSetters = true)
    private Set<Product> productInfos = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public OrderRegistrationInfo id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderRegNum() {
        return this.orderRegNum;
    }

    public OrderRegistrationInfo orderRegNum(String orderRegNum) {
        this.setOrderRegNum(orderRegNum);
        return this;
    }

    public void setOrderRegNum(String orderRegNum) {
        this.orderRegNum = orderRegNum;
    }

    public String getStartOrderRegDate() {
        return this.startOrderRegDate;
    }

    public OrderRegistrationInfo startOrderRegDate(String startOrderRegDate) {
        this.setStartOrderRegDate(startOrderRegDate);
        return this;
    }

    public void setStartOrderRegDate(String startOrderRegDate) {
        this.startOrderRegDate = startOrderRegDate;
    }

    public String getEndOrderRegDate() {
        return this.endOrderRegDate;
    }

    public OrderRegistrationInfo endOrderRegDate(String endOrderRegDate) {
        this.setEndOrderRegDate(endOrderRegDate);
        return this;
    }

    public void setEndOrderRegDate(String endOrderRegDate) {
        this.endOrderRegDate = endOrderRegDate;
    }

    public String getRequestNumber() {
        return this.requestNumber;
    }

    public OrderRegistrationInfo requestNumber(String requestNumber) {
        this.setRequestNumber(requestNumber);
        return this;
    }

    public void setRequestNumber(String requestNumber) {
        this.requestNumber = requestNumber;
    }

    public String getBankCode() {
        return this.bankCode;
    }

    public OrderRegistrationInfo bankCode(String bankCode) {
        this.setBankCode(bankCode);
        return this;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBranchCode() {
        return this.branchCode;
    }

    public OrderRegistrationInfo branchCode(String branchCode) {
        this.setBranchCode(branchCode);
        return this;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getCustomerNumber() {
        return this.customerNumber;
    }

    public OrderRegistrationInfo customerNumber(String customerNumber) {
        this.setCustomerNumber(customerNumber);
        return this;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getApplicantNationalcode() {
        return this.applicantNationalcode;
    }

    public OrderRegistrationInfo applicantNationalcode(String applicantNationalcode) {
        this.setApplicantNationalcode(applicantNationalcode);
        return this;
    }

    public void setApplicantNationalcode(String applicantNationalcode) {
        this.applicantNationalcode = applicantNationalcode;
    }

    public String getPerformaNumber() {
        return this.performaNumber;
    }

    public OrderRegistrationInfo performaNumber(String performaNumber) {
        this.setPerformaNumber(performaNumber);
        return this;
    }

    public void setPerformaNumber(String performaNumber) {
        this.performaNumber = performaNumber;
    }

    public String getPerformaDate() {
        return this.performaDate;
    }

    public OrderRegistrationInfo performaDate(String performaDate) {
        this.setPerformaDate(performaDate);
        return this;
    }

    public void setPerformaDate(String performaDate) {
        this.performaDate = performaDate;
    }

    public String getPerformaExpiryDate() {
        return this.performaExpiryDate;
    }

    public OrderRegistrationInfo performaExpiryDate(String performaExpiryDate) {
        this.setPerformaExpiryDate(performaExpiryDate);
        return this;
    }

    public void setPerformaExpiryDate(String performaExpiryDate) {
        this.performaExpiryDate = performaExpiryDate;
    }

    public String getPerformaDatePersian() {
        return this.performaDatePersian;
    }

    public OrderRegistrationInfo performaDatePersian(String performaDatePersian) {
        this.setPerformaDatePersian(performaDatePersian);
        return this;
    }

    public void setPerformaDatePersian(String performaDatePersian) {
        this.performaDatePersian = performaDatePersian;
    }

    public String getPerformaExpiryDatePersian() {
        return this.performaExpiryDatePersian;
    }

    public OrderRegistrationInfo performaExpiryDatePersian(String performaExpiryDatePersian) {
        this.setPerformaExpiryDatePersian(performaExpiryDatePersian);
        return this;
    }

    public void setPerformaExpiryDatePersian(String performaExpiryDatePersian) {
        this.performaExpiryDatePersian = performaExpiryDatePersian;
    }

    public String getInfoSubmissionDate() {
        return this.infoSubmissionDate;
    }

    public OrderRegistrationInfo infoSubmissionDate(String infoSubmissionDate) {
        this.setInfoSubmissionDate(infoSubmissionDate);
        return this;
    }

    public void setInfoSubmissionDate(String infoSubmissionDate) {
        this.infoSubmissionDate = infoSubmissionDate;
    }

    public String getSellerName() {
        return this.sellerName;
    }

    public OrderRegistrationInfo sellerName(String sellerName) {
        this.setSellerName(sellerName);
        return this;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getBeneficiaryCountry() {
        return this.beneficiaryCountry;
    }

    public OrderRegistrationInfo beneficiaryCountry(String beneficiaryCountry) {
        this.setBeneficiaryCountry(beneficiaryCountry);
        return this;
    }

    public void setBeneficiaryCountry(String beneficiaryCountry) {
        this.beneficiaryCountry = beneficiaryCountry;
    }

    public String getSourceCountry() {
        return this.sourceCountry;
    }

    public OrderRegistrationInfo sourceCountry(String sourceCountry) {
        this.setSourceCountry(sourceCountry);
        return this;
    }

    public void setSourceCountry(String sourceCountry) {
        this.sourceCountry = sourceCountry;
    }

    public Boolean getMultipleTransportable() {
        return this.multipleTransportable;
    }

    public OrderRegistrationInfo multipleTransportable(Boolean multipleTransportable) {
        this.setMultipleTransportable(multipleTransportable);
        return this;
    }

    public void setMultipleTransportable(Boolean multipleTransportable) {
        this.multipleTransportable = multipleTransportable;
    }

    public String getDeliveryTimeOfGoods() {
        return this.deliveryTimeOfGoods;
    }

    public OrderRegistrationInfo deliveryTimeOfGoods(String deliveryTimeOfGoods) {
        this.setDeliveryTimeOfGoods(deliveryTimeOfGoods);
        return this;
    }

    public void setDeliveryTimeOfGoods(String deliveryTimeOfGoods) {
        this.deliveryTimeOfGoods = deliveryTimeOfGoods;
    }

    public Double getTotalWeightInKg() {
        return this.totalWeightInKg;
    }

    public OrderRegistrationInfo totalWeightInKg(Double totalWeightInKg) {
        this.setTotalWeightInKg(totalWeightInKg);
        return this;
    }

    public void setTotalWeightInKg(Double totalWeightInKg) {
        this.totalWeightInKg = totalWeightInKg;
    }

    public Boolean getPossibilityCarrying() {
        return this.possibilityCarrying;
    }

    public OrderRegistrationInfo possibilityCarrying(Boolean possibilityCarrying) {
        this.setPossibilityCarrying(possibilityCarrying);
        return this;
    }

    public void setPossibilityCarrying(Boolean possibilityCarrying) {
        this.possibilityCarrying = possibilityCarrying;
    }

    public Boolean getPossibilityClearance() {
        return this.possibilityClearance;
    }

    public OrderRegistrationInfo possibilityClearance(Boolean possibilityClearance) {
        this.setPossibilityClearance(possibilityClearance);
        return this;
    }

    public void setPossibilityClearance(Boolean possibilityClearance) {
        this.possibilityClearance = possibilityClearance;
    }

    public Boolean getAbleAddServiceInProductOrder() {
        return this.ableAddServiceInProductOrder;
    }

    public OrderRegistrationInfo ableAddServiceInProductOrder(Boolean ableAddServiceInProductOrder) {
        this.setAbleAddServiceInProductOrder(ableAddServiceInProductOrder);
        return this;
    }

    public void setAbleAddServiceInProductOrder(Boolean ableAddServiceInProductOrder) {
        this.ableAddServiceInProductOrder = ableAddServiceInProductOrder;
    }

    public Boolean getFreeZone() {
        return this.freeZone;
    }

    public OrderRegistrationInfo freeZone(Boolean freeZone) {
        this.setFreeZone(freeZone);
        return this;
    }

    public void setFreeZone(Boolean freeZone) {
        this.freeZone = freeZone;
    }

    public String getCurrencyCode() {
        return this.currencyCode;
    }

    public OrderRegistrationInfo currencyCode(String currencyCode) {
        this.setCurrencyCode(currencyCode);
        return this;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public BigDecimal getFobAmount() {
        return this.fobAmount;
    }

    public OrderRegistrationInfo fobAmount(BigDecimal fobAmount) {
        this.setFobAmount(fobAmount);
        return this;
    }

    public void setFobAmount(BigDecimal fobAmount) {
        this.fobAmount = fobAmount;
    }

    public BigDecimal getDiscount() {
        return this.discount;
    }

    public OrderRegistrationInfo discount(BigDecimal discount) {
        this.setDiscount(discount);
        return this;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getShipmentCost() {
        return this.shipmentCost;
    }

    public OrderRegistrationInfo shipmentCost(BigDecimal shipmentCost) {
        this.setShipmentCost(shipmentCost);
        return this;
    }

    public void setShipmentCost(BigDecimal shipmentCost) {
        this.shipmentCost = shipmentCost;
    }

    public BigDecimal getOthrCost() {
        return this.othrCost;
    }

    public OrderRegistrationInfo othrCost(BigDecimal othrCost) {
        this.setOthrCost(othrCost);
        return this;
    }

    public void setOthrCost(BigDecimal othrCost) {
        this.othrCost = othrCost;
    }

    public BigDecimal getTotalAmount() {
        return this.totalAmount;
    }

    public OrderRegistrationInfo totalAmount(BigDecimal totalAmount) {
        this.setTotalAmount(totalAmount);
        return this;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Boolean getIsFile() {
        return this.isFile;
    }

    public OrderRegistrationInfo isFile(Boolean isFile) {
        this.setIsFile(isFile);
        return this;
    }

    public void setIsFile(Boolean isFile) {
        this.isFile = isFile;
    }

    public String getFileNumber() {
        return this.fileNumber;
    }

    public OrderRegistrationInfo fileNumber(String fileNumber) {
        this.setFileNumber(fileNumber);
        return this;
    }

    public void setFileNumber(String fileNumber) {
        this.fileNumber = fileNumber;
    }

    public Boolean getExtended() {
        return this.extended;
    }

    public OrderRegistrationInfo extended(Boolean extended) {
        this.setExtended(extended);
        return this;
    }

    public void setExtended(Boolean extended) {
        this.extended = extended;
    }

    public Boolean getUpdated() {
        return this.updated;
    }

    public OrderRegistrationInfo updated(Boolean updated) {
        this.setUpdated(updated);
        return this;
    }

    public void setUpdated(Boolean updated) {
        this.updated = updated;
    }

    public Boolean getGenerateFromService() {
        return this.generateFromService;
    }

    public OrderRegistrationInfo generateFromService(Boolean generateFromService) {
        this.setGenerateFromService(generateFromService);
        return this;
    }

    public void setGenerateFromService(Boolean generateFromService) {
        this.generateFromService = generateFromService;
    }

    public String getCertificateNumber() {
        return this.certificateNumber;
    }

    public OrderRegistrationInfo certificateNumber(String certificateNumber) {
        this.setCertificateNumber(certificateNumber);
        return this;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public String getCentralBankCode() {
        return this.centralBankCode;
    }

    public OrderRegistrationInfo centralBankCode(String centralBankCode) {
        this.setCentralBankCode(centralBankCode);
        return this;
    }

    public void setCentralBankCode(String centralBankCode) {
        this.centralBankCode = centralBankCode;
    }

    public Boolean getIsMigrational() {
        return this.isMigrational;
    }

    public OrderRegistrationInfo isMigrational(Boolean isMigrational) {
        this.setIsMigrational(isMigrational);
        return this;
    }

    public void setIsMigrational(Boolean isMigrational) {
        this.isMigrational = isMigrational;
    }

    public Long getExternalCustomer() {
        return this.externalCustomer;
    }

    public OrderRegistrationInfo externalCustomer(Long externalCustomer) {
        this.setExternalCustomer(externalCustomer);
        return this;
    }

    public void setExternalCustomer(Long externalCustomer) {
        this.externalCustomer = externalCustomer;
    }

    public Long getSumRedemptionDuration() {
        return this.sumRedemptionDuration;
    }

    public OrderRegistrationInfo sumRedemptionDuration(Long sumRedemptionDuration) {
        this.setSumRedemptionDuration(sumRedemptionDuration);
        return this;
    }

    public void setSumRedemptionDuration(Long sumRedemptionDuration) {
        this.sumRedemptionDuration = sumRedemptionDuration;
    }

    public Long getExtendedDayNumber() {
        return this.extendedDayNumber;
    }

    public OrderRegistrationInfo extendedDayNumber(Long extendedDayNumber) {
        this.setExtendedDayNumber(extendedDayNumber);
        return this;
    }

    public void setExtendedDayNumber(Long extendedDayNumber) {
        this.extendedDayNumber = extendedDayNumber;
    }

    public String getMainGroupProductCode() {
        return this.mainGroupProductCode;
    }

    public OrderRegistrationInfo mainGroupProductCode(String mainGroupProductCode) {
        this.setMainGroupProductCode(mainGroupProductCode);
        return this;
    }

    public void setMainGroupProductCode(String mainGroupProductCode) {
        this.mainGroupProductCode = mainGroupProductCode;
    }

    public String getProducerCountries() {
        return this.producerCountries;
    }

    public OrderRegistrationInfo producerCountries(String producerCountries) {
        this.setProducerCountries(producerCountries);
        return this;
    }

    public void setProducerCountries(String producerCountries) {
        this.producerCountries = producerCountries;
    }

    public byte[] getExcelFile() {
        return this.excelFile;
    }

    public OrderRegistrationInfo excelFile(byte[] excelFile) {
        this.setExcelFile(excelFile);
        return this;
    }

    public void setExcelFile(byte[] excelFile) {
        this.excelFile = excelFile;
    }

    public String getExcelFileContentType() {
        return this.excelFileContentType;
    }

    public OrderRegistrationInfo excelFileContentType(String excelFileContentType) {
        this.excelFileContentType = excelFileContentType;
        return this;
    }

    public void setExcelFileContentType(String excelFileContentType) {
        this.excelFileContentType = excelFileContentType;
    }

    public String getCommissionTransactionNumber() {
        return this.commissionTransactionNumber;
    }

    public OrderRegistrationInfo commissionTransactionNumber(String commissionTransactionNumber) {
        this.setCommissionTransactionNumber(commissionTransactionNumber);
        return this;
    }

    public void setCommissionTransactionNumber(String commissionTransactionNumber) {
        this.commissionTransactionNumber = commissionTransactionNumber;
    }

    public Set<LicenceInfo> getLicenceInfos() {
        return this.licenceInfos;
    }

    public void setLicenceInfos(Set<LicenceInfo> licenceInfos) {
        if (this.licenceInfos != null) {
            this.licenceInfos.forEach(i -> i.setLicenceInfo(null));
        }
        if (licenceInfos != null) {
            licenceInfos.forEach(i -> i.setLicenceInfo(this));
        }
        this.licenceInfos = licenceInfos;
    }

    public OrderRegistrationInfo licenceInfos(Set<LicenceInfo> licenceInfos) {
        this.setLicenceInfos(licenceInfos);
        return this;
    }

    public OrderRegistrationInfo addLicenceInfo(LicenceInfo licenceInfo) {
        this.licenceInfos.add(licenceInfo);
        licenceInfo.setLicenceInfo(this);
        return this;
    }

    public OrderRegistrationInfo removeLicenceInfo(LicenceInfo licenceInfo) {
        this.licenceInfos.remove(licenceInfo);
        licenceInfo.setLicenceInfo(null);
        return this;
    }

    public Set<OrderRegServ> getOrderRegServs() {
        return this.orderRegServs;
    }

    public void setOrderRegServs(Set<OrderRegServ> orderRegServs) {
        if (this.orderRegServs != null) {
            this.orderRegServs.forEach(i -> i.setOrderRegService(null));
        }
        if (orderRegServs != null) {
            orderRegServs.forEach(i -> i.setOrderRegService(this));
        }
        this.orderRegServs = orderRegServs;
    }

    public OrderRegistrationInfo orderRegServs(Set<OrderRegServ> orderRegServs) {
        this.setOrderRegServs(orderRegServs);
        return this;
    }

    public OrderRegistrationInfo addOrderRegServ(OrderRegServ orderRegServ) {
        this.orderRegServs.add(orderRegServ);
        orderRegServ.setOrderRegService(this);
        return this;
    }

    public OrderRegistrationInfo removeOrderRegServ(OrderRegServ orderRegServ) {
        this.orderRegServs.remove(orderRegServ);
        orderRegServ.setOrderRegService(null);
        return this;
    }

    public Set<PurchaseFromOtherResources> getPurchaseFromOtherResources() {
        return this.purchaseFromOtherResources;
    }

    public void setPurchaseFromOtherResources(Set<PurchaseFromOtherResources> purchaseFromOtherResources) {
        if (this.purchaseFromOtherResources != null) {
            this.purchaseFromOtherResources.forEach(i -> i.setPurchaseFromOtherResources(null));
        }
        if (purchaseFromOtherResources != null) {
            purchaseFromOtherResources.forEach(i -> i.setPurchaseFromOtherResources(this));
        }
        this.purchaseFromOtherResources = purchaseFromOtherResources;
    }

    public OrderRegistrationInfo purchaseFromOtherResources(Set<PurchaseFromOtherResources> purchaseFromOtherResources) {
        this.setPurchaseFromOtherResources(purchaseFromOtherResources);
        return this;
    }

    public OrderRegistrationInfo addPurchaseFromOtherResources(PurchaseFromOtherResources purchaseFromOtherResources) {
        this.purchaseFromOtherResources.add(purchaseFromOtherResources);
        purchaseFromOtherResources.setPurchaseFromOtherResources(this);
        return this;
    }

    public OrderRegistrationInfo removePurchaseFromOtherResources(PurchaseFromOtherResources purchaseFromOtherResources) {
        this.purchaseFromOtherResources.remove(purchaseFromOtherResources);
        purchaseFromOtherResources.setPurchaseFromOtherResources(null);
        return this;
    }

    public Set<Custom> getCustoms() {
        return this.customs;
    }

    public void setCustoms(Set<Custom> customs) {
        if (this.customs != null) {
            this.customs.forEach(i -> i.removeOrderRegistrationInfo(this));
        }
        if (customs != null) {
            customs.forEach(i -> i.addOrderRegistrationInfo(this));
        }
        this.customs = customs;
    }

    public OrderRegistrationInfo customs(Set<Custom> customs) {
        this.setCustoms(customs);
        return this;
    }

    public OrderRegistrationInfo addCustom(Custom custom) {
        this.customs.add(custom);
        custom.getOrderRegistrationInfos().add(this);
        return this;
    }

    public OrderRegistrationInfo removeCustom(Custom custom) {
        this.customs.remove(custom);
        custom.getOrderRegistrationInfos().remove(this);
        return this;
    }

    public Set<Product> getProductInfos() {
        return this.productInfos;
    }

    public void setProductInfos(Set<Product> products) {
        if (this.productInfos != null) {
            this.productInfos.forEach(i -> i.removeOrderRegistrationInfo(this));
        }
        if (products != null) {
            products.forEach(i -> i.addOrderRegistrationInfo(this));
        }
        this.productInfos = products;
    }

    public OrderRegistrationInfo productInfos(Set<Product> products) {
        this.setProductInfos(products);
        return this;
    }

    public OrderRegistrationInfo addProductInfo(Product product) {
        this.productInfos.add(product);
        product.getOrderRegistrationInfos().add(this);
        return this;
    }

    public OrderRegistrationInfo removeProductInfo(Product product) {
        this.productInfos.remove(product);
        product.getOrderRegistrationInfos().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrderRegistrationInfo)) {
            return false;
        }
        return getId() != null && getId().equals(((OrderRegistrationInfo) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OrderRegistrationInfo{" +
            "id=" + getId() +
            ", orderRegNum='" + getOrderRegNum() + "'" +
            ", startOrderRegDate='" + getStartOrderRegDate() + "'" +
            ", endOrderRegDate='" + getEndOrderRegDate() + "'" +
            ", requestNumber='" + getRequestNumber() + "'" +
            ", bankCode='" + getBankCode() + "'" +
            ", branchCode='" + getBranchCode() + "'" +
            ", customerNumber='" + getCustomerNumber() + "'" +
            ", applicantNationalcode='" + getApplicantNationalcode() + "'" +
            ", performaNumber='" + getPerformaNumber() + "'" +
            ", performaDate='" + getPerformaDate() + "'" +
            ", performaExpiryDate='" + getPerformaExpiryDate() + "'" +
            ", performaDatePersian='" + getPerformaDatePersian() + "'" +
            ", performaExpiryDatePersian='" + getPerformaExpiryDatePersian() + "'" +
            ", infoSubmissionDate='" + getInfoSubmissionDate() + "'" +
            ", sellerName='" + getSellerName() + "'" +
            ", beneficiaryCountry='" + getBeneficiaryCountry() + "'" +
            ", sourceCountry='" + getSourceCountry() + "'" +
            ", multipleTransportable='" + getMultipleTransportable() + "'" +
            ", deliveryTimeOfGoods='" + getDeliveryTimeOfGoods() + "'" +
            ", totalWeightInKg=" + getTotalWeightInKg() +
            ", possibilityCarrying='" + getPossibilityCarrying() + "'" +
            ", possibilityClearance='" + getPossibilityClearance() + "'" +
            ", ableAddServiceInProductOrder='" + getAbleAddServiceInProductOrder() + "'" +
            ", freeZone='" + getFreeZone() + "'" +
            ", currencyCode='" + getCurrencyCode() + "'" +
            ", fobAmount=" + getFobAmount() +
            ", discount=" + getDiscount() +
            ", shipmentCost=" + getShipmentCost() +
            ", othrCost=" + getOthrCost() +
            ", totalAmount=" + getTotalAmount() +
            ", isFile='" + getIsFile() + "'" +
            ", fileNumber='" + getFileNumber() + "'" +
            ", extended='" + getExtended() + "'" +
            ", updated='" + getUpdated() + "'" +
            ", generateFromService='" + getGenerateFromService() + "'" +
            ", certificateNumber='" + getCertificateNumber() + "'" +
            ", centralBankCode='" + getCentralBankCode() + "'" +
            ", isMigrational='" + getIsMigrational() + "'" +
            ", externalCustomer=" + getExternalCustomer() +
            ", sumRedemptionDuration=" + getSumRedemptionDuration() +
            ", extendedDayNumber=" + getExtendedDayNumber() +
            ", mainGroupProductCode='" + getMainGroupProductCode() + "'" +
            ", producerCountries='" + getProducerCountries() + "'" +
            ", excelFile='" + getExcelFile() + "'" +
            ", excelFileContentType='" + getExcelFileContentType() + "'" +
            ", commissionTransactionNumber='" + getCommissionTransactionNumber() + "'" +
            "}";
    }
}
