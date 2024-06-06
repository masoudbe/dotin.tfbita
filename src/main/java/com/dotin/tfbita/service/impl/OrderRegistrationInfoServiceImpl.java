package com.dotin.tfbita.service.impl;

import com.dotin.tfbita.domain.OrderRegistrationInfo;
import com.dotin.tfbita.repository.OrderRegistrationInfoRepository;
import com.dotin.tfbita.service.OrderRegistrationInfoService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.dotin.tfbita.domain.OrderRegistrationInfo}.
 */
@Service
@Transactional
public class OrderRegistrationInfoServiceImpl implements OrderRegistrationInfoService {

    private final Logger log = LoggerFactory.getLogger(OrderRegistrationInfoServiceImpl.class);

    private final OrderRegistrationInfoRepository orderRegistrationInfoRepository;

    public OrderRegistrationInfoServiceImpl(OrderRegistrationInfoRepository orderRegistrationInfoRepository) {
        this.orderRegistrationInfoRepository = orderRegistrationInfoRepository;
    }

    @Override
    public OrderRegistrationInfo save(OrderRegistrationInfo orderRegistrationInfo) {
        log.debug("Request to save OrderRegistrationInfo : {}", orderRegistrationInfo);
        return orderRegistrationInfoRepository.save(orderRegistrationInfo);
    }

    @Override
    public OrderRegistrationInfo update(OrderRegistrationInfo orderRegistrationInfo) {
        log.debug("Request to update OrderRegistrationInfo : {}", orderRegistrationInfo);
        return orderRegistrationInfoRepository.save(orderRegistrationInfo);
    }

    @Override
    public Optional<OrderRegistrationInfo> partialUpdate(OrderRegistrationInfo orderRegistrationInfo) {
        log.debug("Request to partially update OrderRegistrationInfo : {}", orderRegistrationInfo);

        return orderRegistrationInfoRepository
            .findById(orderRegistrationInfo.getId())
            .map(existingOrderRegistrationInfo -> {
                if (orderRegistrationInfo.getOrderRegNum() != null) {
                    existingOrderRegistrationInfo.setOrderRegNum(orderRegistrationInfo.getOrderRegNum());
                }
                if (orderRegistrationInfo.getStartOrderRegDate() != null) {
                    existingOrderRegistrationInfo.setStartOrderRegDate(orderRegistrationInfo.getStartOrderRegDate());
                }
                if (orderRegistrationInfo.getEndOrderRegDate() != null) {
                    existingOrderRegistrationInfo.setEndOrderRegDate(orderRegistrationInfo.getEndOrderRegDate());
                }
                if (orderRegistrationInfo.getRequestNumber() != null) {
                    existingOrderRegistrationInfo.setRequestNumber(orderRegistrationInfo.getRequestNumber());
                }
                if (orderRegistrationInfo.getBankCode() != null) {
                    existingOrderRegistrationInfo.setBankCode(orderRegistrationInfo.getBankCode());
                }
                if (orderRegistrationInfo.getBranchCode() != null) {
                    existingOrderRegistrationInfo.setBranchCode(orderRegistrationInfo.getBranchCode());
                }
                if (orderRegistrationInfo.getCustomerNumber() != null) {
                    existingOrderRegistrationInfo.setCustomerNumber(orderRegistrationInfo.getCustomerNumber());
                }
                if (orderRegistrationInfo.getApplicantNationalcode() != null) {
                    existingOrderRegistrationInfo.setApplicantNationalcode(orderRegistrationInfo.getApplicantNationalcode());
                }
                if (orderRegistrationInfo.getPerformaNumber() != null) {
                    existingOrderRegistrationInfo.setPerformaNumber(orderRegistrationInfo.getPerformaNumber());
                }
                if (orderRegistrationInfo.getPerformaDate() != null) {
                    existingOrderRegistrationInfo.setPerformaDate(orderRegistrationInfo.getPerformaDate());
                }
                if (orderRegistrationInfo.getPerformaExpiryDate() != null) {
                    existingOrderRegistrationInfo.setPerformaExpiryDate(orderRegistrationInfo.getPerformaExpiryDate());
                }
                if (orderRegistrationInfo.getPerformaDatePersian() != null) {
                    existingOrderRegistrationInfo.setPerformaDatePersian(orderRegistrationInfo.getPerformaDatePersian());
                }
                if (orderRegistrationInfo.getPerformaExpiryDatePersian() != null) {
                    existingOrderRegistrationInfo.setPerformaExpiryDatePersian(orderRegistrationInfo.getPerformaExpiryDatePersian());
                }
                if (orderRegistrationInfo.getInfoSubmissionDate() != null) {
                    existingOrderRegistrationInfo.setInfoSubmissionDate(orderRegistrationInfo.getInfoSubmissionDate());
                }
                if (orderRegistrationInfo.getSellerName() != null) {
                    existingOrderRegistrationInfo.setSellerName(orderRegistrationInfo.getSellerName());
                }
                if (orderRegistrationInfo.getBeneficiaryCountry() != null) {
                    existingOrderRegistrationInfo.setBeneficiaryCountry(orderRegistrationInfo.getBeneficiaryCountry());
                }
                if (orderRegistrationInfo.getSourceCountry() != null) {
                    existingOrderRegistrationInfo.setSourceCountry(orderRegistrationInfo.getSourceCountry());
                }
                if (orderRegistrationInfo.getMultipleTransportable() != null) {
                    existingOrderRegistrationInfo.setMultipleTransportable(orderRegistrationInfo.getMultipleTransportable());
                }
                if (orderRegistrationInfo.getDeliveryTimeOfGoods() != null) {
                    existingOrderRegistrationInfo.setDeliveryTimeOfGoods(orderRegistrationInfo.getDeliveryTimeOfGoods());
                }
                if (orderRegistrationInfo.getTotalWeightInKg() != null) {
                    existingOrderRegistrationInfo.setTotalWeightInKg(orderRegistrationInfo.getTotalWeightInKg());
                }
                if (orderRegistrationInfo.getPossibilityCarrying() != null) {
                    existingOrderRegistrationInfo.setPossibilityCarrying(orderRegistrationInfo.getPossibilityCarrying());
                }
                if (orderRegistrationInfo.getPossibilityClearance() != null) {
                    existingOrderRegistrationInfo.setPossibilityClearance(orderRegistrationInfo.getPossibilityClearance());
                }
                if (orderRegistrationInfo.getAbleAddServiceInProductOrder() != null) {
                    existingOrderRegistrationInfo.setAbleAddServiceInProductOrder(orderRegistrationInfo.getAbleAddServiceInProductOrder());
                }
                if (orderRegistrationInfo.getFreeZone() != null) {
                    existingOrderRegistrationInfo.setFreeZone(orderRegistrationInfo.getFreeZone());
                }
                if (orderRegistrationInfo.getCurrencyCode() != null) {
                    existingOrderRegistrationInfo.setCurrencyCode(orderRegistrationInfo.getCurrencyCode());
                }
                if (orderRegistrationInfo.getFobAmount() != null) {
                    existingOrderRegistrationInfo.setFobAmount(orderRegistrationInfo.getFobAmount());
                }
                if (orderRegistrationInfo.getDiscount() != null) {
                    existingOrderRegistrationInfo.setDiscount(orderRegistrationInfo.getDiscount());
                }
                if (orderRegistrationInfo.getShipmentCost() != null) {
                    existingOrderRegistrationInfo.setShipmentCost(orderRegistrationInfo.getShipmentCost());
                }
                if (orderRegistrationInfo.getOthrCost() != null) {
                    existingOrderRegistrationInfo.setOthrCost(orderRegistrationInfo.getOthrCost());
                }
                if (orderRegistrationInfo.getTotalAmount() != null) {
                    existingOrderRegistrationInfo.setTotalAmount(orderRegistrationInfo.getTotalAmount());
                }
                if (orderRegistrationInfo.getIsFile() != null) {
                    existingOrderRegistrationInfo.setIsFile(orderRegistrationInfo.getIsFile());
                }
                if (orderRegistrationInfo.getFileNumber() != null) {
                    existingOrderRegistrationInfo.setFileNumber(orderRegistrationInfo.getFileNumber());
                }
                if (orderRegistrationInfo.getExtended() != null) {
                    existingOrderRegistrationInfo.setExtended(orderRegistrationInfo.getExtended());
                }
                if (orderRegistrationInfo.getUpdated() != null) {
                    existingOrderRegistrationInfo.setUpdated(orderRegistrationInfo.getUpdated());
                }
                if (orderRegistrationInfo.getGenerateFromService() != null) {
                    existingOrderRegistrationInfo.setGenerateFromService(orderRegistrationInfo.getGenerateFromService());
                }
                if (orderRegistrationInfo.getCertificateNumber() != null) {
                    existingOrderRegistrationInfo.setCertificateNumber(orderRegistrationInfo.getCertificateNumber());
                }
                if (orderRegistrationInfo.getCentralBankCode() != null) {
                    existingOrderRegistrationInfo.setCentralBankCode(orderRegistrationInfo.getCentralBankCode());
                }
                if (orderRegistrationInfo.getIsMigrational() != null) {
                    existingOrderRegistrationInfo.setIsMigrational(orderRegistrationInfo.getIsMigrational());
                }
                if (orderRegistrationInfo.getExternalCustomer() != null) {
                    existingOrderRegistrationInfo.setExternalCustomer(orderRegistrationInfo.getExternalCustomer());
                }
                if (orderRegistrationInfo.getSumRedemptionDuration() != null) {
                    existingOrderRegistrationInfo.setSumRedemptionDuration(orderRegistrationInfo.getSumRedemptionDuration());
                }
                if (orderRegistrationInfo.getExtendedDayNumber() != null) {
                    existingOrderRegistrationInfo.setExtendedDayNumber(orderRegistrationInfo.getExtendedDayNumber());
                }
                if (orderRegistrationInfo.getMainGroupProductCode() != null) {
                    existingOrderRegistrationInfo.setMainGroupProductCode(orderRegistrationInfo.getMainGroupProductCode());
                }
                if (orderRegistrationInfo.getProducerCountries() != null) {
                    existingOrderRegistrationInfo.setProducerCountries(orderRegistrationInfo.getProducerCountries());
                }
                if (orderRegistrationInfo.getExcelFile() != null) {
                    existingOrderRegistrationInfo.setExcelFile(orderRegistrationInfo.getExcelFile());
                }
                if (orderRegistrationInfo.getExcelFileContentType() != null) {
                    existingOrderRegistrationInfo.setExcelFileContentType(orderRegistrationInfo.getExcelFileContentType());
                }
                if (orderRegistrationInfo.getCommissionTransactionNumber() != null) {
                    existingOrderRegistrationInfo.setCommissionTransactionNumber(orderRegistrationInfo.getCommissionTransactionNumber());
                }

                return existingOrderRegistrationInfo;
            })
            .map(orderRegistrationInfoRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderRegistrationInfo> findAll() {
        log.debug("Request to get all OrderRegistrationInfos");
        return orderRegistrationInfoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<OrderRegistrationInfo> findOne(Long id) {
        log.debug("Request to get OrderRegistrationInfo : {}", id);
        return orderRegistrationInfoRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete OrderRegistrationInfo : {}", id);
        orderRegistrationInfoRepository.deleteById(id);
    }
}
